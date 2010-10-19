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
        comm = new CommPantalla();
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
        String[] turno = comando.split("%");
        boolean resultado = bd.guardarTurno(Integer.parseInt(turno[0]), turno[1]);
        if (resultado) {
            System.out.println("Turno guardado correctamente..." + turno[2]);
            String cmd = "<MENS3\r" + "CAJA" + " " + turno[2] + " " + turno[0] + "\r";
            comm.enviarComando(cmd);
            comm.start();
        } else {
            System.out.println("NO se guardo turno...");
        }

        return "" + resultado;
    }
}
