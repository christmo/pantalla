/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servicios.servidor;

import BaseDatos.BaseDatos;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import servicios.rmi.PantallaRMI;

/**
 *
 * @author kradac
 */
public class LogicaServidor  implements PantallaRMI {

    private String myURL = "localhost";
    private BaseDatos bd;

    public LogicaServidor() {
        try {
            bd = new BaseDatos();
            myURL = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            myURL = "localhost";
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
         */
        String[] turno = comando.split("%");
        boolean resultado = bd.guardarTurno(Integer.parseInt(turno[0]), turno[1]);
        if (resultado) {
            System.out.println("Turno guardado correctamente...");
        } else {
            System.out.println("NO se guardo turno...");
        }

        return "" + resultado;
    }

}
