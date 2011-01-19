/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cliente.java
 *
 * Created on 14/10/2010, 10:02:15 AM
 */
package servicios.cliente;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import utilitarios.Utilitarios;

/**
 *
 * @author kradac
 */
public class Cliente extends javax.swing.JDialog {

    public static JDialog gui;
    private static LogicaCliente stubCliente;
    private PrintWriter stdOut = new PrintWriter(System.out);
    private String output;
    private JPopupMenu popup = new JPopupMenu();
    private TrayIcon trayIcon;
    private Properties prop;
    /**
     * Mantiene el estado del sistema cliente para saber si se puede llamar clientes
     * con la tecla F12, por defecto al ejecutar el cliente va estar en true
     */
    public static boolean boolEstadoTurnos = true;

    /** Creates new form Cliente */
    public Cliente() {
        super.setIconImage(new ImageIcon(getClass().getResource("/iconos/kradac_icono.png")).getImage());
        setUndecorated(true);

        initComponents();
        iconoBarraTarreas();
        posInicial();
        gui = this;

        prop = Utilitarios.obtenerArchivoPropiedades("configuracion.properties");
        ConectarServer();
    }

    private void ConectarServer() {
        String host = prop.getProperty("host");
        int puerto = Integer.parseInt(prop.getProperty("puerto"));
        stubCliente = new LogicaCliente(host, puerto);//EJERCICIO: crear una instancia del stub
    }

    /**
     * Poner icono en la barra de tareas para que pueda correr en background
     */
    private void iconoBarraTarreas() {
        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/iconos/kradac_icono.png"));

            MouseListener mouseListener = new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    //System.out.println("Tray Icon - Mouse clicked!");
                }

                public void mouseEntered(MouseEvent e) {
                    //System.out.println("Tray Icon - Mouse entered!");
                }

                public void mouseExited(MouseEvent e) {
                    //System.out.println("Tray Icon - Mouse exited!");
                }

                public void mousePressed(MouseEvent e) {
                    if (gui.isVisible()) {
                        gui.setVisible(false);
                    } else {
                        gui.setVisible(true);
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    //System.out.println("Tray Icon - Mouse released!");
                }
            };

            //PopupMenu popup = new PopupMenu();
            //MenuItem mostrar = new MenuItem("Mostrar");
            JMenuItem jmMostrar = new JMenuItem("Mostar");
            ActionListener mostrarVentana = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("Mostrar...");
                    //gui.dispose();
                    gui.setVisible(true);
                }
            };
            //jmMostrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
            jmMostrar.addActionListener(mostrarVentana);
            popup.add(jmMostrar);

            JMenuItem jmOcultar = new JMenuItem("Ocultar");
            ActionListener ocultarVentana = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    gui.setVisible(false);
                }
            };
            //jmMostrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
            jmOcultar.addActionListener(ocultarVentana);
            popup.add(jmOcultar);

            //MenuItem defaultItem = new MenuItem("Salir");
            JMenuItem jmSalir = new JMenuItem("Salir");
            ActionListener exitListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("Saliendo...");
                    System.exit(0);
                }
            };
            //jmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.CTRL_MASK));
            jmSalir.addActionListener(exitListener);
            popup.add(jmSalir);

            //trayIcon = new TrayIcon(image, "Sistema de Turnos Activado...", popup);
            trayIcon = new TrayIcon(image, "Sistema de Turnos Activado...", null);

            ActionListener actionListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    gui.dispose();
                    gui.setVisible(true);
                    trayIcon.displayMessage("Sistema de Turnos...",
                            "Activo...",
                            TrayIcon.MessageType.INFO);
                }
            };

            trayIcon.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popup.setLocation(e.getX(), e.getY());
                        popup.setInvoker(popup);
                        popup.setVisible(true);
                    }
                }
            });
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);


            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        } else {
            //  System Tray is not supported
        }
    }

    private void posInicial() {
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
        int vAncho = this.getWidth();
        int vAlto = this.getHeight();
        this.setLocation((ancho - vAncho), (alto - vAlto) - 50);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLlamar = new javax.swing.JButton();
        btnRellamar = new javax.swing.JToggleButton();

        setTitle("Turnos");
        setAlwaysOnTop(true);
        setFocusable(false);
        setResizable(false);

        btnLlamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/llamar.png"))); // NOI18N
        btnLlamar.setToolTipText("Llama a un nuevo servicios.cliente...");
        btnLlamar.setFocusable(false);
        btnLlamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLlamarActionPerformed(evt);
            }
        });
        btnLlamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLlamarKeyPressed(evt);
            }
        });

        btnRellamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rellamar.png"))); // NOI18N
        btnRellamar.setToolTipText("Habilita y deshabilita el llamado de turnos...");
        btnRellamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRellamarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLlamar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRellamar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRellamar, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(btnLlamar, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRellamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRellamarActionPerformed
        if (btnRellamar.isSelected()) {
            btnLlamar.setEnabled(false);
            btnLlamar.requestFocus();
            accionesBotonesCliente("INACTIVO");
            boolEstadoTurnos = false;
        } else {
            boolEstadoTurnos = true;
            btnLlamar.setEnabled(true);
            btnLlamar.requestFocus();
        }
    }//GEN-LAST:event_btnRellamarActionPerformed

    private void btnLlamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLlamarKeyPressed

    }//GEN-LAST:event_btnLlamarKeyPressed

    private void btnLlamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLlamarActionPerformed
        accionesBotonesCliente("ACTIVO");
        btnLlamar.setEnabled(false);
        pausa();
    }//GEN-LAST:event_btnLlamarActionPerformed

    /**
     * Permite hacer una espera de 1 segundo entre el cliente y el servidor para
     * que se envien los datos y no se permita hacer una llamada a un cliente en
     * el mismo segundo
     */
    public void pausa() {
        Thread a = new Thread(new Runnable() {

            public void run() {
                try {
                    boolEstadoTurnos = false;
                    Thread.sleep(1000);
                    boolEstadoTurnos = true;
                    btnLlamar.setEnabled(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        a.start();
    }

    private void accionesBotonesCliente(String estado) {
        try {
            String caja = prop.getProperty("caja");
            String dir = prop.getProperty("dir");
            String usuario = System.getProperty("user.name");
            String cmd = caja + "%" + estado + "%" + dir + "%" + usuario;
            output = stubCliente.enviarComando(cmd);
            stdOut.write(output);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "No se puede conectar con el servidor de turnos...", "Error...", 0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel");
                } catch (Exception e) {
                    System.out.println("Problemas al cargar Temas Substance");
                }
                new Cliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnLlamar;
    private javax.swing.JToggleButton btnRellamar;
    // End of variables declaration//GEN-END:variables
}
