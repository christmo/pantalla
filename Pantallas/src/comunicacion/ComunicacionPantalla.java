/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import PantallaGUI.PrincipalGUI;
import comunicacion.comm.CommPantalla;
import java.util.Properties;
import javax.swing.JOptionPane;

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
            envioInformacionPantallaSocket(cmd, id_pantalla);
        }
    }

    /**
     * Envia los comando a travez de socket
     * @param cmd
     */
    private void envioInformacionPantallaSocket(String cmd, int idPantalla) throws IndexOutOfBoundsException {
        try {
            PrincipalGUI.pantallas[idPantalla - 1].enviarComando(cmd);
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "No esta conectada esta pantalla", "Error...", 0);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "No esta conectada esta pantalla", "Error...", 0);
        }
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
