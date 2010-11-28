/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.servidor;

import BaseDatos.BaseDatos;
import comunicacion.ComunicacionPantalla;
import comunicacion.comm.CommPantalla;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import servicios.rmi.PantallaRMI;

/**
 *
 * @author kradac
 */
public class LogicaServidor implements PantallaRMI {

    private BaseDatos bd;
    private Properties arcConfig;
    // private CommPantalla comm;
    private int TIEMPO;

    public LogicaServidor(Properties arch, BaseDatos db) {
        //bd = new BaseDatos();
        bd = db;
        arcConfig = arch;
        try {
            TIEMPO = Integer.parseInt(arch.getProperty("tiempo_atencion"));
        } catch (NumberFormatException ex) {
            //tiempo de atención por defecto para un cliente
            TIEMPO = 50;
        }
    }

    /**
     * Guarda en la base de datos los comandos que vienen del cliente hacia el
     * servidor para imprimir el mensaje en la pantalla
     * @param comando -> formato
     * @return String
     * @throws RemoteException
     */
    public String enviarComando(String comando) throws RemoteException {
        /**
         * ID caja -> [0]
         * estado ->  [1]
         * direccion -> [2] -> donde debe apuntar la flecha
         * usuario -> [3]
         */
        int nCaja = 0;

        String[] turno = comando.split("%");
        try {
            nCaja = Integer.parseInt(turno[0]);
        } catch (NumberFormatException ex) {
        }
        boolean resultado = true;

        if (tiempoEntreLlamadas(nCaja) >= TIEMPO) {
            try {
                resultado = bd.guardarTurno(nCaja, turno[1]);
            } catch (IndexOutOfBoundsException ioex) {
            }
        }
        if (resultado) {
            //String cmd = "<MENS3\r" + "CAJA" + " " + turno[2] + " " + turno[0] + "\r";
            //3A > 5CAJ
            //3A >08CAJ
            //String cmd = "3A > " + "CAJA" + " " + turno[2] + " " + turno[0] + "\r";
            if (!turno[1].equals("INACTIVO")) {
                String cmd = "3A" + " " + turno[2] + " " + turno[0] + "CAJ\r";
                ComunicacionPantalla enlacePantalla = new ComunicacionPantalla(cmd, arcConfig);
            }
        } else {
            System.out.println("NO se guardo turno...");
        }

        return "" + resultado;
    }

    private long tiempoEntreLlamadas(int caja) {
        Date horaUltimaLlamada = bd.obtenerUltimaLlamadaCaja(caja);
        Calendar c = GregorianCalendar.getInstance();

        long diferencia;
        try {
            diferencia = (c.getTimeInMillis() - horaUltimaLlamada.getTime()) / 1000;
        } catch (NullPointerException ex) {
            //Tiempo de diferencia por defecto para una atención
            diferencia = 60;
        }
        return diferencia;
    }
}
