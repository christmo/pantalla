/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import comunicacion.comm.CommPantalla;
import comunicacion.socket.SocketPantalla;
import java.util.Properties;

/**
 *
 * @author kradac
 */
public class ComunicacionPantalla {

    /**
     * Permite enviar un comando a la pantalla dependiendo del tipo de
     * conexion que se tenga
     * @param cmd
     * @param prop
     */
    public ComunicacionPantalla(String cmd, Properties prop, int id_pantalla) {
        String comm = prop.getProperty("comm");
        if (!comm.equals("0")) {
            envioInformacionPantallaCOMM(cmd);
        } else {
            envioInformacionPantallaSocket(cmd,id_pantalla);
        }
    }

    /**
     * Envia los comando a travez de socket
     * @param cmd
     */
    private void envioInformacionPantallaSocket(String cmd,int idPantalla) {
        SocketPantalla sockPantalla = new SocketPantalla(idPantalla);
        sockPantalla.enviarComando(cmd);
        sockPantalla.start();
    }

    /**
     * Envia los comando a traves de puerto serial
     * @param cmd
     */
    private void envioInformacionPantallaCOMM(String cmd) {
        CommPantalla comm = new CommPantalla();
        comm.enviarComando(cmd);
        comm.start();
    }
}
