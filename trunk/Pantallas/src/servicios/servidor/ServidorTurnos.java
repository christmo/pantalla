/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.servidor;

import BaseDatos.BaseDatos;
import comunicacion.socket.SocketPantalla;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

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
    private static LogicaServidor server;
    private static boolean dormirServidorTurnos = false;
    private BaseDatos bd;
    private Properties prop;
    private ArrayList<SocketPantalla> pantallas = new ArrayList<SocketPantalla>();

    public ServidorTurnos(BaseDatos db, Properties arcConfig) {
        this.bd = db;
        this.prop = arcConfig;
        this.start();
    }

    /**
     * Establece un sock con el cliente y abre conexión con la base de
     * datos para que se registren las llamadas a los clientes
     */
    private void levantarServidorTurnos() {
        try {
            server = new LogicaServidor(prop, bd);
            int puerto = Integer.parseInt(prop.getProperty("puerto"));
            serverSocket = new ServerSocket(puerto);
            System.out.println("Escuchando el pruerto: [" + puerto + "] para cajas...");
        } catch (IOException e) {
            System.out.println("El puerto esta en uso, el servidor ya esta corriendo...");
            System.exit(1);
        }

        try {
            boolean listening = true;
            while (listening) {
                clientSocket = serverSocket.accept();
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream());
                if (!dormirServidorTurnos) {
                    while ((inputline = is.readLine()) != null) {
                        os.println(server.enviarComando(inputline));
                        os.flush();
                    }
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

    /**
     * Permite hacer que el servidor de las cajas no atienda las llamadas
     * cuando se esta configurando un nuevo mensaje, fecha u hora que son
     * comando largos
     * @param boolean true dormir| false despertar
     */
    public static void dormirServidorTurnos(boolean dormir) {
        dormirServidorTurnos = dormir;
    }

    @Override
    public void run() {
        levantarServidorTurnos();
    }
}
