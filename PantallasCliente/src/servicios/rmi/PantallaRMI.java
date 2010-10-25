/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servicios.rmi;

import java.rmi.Remote;

/**
 *
 * @author kradac
 */
public interface PantallaRMI extends Remote {
    public String enviarComando(String input) throws java.rmi.RemoteException;
}
