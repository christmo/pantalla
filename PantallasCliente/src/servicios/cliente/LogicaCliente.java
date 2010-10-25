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
import javax.swing.JOptionPane;
import servicios.rmi.PantallaRMI;

/**
 *
 * @author kradac
 */
public class LogicaCliente implements PantallaRMI {

    private Socket echoSocket = null;
    private PrintWriter os = null;
    private BufferedReader is = null;
    private String host;
    private int port;
    private String output = "Error";

    public void setHostAndPort(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private synchronized void connect() throws java.rmi.RemoteException {
        try {
            //EJERCICIO: Implemente el método connect
            echoSocket = new Socket(host, port);

            is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            os = new PrintWriter(echoSocket.getOutputStream());

        } catch (UnknownHostException ex) {
            System.err.println("" + ex);
        } catch (IOException ex) {
            if (ex.getMessage().equals("Connection refused")) {
                JOptionPane.showMessageDialog(null, "Conexión NO establecida con el servidor se puede deber"
                        + "\na problemas con la red o con el servidor de turnos...", "Error...", 0);
                System.out.println("Conexion rechazada por el servidor...");
                System.exit(0);
            } else if (ex.getMessage().equals("Connection refused: connect")) {
                JOptionPane.showMessageDialog(null, "Conexión NO establecida con el servidor se puede deber"
                        + "\na problemas con la red o con el servidor de turnos...", "Error...", 0);
                System.out.println("Conexion rechazada por el servidor...");
                System.exit(0);
            } else {
                //System.out.println("Conexion rechazada por el servidor...");
                Logger.getLogger(LogicaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        } catch (NullPointerException nex) {
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
            } catch (NullPointerException nex) {
            }
        }
        disconnect();
        return output;
    }
}
