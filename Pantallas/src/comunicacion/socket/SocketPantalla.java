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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import servicios.servidor.ServidorTurnos;

/**
 *
 * @author kradac
 */
public class SocketPantalla {

    private Socket socket = null;
    private PrintWriter os = null;
    // private BufferedReader is;
    private String host;
    private int port;
    private String cmd;
    private JLabel lblIconPantalla;
    private ImageIcon iconOff = new ImageIcon(getClass().getResource("/iconos/panOff.png"));
    private ImageIcon iconOn = new ImageIcon(getClass().getResource("/iconos/panOn.png"));

    public SocketPantalla(int idPantalla, JLabel lblIconPantalla) {
        Properties arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
        String ipPantalla = "";
        int puertoPantalla = 0;
        this.lblIconPantalla = lblIconPantalla;

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
        } else if (idPantalla == 2) {
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
        if (puertoPantalla != 0) {
            conectar();
            System.out.println("Conectado... ");
        }
    }
    /**
     * controla si el comando anterior es caja para poner la pausa antes de
     * enviar el comando para esperar que se libere la pantalla
     */
    private boolean booCMDcaja = false;
    /**
     * controla si el comando anterior es caja para poner la pausa despues de
     * enviar el comando
     */
    private boolean booCMDcaja2 = false;
    /**
     * Comprueba si se quiere borrar la pantalla para inhibir las cajas por menos
     * tiempo que con los otros comandos
     */
    private boolean booBorrarPantalla = false;

    /**
     * Permite enviar un comando para que sea ejecutado en el hilo
     * @param comando
     */
    public synchronized void enviarComando(String comando) {
        this.cmd = comando;

        String[] comandos = cmd.split("&%");
        if (getSocket() != null) {
            for (int i = 0; i < comandos.length - 1; i++) {
                System.out.println("IMP CMD:" + comandos[i]);
                if (booCMDcaja) {
                    if (comandos[i].charAt(0) == '1'
                            || comandos[i].charAt(0) == 'd'
                            || comandos[i].charAt(0) == 't'
                            || comandos[i].charAt(0) == 'V'
                            || comandos[i].charAt(0) == 'I'
                            || comandos[i].charAt(0) == 'v'
                            || comandos[i].charAt(0) == 'T') {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.err.println("[durmiendo]");
                        ServidorTurnos.dormirServidorTurnos(true);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        booCMDcaja = false;
                        booCMDcaja2 = true;
                    } else if (comandos[i].charAt(0) == '2') {
                        try {
                            ServidorTurnos.dormirServidorTurnos(true);
                            Thread.sleep(1000);
                            booBorrarPantalla = true;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                for (char letra : comandos[i].toCharArray()) {
                    /**
                     * Envia letra a letra con una pausa para que la pantalla pueda
                     * procesar la informacion
                     */
                    if (enviarDatos("" + letra)) {
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
                    } else {
                        System.out.println("[RECONECTAR]");
                        reConectar();
                        break;
                    }
                }
                if (booCMDcaja2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    booCMDcaja2 = false;
                }
                if (booBorrarPantalla && comandos[1].charAt(0) == '1') {
                    booBorrarPantalla = false;
                } else {
                    ServidorTurnos.dormirServidorTurnos(false);
                }
                if (comandos[i].length() == 12 && comandos[i].charAt(2) == '3') {
                    booCMDcaja = true;
                }
            }
            System.out.println("Comando: " + cmd);
        } else {
            System.out.println("No esta conectado a esa pantalla...");
            reConectar();
        }
    }
    /**
     * Cuenta las veces que se ha querido reconectar la aplicacion a la pantalla
     */
    int contadorReconexion = 0;

    /**
     * Conecta el sock del cliente contra el servidor
     * @throws java.rmi.RemoteException
     */
    private void conectar() {
        try {
            setSocket(new Socket(host, port));

            //is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(getSocket().getOutputStream());
            System.out.println("[CONECTADO][" + host + "][" + port + "]");
            lblIconPantalla.setIcon(iconOn);
            enviarComandoDesbloqueo();
        } catch (UnknownHostException ex) {
            Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Conexion rechazada por el servidor... IP[" + host + "] PUERTO[" + port + "]");
            lblIconPantalla.setIcon(iconOff);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex1) {
                Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("[RECONECTAR]");
            conectar();
        }
    }

    /**
     * Envia el comando de desbloqueo para cuando se conectan las pantallas
     */
    private void enviarComandoDesbloqueo() {
        String comando = "   3     \r" + "&%true";
        enviarComando(comando);
    }

    private void reConectar() {
        try {
            setSocket(new Socket(host, port));

            //is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(getSocket().getOutputStream());
            System.out.println("[CONECTADO][" + host + "][" + port + "]");
            lblIconPantalla.setIcon(iconOn);
            enviarComandoDesbloqueo();
        } catch (UnknownHostException ex) {
            Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Conexion rechazada por el servidor... IP[" + host + "] PUERTO[" + port + "]");
            lblIconPantalla.setIcon(iconOff);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex1) {
                Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex1);
            }
            //System.out.println("[RECONECTAR]");
            //conectar();
        }
    }

    /**
     * Desconecta el Socket contra el servidor
     */
    public void desconectar() {
        try {
            os.close();
            getSocket().close();
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
    public boolean enviarDatos(String input) {
        if (getSocket() != null && os != null) {
            //System.out.println("Enviar: " + input);
            os.print(input);
            if (os.checkError()) {
                lblIconPantalla.setIcon(iconOff);
                //System.out.println("[RECONECTAR]");
                //conectar();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public void enviarLetra(String letra) {
        if (enviarDatos("" + letra)) {
            /**
             * Comprueba que al final del comando se envie si se envia las
             * letras con una pausa o se envia rapidamente sin pausa a la pantalla
             */
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(SocketPantalla.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("[RECONECTAR]");
            reConectar();
        }
    }

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
