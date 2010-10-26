
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.cliente.LogicaCliente;

public class Keylogger extends Thread {
    //Usaremos un Hilo

    public static final int DELAY = 1500;
    private boolean activated = true;
    private char value;
    private ResourceBundle rb;

    private native char get();

    static {
        System.loadLibrary("Keylogger");
        //Preparamos la Carga de la DLL
    }

    @Override
    public synchronized void run() {
        rb = ResourceBundle.getBundle("servicios.cliente.configuracion");
        String host = rb.getString("host");
        int puerto = Integer.parseInt(rb.getString("puerto"));
        while (activated) {
            try {
                value = get();
                //Optenemos la Tecla Precionada
                if ((int) value == 123) {
                    //JOptionPane.showMessageDialog(null, "Heeee", "Si vale", 2);
                    LogicaCliente lc = new LogicaCliente(host, puerto);
                    try {
                        String caja = rb.getString("caja");
                        String dir = rb.getString("dir");
                        String cmd = caja + "%" + "ACTIVO" + "%" + dir;
                        lc.enviarComando(cmd);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println((int) value); //La Mostramos en la Salida Estandar
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
