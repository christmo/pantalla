/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion.comm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

/**
 *
 * @author christmo
 */
public class CommPantalla extends Thread {

    private CommPortIdentifier id_Puerto;
    private SerialPort sPuerto;
    public Enumeration listaPuertos;
    private String cmd;
    private ResourceBundle rb;
    private OutputStream os;

//    public CommPantalla() {
//    }
    /**
     * Abrir el puerto con configuraciones por defecto
     * @param puerto
     */
    public CommPantalla(String puerto, String cmd) {
        this.cmd = cmd;
        if (!AbrirPuerto(puerto)) {
            System.err.println("No se pudo abrir el puerto");
        }
        if (!setParametros(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE)) {
            System.err.println("No se pudo setear puerto");
            CerrarPuerto();
        }
    }

    public CommPantalla() {
        rb = ResourceBundle.getBundle("BaseDatos.configsystem");
        String puerto = rb.getString("comm");
        System.out.println("Puerto: " + puerto);
        if (!puerto.equals("0")) {
            if (!AbrirPuerto(puerto)) {
                System.err.println("No se pudo abrir el puerto");
            }
            if (!setParametros(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE)) {
                System.err.println("No se pudo setear puerto");
                CerrarPuerto();
            }
        } else {
            System.out.println("Comm es 0");
        }
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
        String[] comandos = cmd.split("&%");
        for (int i = 0; i < comandos.length; i++) {
            for (char l : comandos[i].toCharArray()) {
                //System.out.println("" + l);
                enviarDatos("" + l);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        CerrarPuerto();
        System.out.println("Comando: " + cmd);
    }

    /**
     * Abre el puerto de comunicación serial, se bloquea para su uso por 60s
     * @param comm
     * @return boolean -> true si no hay problemas al abrir el puerto
     */
    private boolean AbrirPuerto(String comm) {
        try {
            id_Puerto = CommPortIdentifier.getPortIdentifier(comm);
            sPuerto = (SerialPort) id_Puerto.open("PantallasKradac", 5000); //tiempo de bloqueo 1m
            return true;
        } catch (PortInUseException ex) {
            //Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Puerto del COMM está en uso por otra apicación...\nModificar los paramatros de inicio.", "error", 0);
            System.err.println("Cerrar Apicación - puerto en uso o no hay puerto serial disponible...");
            //CerrarPuerto();
            //System.exit(0);
        } catch (NoSuchPortException ex) {
            //JOptionPane.showMessageDialog(null, "No se ha cargado el driver COMM de java:\n\n" + ex + "\n\nNo se puede cargar la aplicación...", "error", 0);
            CargarDriverCOMM();
            Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(0);
        }
        CerrarPuerto();
        return false;
    }
    /*
     * Librerias para windows para leer el puerto comm desde java
     */
    String strWin32 = "win32com.dll";
    String strComm = "comm.jar";
    String strProp = "javax.comm.properties";

    /**
     * Copia el driver para que pueda java leer el puerto com en windows
     */
    public void CargarDriverCOMM() {

        String dirLib = System.getProperty("java.home") + System.getProperty("file.separator") + "lib/";
        String dirBin = System.getProperty("java.home") + System.getProperty("file.separator") + "bin/";
        //System.out.println(""+dirLib);
        //System.out.println(""+dirBin);
        //System.out.println(""+System.getProperty("user.dir")+System.getProperty("file.separator")+"lib");

        File jar = new File(System.getProperty("java.class.path"));
        String srcDirCOMM = jar.getPath().substring(0, jar.getPath().length() - jar.getName().length()) + "comm/";

        System.out.println("PATH: " + srcDirCOMM + strWin32);
        System.out.println("PATH: " + srcDirCOMM + strComm);
        System.out.println("PATH: " + srcDirCOMM + strProp);

        File win32 = new File(srcDirCOMM + strWin32);
        File comm = new File(srcDirCOMM + strComm);
        File com_prop = new File(srcDirCOMM + strProp);



        /*if (srcDir.isDirectory()) {
        if (!dstDir.exists()) {
        dstDir.mkdir();
        }
        String[] children = srcDir.list();
        for (int i=0; i<children.length; i++) {
        copyDirectory(new File(srcDir, children[i]),
        new File(dstDir, children[i]));
        }
        } else {
        copy(srcDir, dstDir);
        }*/


    }

    /**
     * Configuración de parametros para el puerto
     * @param baudRate
     * @param dataBits
     * @param stopBits
     * @param paridad
     * @return boolean
     */
    private boolean setParametros(int baudRate, int dataBits, int stopBits, int paridad) {
        try {
            sPuerto.setSerialPortParams(baudRate, dataBits, stopBits, paridad);
            return true;
        } catch (UnsupportedCommOperationException ex) {
            //Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cerrar Aplicación");
            System.exit(0);
        } catch (NullPointerException ex) {
        }
        return false;
    }

    /**
     * Permite hacer la lectura de los datos que se envien desde el puerto serie
     * @return InputStream
     */
    public InputStream recibirDatos() {
        try {
            return sPuerto.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Permite escuchar lo que llega por el puerto serial
     * @return String
     */
    public String leerDatos() {
        InputStream is = null;
        try {
            is = sPuerto.getInputStream();
            return "" + ((char) is.read());
        } catch (IOException ex) {
            Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }/*catch(IllegalStateException ex){
        System.err.println("Error -> Puerto Cerrado...");
        }*/
        return null;
    }

    public int leerDatosCode() {
        InputStream is = null;
        try {
            is = sPuerto.getInputStream();
            return is.read();
        } catch (IOException ex) {
            Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }/*catch(IllegalStateException ex){
        System.err.println("Error -> Puerto Cerrado...");
        }*/ catch (NullPointerException nex) {
            Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, nex);
        }
        return 0;
    }

    /**
     * Permite abrir un canal de comunicación para enviar datos por el puerto abierto
     */
    public void enviarDatos(String mensaje) {

        try {
            os = sPuerto.getOutputStream();
            //System.out.println("Enviar: " + mensaje);
            os.write(mensaje.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
        } catch (IllegalStateException ex) {
        }
    }

    /**
     * Propietario que esta utilizando el puerto
     * @return String
     */
    public String getPropietario() {
        return id_Puerto.getCurrentOwner();
    }

    /**
     * Ontiene el estado de ring del telefono
     * @return boolean
     */
    public boolean getEstadoRing() {
        return sPuerto.isRI();
    }

    /**
     * Notificar el estado de ring true lo habilita
     * @param ri
     */
    public void notificarEstadoRing(boolean ri) {
        sPuerto.notifyOnRingIndicator(ri);
    }

    /**
     * Cerrar el puerto abierto
     */
    public final boolean CerrarPuerto() {
        try {
            os.close();
            sPuerto.close();
            System.out.println("Cerrar puerto COMM...");
        } catch (IOException ex) {
            Logger.getLogger(CommPantalla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
        }
        return true;
    }
}
