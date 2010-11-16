/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.servidor;

import PantallaGUI.utilitarios.Utilitarios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author kradac
 */
public class ServidorTurnos extends Thread {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static BufferedReader is = null;
    private static PrintWriter os = null;
    private static String inputline = new String();
    private static LogicaServidor server = new LogicaServidor();

    public ServidorTurnos() {
        this.start();
    }

    /**
     * Establece un sock con el cliente y abre conexoón con la base de
     * datos para que se registren las llamadas a los clientes
     */
    private void levantarServidorTurnos() {
        try {
            Properties prop = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
            int puerto = Integer.parseInt(prop.getProperty("puerto"));
            serverSocket = new ServerSocket(puerto);
            System.out.println("Escuchando el pruerto: " + puerto);
        } catch (IOException e) {
            System.out.println("El puerto esta en uso, el servidor ya esta corriendo...");
            //JOptionPane.showMessageDialog(null, "El servidor ya se encuentra corriendo...", "Error...", 0);
            System.exit(1);
        }

        try {
            boolean listening = true;
            while (listening) {
                clientSocket = serverSocket.accept();
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream());
                while ((inputline = is.readLine()) != null) {
                    os.println(server.enviarComando(inputline));
                    os.flush();
                }
                os.close();
                is.close();
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error sending/receiving" + e.getMessage());
        }
    }

    @Override
    public void run() {
        levantarServidorTurnos();
    }
}
