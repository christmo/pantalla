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
    private String host;
    private int port;
    private String output = "Error";
    private int intContadorIntentosReconexion = 0;

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
            System.out.println("[CONECTADO]");
        } catch (UnknownHostException ex) {
            System.err.println("" + ex);
        } catch (IOException ex) {
            System.out.println("Conexion rechazada por el servidor... IP[" + host + "] PUERTO[" + port + "]");
            if (intContadorIntentosReconexion != 5) {
                try {
                    System.out.println("[RECONECTAR SERVIDOR] = " + intContadorIntentosReconexion);
                    Thread.sleep(1000);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(LogicaCliente.class.getName()).log(Level.SEVERE, null, ex1);
                }
                intContadorIntentosReconexion++;
                conectar();
            } else {
                intContadorIntentosReconexion = 0;

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
                System.err.println("Error al escribir en el servidor...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LogicaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                throw new java.rmi.RemoteException("Error al escribir en el servidor...");
            } catch (NullPointerException nex) {
            }
        }
        desconectar();
        return output;
    }
}
