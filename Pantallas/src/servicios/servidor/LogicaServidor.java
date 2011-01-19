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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kradac
 */
public class LogicaServidor implements PantallaRMI {

    private BaseDatos bd;
    private Properties arcConfig;
    /**
     * Esta es la constante de tiempo que el programa debe esperar para guardar
     * una llamada desde el cliente
     */
    private int TIEMPO;
    private int contResgistros = 0;

    public LogicaServidor(Properties arch, BaseDatos db) {
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
                resultado = bd.guardarTurnoConUsuario(nCaja, turno[1], turno[3]);
            } catch (IndexOutOfBoundsException ioex) {
                return "" + false;
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
                /**
                 * ID caja -> [0] --> numero de la caja donde se envia el llamado
                 * direccion -> [2] -> donde debe apuntar la flecha
                 */
                String cmd = "  3A" + " " + turno[2] + " " + turno[0] + "CAJ\r" + "&%true";
                ComunicacionPantalla enlacePantalla = new ComunicacionPantalla(cmd, arcConfig, 1);
                /**
                 * Pausa para que la pantalla no mezcle los comandos que llegan
                 * en ese mismo instante
                 */
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServidorTurnos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("NO se guardo turno...");
            return "" + false;
        }

        return "" + resultado;
    }

    /**
     * Obtiene el tiempo en segundos de la ultima inserción a la base de
     * datos de una caja determinada
     * @param caja
     * @return long
     */
    private long tiempoEntreLlamadas(int caja) {
        Date horaUltimaLlamada = bd.obtenerUltimaLlamadaCaja(caja);
        Calendar c = GregorianCalendar.getInstance();

        long diferencia;
        try {
            diferencia = (c.getTimeInMillis() - horaUltimaLlamada.getTime()) / 1000;
            System.out.println("Ultima Inserción caja [" + caja + "]:" + diferencia);
        } catch (NullPointerException ex) {
            //Tiempo de diferencia por defecto para una atención (segundos)
            diferencia = 30;
        }
        return diferencia;
    }
}
