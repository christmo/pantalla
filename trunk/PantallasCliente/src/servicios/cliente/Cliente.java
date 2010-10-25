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
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;


/**
 *
 * @author kradac
 */
public class Cliente extends javax.swing.JFrame {

    public static JFrame gui;
    private static LogicaCliente stubCliente;
    private PrintWriter stdOut = new PrintWriter(System.out);
    private String output;
    private ResourceBundle rb;

    /** Creates new form Cliente */
    public Cliente() {
        super.setIconImage(new ImageIcon(getClass().getResource("/iconos/kradac_icono.png")).getImage());
        setUndecorated(true);

        initComponents();

        trayICON();

        posInicial();
        gui = this;

        //File jar = new File(System.getProperty("java.class.path"));
        //String srcDirProyecto = jar.getPath().substring(0, jar.getPath().length() - jar.getName().length());

        rb = ResourceBundle.getBundle("servicios.cliente.configuracion");
        ConectarServer();
    }

    private void ConectarServer() {
        stubCliente = new LogicaCliente();//EJERCICIO: crear una instancia del stub
        String host = rb.getString("host");
        int puerto = Integer.parseInt(rb.getString("puerto"));
        stubCliente.setHostAndPort(host, puerto);
    }

    private void trayICON() {
        final TrayIcon trayIcon;

        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/iconos/kradac_icono.png"));

            MouseListener mouseListener = new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");

                }

                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");
                }

                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");
                }

                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");
                }

                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");
                }
            };

            ActionListener exitListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("Saliendo...");
                    System.exit(0);
                }
            };

            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Salir");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);


            MenuItem mostrar = new MenuItem("Mostrar");
            ActionListener otro = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("Mostrar...");
                    gui.dispose();
                    gui.setVisible(true);
                }
            };
            mostrar.addActionListener(otro);
            mostrar.setShortcut(new MenuShortcut(KeyEvent.VK_F12, false));
            
            popup.add(mostrar);

            trayIcon = new TrayIcon(image, "Sistema de Turnos Activado...", popup);

            ActionListener actionListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("Action Event",
                            "An Action Event Has Been Performed!",
                            TrayIcon.MessageType.INFO);
                }
            };

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
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        btnLlamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/llamar.png"))); // NOI18N
        btnLlamar.setToolTipText("Llama a un nuevo servicios.cliente...");
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

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        System.out.println("focus lost");
    }//GEN-LAST:event_formFocusLost

    private void btnRellamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRellamarActionPerformed
        if (btnRellamar.isSelected()) {
            btnLlamar.setEnabled(false);
            btnLlamar.requestFocus();
            accionesBotonesCliente("INACTIVO");
        } else {
            btnLlamar.setEnabled(true);
            btnLlamar.requestFocus();
        }
    }//GEN-LAST:event_btnRellamarActionPerformed

    private void btnLlamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLlamarKeyPressed
        System.out.println("" + evt.getKeyCode());
        if (evt.getKeyCode() == 123) {
            accionesBotonesCliente("ACTIVO");
        }
    }//GEN-LAST:event_btnLlamarKeyPressed

    private void btnLlamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLlamarActionPerformed
        accionesBotonesCliente("ACTIVO");
    }//GEN-LAST:event_btnLlamarActionPerformed

    private void accionesBotonesCliente(String estado) {
        try {
            String caja = rb.getString("caja");
            String dir = rb.getString("dir");
            String cmd = caja + "%" + estado + "%" + dir;
            output = stubCliente.enviarComando(cmd);
            stdOut.write(output);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
                    //UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
                    //UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel");
                    //UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel");
                    //UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel");
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel");
                } catch (Exception e) {
                    System.out.println("Problemas al cargar Temas Substance");
                }
                new Cliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLlamar;
    private javax.swing.JToggleButton btnRellamar;
    // End of variables declaration//GEN-END:variables
}
