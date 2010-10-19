/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;

/**
 *
 * @author kradac
 */
public class servidor {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static BufferedReader is = null;
    private static PrintWriter os = null;
    private static String inputline = new String();
    private static LogicaServidor server = new LogicaServidor();
    private static ResourceBundle rb;

    public static void main(String[] args) {
        try {
            rb = ResourceBundle.getBundle("BaseDatos.configsystem");
            int puerto = Integer.parseInt(rb.getString("puerto"));
            serverSocket = new ServerSocket(puerto);
            System.out.println("Escuchando el pruerto: " + puerto);
        } catch (IOException e) {
            System.out.println("El pueto esta en uso...");
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
            e.printStackTrace();
        }
    }
}
