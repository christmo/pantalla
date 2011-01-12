/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion.socket;

import PantallaGUI.utilitarios.Utilitarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kradac
 */
public class SocketPantalla extends Thread {

    private Socket socket = null;
    private PrintWriter os = null;
    // private BufferedReader is;
    private String host;
    private int port;
    private String cmd;

    public SocketPantalla(int idPantalla) {
        Properties arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
        String ipPantalla = "";
        int puertoPantalla;
        if (idPantalla == 1) {
            /**
             * Pantalla de los clientes para hacer las llamadas a los clientes
             */
            ipPantalla = arcConfig.getProperty("ip_pantalla1");
            try {
                puertoPantalla = Integer.parseInt(arcConfig.getProperty("puerto_pantalla1"));
            } catch (NumberFormatException ex) {
                puertoPantalla = 0;
            }
        } else {
            /**
             * Pantalla para pasar los mensajes que se requiera
             */
            ipPantalla = arcConfig.getProperty("ip_pantalla2");
            try {
                puertoPantalla = Integer.parseInt(arcConfig.getProperty("puerto_pantalla2"));
            } catch (NumberFormatException ex) {
                puertoPantalla = 0;
            }
        }
        this.host = ipPantalla;
        this.port = puertoPantalla;
    }

    /**
     * Permite enviar un comando para que sea ejecutado en el hilo
     * @param comando
     */
    public void enviarComando(String comando) {
        this.cmd = comando;
    }

    @Override
    public void run() {
        conectar();
        String[] comandos = cmd.split("&%");
        for (int i = 0; i < comandos.length - 1; i++) {
            for (char letra : comandos[i].toCharArray()) {
                /**
                 * Envia letra a letra con una pausa para que pa pantalla pueda
                 * procesar la informacion
                 */
                enviarDatos("" + letra);
                /**
                 * Comprueba que al final del comando se envie si se envia las
                 * letras con una pausa o se envia rapidamente sin pausa a la pantalla
                 */
                if (Boolean.parseBoolean(comandos[comandos.length - 1])) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        desconectar();
        System.out.println("Comando: " + cmd);
        
        /**
         * Inhibir el uso de la pantalla mientras se procesa la información
         */
//        try {
//            System.out.println("Durmiendo por la pantalla...");
//            Thread.sleep(1500);
//            System.err.println("---");
//        } catch (InterruptedException ex) {
//            Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /**
     * Conecta el sock del cliente contra el servidor
     * @throws java.rmi.RemoteException
     */
    private void conectar() {
        try {
            socket = new Socket(host, port);

            //is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(socket.getOutputStream());

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
            } else if (ex.getMessage().equals("Connection timed out: connect")) {
                System.out.println("No se puede conectar con la pantalla");
                System.exit(1);
            } else {
                //System.out.println("Conexion rechazada por el servidor...");
                Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Desconecta el Socket contra el servidor
     */
    private void desconectar() {
        try {
            os.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
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
    public void enviarDatos(String input) {

        if (socket != null && os != null) {
            try {
                os.print(input);
                os.flush();
            } catch (NullPointerException nex) {
            }
        }

    }
//    public static void main(String[] args) {
//        SocketPantalla s = new SocketPantalla("localhost", 9999);
//        s.enviarDatos("christmo");
//    }
}
