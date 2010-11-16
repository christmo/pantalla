/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.servidor;

import BaseDatos.BaseDatos;
import comunicacion.comm.CommPantalla;
import java.rmi.RemoteException;
import servicios.rmi.PantallaRMI;

/**
 *
 * @author kradac
 */
public class LogicaServidor implements PantallaRMI {

    private BaseDatos bd;
    private CommPantalla comm;

    public LogicaServidor() {
        bd = new BaseDatos();
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
         */
        int nCaja = 0;

        String[] turno = comando.split("%");
        try {
            nCaja = Integer.parseInt(turno[0]);
        } catch (NumberFormatException ex) {
        }
        boolean resultado = false;
        try {
            resultado = bd.guardarTurno(nCaja, turno[1]);
        } catch (IndexOutOfBoundsException ioex) {
        }
        if (resultado) {
            //String cmd = "<MENS3\r" + "CAJA" + " " + turno[2] + " " + turno[0] + "\r";
            //3A > 5CAJ
            //3A >08CAJ
            //String cmd = "3A > " + "CAJA" + " " + turno[2] + " " + turno[0] + "\r";
            if (!turno[1].equals("INACTIVO")) {
                String cmd = "3A" + " " + turno[2] + " " + turno[0] + "CAJ\r";
                comm = new CommPantalla();
                //cmd ="312345678\r";
                comm.enviarComando(cmd);
                comm.start();
            }
        } else {
            System.out.println("NO se guardo turno...");
        }

        return "" + resultado;
    }
}
