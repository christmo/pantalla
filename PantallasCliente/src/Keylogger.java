
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.cliente.Cliente;
import servicios.cliente.LogicaCliente;
import utilitarios.Utilitarios;

public class Keylogger extends Thread {

    public static final int DELAY = 1000;
    private boolean activated = true;
    private char value;
    private Properties prop;
    private int priTcla1;
    private int segTcla1;
    private int tecla;
    private boolean shift;
    private boolean ctrl;
    int i = 0;

    private native char get();

    static {
        System.loadLibrary("Keylogger");
        //Preparamos la Carga de la DLL
    }

    @Override
    public synchronized void run() {
        prop = Utilitarios.obtenerArchivoPropiedades("configuracion.properties");
        String host = prop.getProperty("host");
        int puerto = Integer.parseInt(prop.getProperty("puerto"));
        int teclaConfig = 0;
        try {
            teclaConfig = Integer.parseInt(prop.getProperty("tecla"));
        } catch (NumberFormatException ex) {
        }
        while (activated) {
            try {
                value = get();
                //Optenemos la Tecla Precionada F12
                if ((int) value == teclaConfig) {
                    if (Cliente.boolEstadoTurnos) {
                        LogicaCliente lc = new LogicaCliente(host, puerto);
                        try {
                            /**
                             * NOTA: Todo cambio que se haga aqui se tiene
                             * que replicar en la clase Keylogger.java que
                             * es la que controla el teclado, aqui solo clic
                             */
                            String caja = prop.getProperty("caja");
                            String dir = prop.getProperty("dir");
                            String usuario = System.getProperty("user.name");
                            String cmd = caja + "%" + "ACTIVO" + "%" + dir + "%" + usuario;
                            lc.enviarComando(cmd);
                            Cliente.btnLlamar.setEnabled(false);
                            Thread.sleep(DELAY);
                            Cliente.btnLlamar.setEnabled(true);
                        } catch (RemoteException ex) {
                            Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                //System.out.println("" + (int) value);

//                distincionTeclas((int) value);
//
//
//                if (ctrl) {
//                    System.out.println("--ok--");
//                    if (tecla == 32) {
//                        System.out.println("-----");
//                        System.out.println("Listo:" + tecla);
//                        System.out.println("-----");
//                        i = 0;
//                    }
//                    if (i == 1) {
//                        shift = false;
//                        ctrl = false;
//                        i = 0;
//                    }
//                    i++;
//                } else {
//                    i = 0;
//                }

            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Reconoce si se preciona control o shift
     * @param value
     */
    private void distincionTeclas(int value) {
        /**
         * Distingue el inicio de las teclas especiales
         */
        if (value == 16) { // Shift
            priTcla1 = (int) value;
        } else if (value == 17) { // Control
            segTcla1 = (int) value;
        } else {
            tecla = (int) value;
        }

        /**
         * Si el primer caracter de la tecla especial es 16 y el siguiente es
         * 160 entonces se presiono la tecla SHIFT
         */
        if (priTcla1 == 16) {
            if (value == 160) {
                //shift izq
                shift = true;
            }
        } else {
            shift = false;
        }

        /**
         * Si el primer caracter de la tecla especial es 17 y el siguiente es
         * 162 entonces se presiono la tecla CONTROL
         */
        if (segTcla1 == 17) {
            if (value == 162) {
                //ctrl izq
                ctrl = true;
            }
        } else {
            ctrl = false;
        }
    }

    public static void main(String[] args) {
        new Keylogger().start();
    }
}
