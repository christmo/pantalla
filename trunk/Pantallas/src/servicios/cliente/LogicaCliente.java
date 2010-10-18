/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.rmi.PantallaRMI;

/**
 *
 * @author kradac
 */
public class LogicaCliente implements PantallaRMI {

    private Socket echoSocket = null;
    private PrintWriter os = null;
    private BufferedReader is = null;
    private String host = "localhost";
    private int port = 7;
    private String output = "Error";
    private boolean connected = false;

    public void setHostAndPort(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private synchronized void connect() throws java.rmi.RemoteException {
        try {
            //EJERCICIO: Implemente el método connect
            echoSocket = new Socket(host, port);
            connected = true;
            is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            os = new PrintWriter(echoSocket.getOutputStream());
            //os = echoSocket.getOutputStream();
        } catch (UnknownHostException ex) {
            Logger.getLogger(LogicaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogicaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void disconnect() {
        try {
            //EJERCICIO: Implemente el método disconnect
            os.close();
            is.close();
            echoSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(LogicaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String enviarComando(String input) throws RemoteException {
        connect();
        if (echoSocket != null && os != null && is != null) {
            try {
                os.println(input);
                os.flush();
                output = is.readLine();
            } catch (IOException e) {
                System.err.println("I/O failed in reading/writing socket");
                throw new java.rmi.RemoteException("I/O failed in reading/writing socket");
            }
        }
        disconnect();
        return output;
    }
}
