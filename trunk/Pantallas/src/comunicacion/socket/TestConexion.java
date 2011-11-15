/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion.socket;

import PantallaGUI.PrincipalGUI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class TestConexion extends Thread {

    @Override
    public void run() {
        while (true) {
            ping();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private synchronized void ping() {
        try {
            /**
             * Enviar @# para comprobar si hay conexion
             * este se envia sin pausas a la pantalla separado por esto &%
             */
            PrincipalGUI.pantallas[0].enviarComando(".&%true");
        } catch (NullPointerException ex) {
        }

        try {
            /**
             * Enviar @# para comprobar si hay conexion
             * este se envia sin pausas a la pantalla separado por esto &%
             */
            PrincipalGUI.pantallas[1].enviarComando(".&%true");
        } catch (NullPointerException ex) {
        }
    }
}
