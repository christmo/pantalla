/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.servidor;

import BaseDatos.BaseDatos;
import comunicacion.ComunicacionPantalla;
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
    private int contResgistros = 0;

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
         * ID caja -> [0] --> numero de la caja donde se envia el llamado
         * estado ->  [1] --> ACT||INA --> de la caja
         * direccion -> [2] -> donde debe apuntar la flecha
         * usuario -> [3] --> usuario que envia la llamada al servidor
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
                //resultado = bd.guardarTurno(nCaja, turno[1]);
                //resultado = bd.guardarTurno(nCaja, turno[1]);
                resultado = bd.guardarTurnoConUsuario(nCaja, turno[1], turno[3]);
            } catch (IndexOutOfBoundsException ioex) {
            }
        } else {
            if (contResgistros == 0) {
                resultado = bd.guardarTurnoConUsuario(nCaja, turno[1], turno[3]);
                contResgistros++;
            }
        }
        if (resultado) {
            //String cmd = "<MENS3\r" + "CAJA" + " " + turno[2] + " " + turno[0] + "\r";
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
            System.out.println("Diferencia: " + diferencia);
        } catch (NullPointerException ex) {
            //Tiempo de diferencia por defecto para una atención (segundos)
            diferencia = 30;
        }
        return diferencia;
    }
}
