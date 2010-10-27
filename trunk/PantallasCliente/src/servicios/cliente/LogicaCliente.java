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

    public LogicaCliente(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Conecta el sock del cliente contra el servidor
     * @throws java.rmi.RemoteException
     */
    private void conectar() throws java.rmi.RemoteException {
        try {
            //EJERCICIO: Implemente el método connect
            echoSocket = new Socket(host, port);

            is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            os = new PrintWriter(echoSocket.getOutputStream());

        } catch (UnknownHostException ex) {
            System.err.println("" + ex);
        } catch (IOException ex) {
            if (ex.getMessage().equals("Connection refused")) {
                System.out.println("Conexion rechazada por el servidor...");
                //JOptionPane.showMessageDialog(null, "Conexión NO establecida con el servidor se puede deber"
                //        + "\na problemas con la red o con el servidor de turnos...", "Error...", 0);
                System.exit(0);
            } else if (ex.getMessage().equals("Connection refused: connect")) {
                System.out.println("Conexion rechazada por el servidor...");
                //JOptionPane.showMessageDialog(null, "Conexión NO establecida con el servidor se puede deber"
                //        + "\na problemas con la red o con el servidor de turnos...", "Error...", 0);
                System.exit(0);
            } else {
                //System.out.println("Conexion rechazada por el servidor...");
                Logger.getLogger(LogicaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Desconecta el Socket contra el servidor
     */
    private void desconectar() {
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

    /**
     * Envia el comando al servidor para que se presente en la pantalla y se
     * presente el mensaje en el pantalla pasamensajes
     * @param input
     * @return String
     * @throws RemoteException
     */
    public String enviarComando(String input) throws RemoteException {
        conectar();
        if (echoSocket != null && os != null && is != null) {
            try {
                os.println(input);
                os.flush();
                output = is.readLine();
            } catch (IOException e) {
                System.err.println("Erro al escribir en el servidor...");
                throw new java.rmi.RemoteException("Erro al escribir en el servidor...");
            } catch (NullPointerException nex) {
            }
        }
        desconectar();
        return output;
    }
}
