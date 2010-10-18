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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
//import org.jdesktop.jdic.tray.SystemTray;
//import org.jdesktop.jdic.tray.TrayIcon;

/**
 *
 * @author kradac
 */
public class Cliente extends javax.swing.JFrame {

    public static JFrame gui;
    private TrayIcon ti;
    private static LogicaCliente stubCliente;
//    private SystemTray tray = SystemTray.getDefaultSystemTray();

    /** Creates new form Cliente */
    public Cliente() {
        super.setIconImage(new ImageIcon(getClass().getResource("/iconos/kradac_icono.png")).getImage());
        setUndecorated(true);

        initComponents();
        //Tray();
        trayICON();

        posInicial();
        gui = this;
    }

    private void Tray() {

        JPopupMenu menu;
        JMenuItem menuItem;
        //JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        menu = new JPopupMenu("A Menu");


        menuItem = new JMenuItem("A text-only menu item", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");

        menu.add(menuItem);

        menu.addSeparator();

        // a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);

        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);

        menu.add(cbMenuItem);

        // "Quit" menu item
        menu.addSeparator();
        menuItem = new JMenuItem("Quit");

        menu.add(menuItem);

        ImageIcon i = new ImageIcon(getClass().getResource("/iconos/kradac_icono.png"));

        // ti = new TrayIcon(i, "JDIC Tray Icon API Demo - TrayIcon", menu);

        //tray.addTrayIcon(ti);
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
            System.out.println(mostrar.getShortcut());
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
        } else {
            btnLlamar.setEnabled(true);
            btnLlamar.requestFocus();
        }
    }//GEN-LAST:event_btnRellamarActionPerformed

    private void btnLlamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLlamarKeyPressed
        System.out.println("" + evt.getKeyCode());
    }//GEN-LAST:event_btnLlamarKeyPressed

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
//        public static void main(String[] args) {
//        /*if (args.length < 2) {
//            System.out.println("Usage: Echo <host> <port#>");
//            System.exit(1);
//        }*/
//        try {
//            stubCliente = new LogicaCliente();//EJERCICIO: crear una instancia del stub
//            //ss.setHostAndPort(args[0], Integer.parseInt(args[1]));
//            stubCliente.setHostAndPort("localhost", 7);
//            //ss.setHostAndPort("172.16.12.173", 7);
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//            PrintWriter stdOut = new PrintWriter(System.out);
//            String input, output;
//
//            //EJERCICIO: el bucle infinito:
//            while (true) {
//                //EJERCICIO: Leer de teclado
//                input = stdIn.readLine();
//                //EJERCICIO: Invocar el stub
//                output = stubCliente.enviarComando(input);
//                //EJERCICIO: Imprimir por pantalla
//                System.out.println("" + output);
//                stdOut.write(output);
//            }
//        //} catch (UnknownHostException e) {
//        //    System.err.println("Don't know about host: " + args[0]);
//        } catch (IOException e) {
//            System.err.println("I/O failed for connection to: " + args[0]);
//        }
//
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLlamar;
    private javax.swing.JToggleButton btnRellamar;
    // End of variables declaration//GEN-END:variables
}
