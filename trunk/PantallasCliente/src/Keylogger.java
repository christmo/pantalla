
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.cliente.Cliente;
import servicios.cliente.LogicaCliente;

public class Keylogger extends Thread {

    public static final int DELAY = 1000;
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
                //Optenemos la Tecla Precionada F12
                if ((int) value == 123) {
                    //JOptionPane.showMessageDialog(null, "Heeee", "Si vale", 2);
                    if (Cliente.boolEstadoTurnos) {
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
                }
                //System.out.println((int) value); //La Mostramos en la Salida Estandar
                Cliente.btnLlamar.setEnabled(false);
                Thread.sleep(DELAY);
                Cliente.btnLlamar.setEnabled(true);
            } catch (InterruptedException e) {
            }
        }
    }
}
