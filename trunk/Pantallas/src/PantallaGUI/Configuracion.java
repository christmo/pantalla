/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Configuracion.java
 *
 * Created on 09/10/2010, 12:39:26 PM
 */
package PantallaGUI;

import BaseDatos.BaseDatos;
import PantallaGUI.utilitarios.Utilitarios;
import comunicacion.ComunicacionPantalla;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kradac
 */
public class Configuracion extends javax.swing.JFrame {

    /**
     * Escribe el texto en la caja desde la ventana que recupera los mensajes
     * de la base de datos
     * @param dato
     */
    static void insertarMensaje(String dato) {
        txtTexto.setText(dato);
    }
    /**
     * permite tener todos los botones de las fuentes en un arreglo para permitir
     * tener seleccionado solo un boton de todo el conjunto de 6
     */
    //private ArrayList arrayBotonesFuentes = new ArrayList();
    private MensajesGuardados mg;
    private BaseDatos bd;
    private ImageIcon ic;
    private Properties arcConfig;
    //private CommPantalla comm;

    /** Creates new form Configuracion */
    public Configuracion(BaseDatos db, Properties prop) {
        super.setIconImage(new ImageIcon(getClass().getResource("/iconos/kradac_icono.png")).getImage());
        //bd = new BaseDatos();
        this.bd = db;
        arcConfig = prop;
        initComponents();
        formatearHoraFecha();
        this.setVisible(true);
        cargarNombresPantallas();
    }

    /**
     * Carga los nombres de las pantallas segun esten escritos en el archivo
     * de confiuguracion
     */
    private void cargarNombresPantallas() {
        jcPantalla.addItem(arcConfig.getProperty("nombre_p1"));
        jcPantalla.addItem(arcConfig.getProperty("nombre_p2"));
    }

    /**
     * Formatea con la hora y la fecha los campos de la interfaz
     */
    private void formatearHoraFecha() {
        Date fecha = new GregorianCalendar().getTime();
        SimpleDateFormat sfh = new SimpleDateFormat("HH:mm:ss");
        txtHora.setText(sfh.format(fecha));
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        jxFecha.setFormats(sfd);
        jxFecha.setDate(fecha);
    }

    private void enviarDatosPantalla(String cmd) {
        int idPantalla = jcPantalla.getSelectedIndex() + 1;
        ComunicacionPantalla enlacePantalla = new ComunicacionPantalla(cmd, arcConfig, idPantalla);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnEscribir = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnMasVelocidad = new javax.swing.JButton();
        btnMenosVelocidad = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnFuente = new javax.swing.JButton();
        btnInvertir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jxFecha = new org.jdesktop.swingx.JXDatePicker();
        btnFecha = new javax.swing.JButton();
        btnHora = new javax.swing.JButton();
        txtHora = new javax.swing.JFormattedTextField();
        btnSalir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcPantalla = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuración Pantalla");
        setResizable(false);

        txtTexto.setColumns(20);
        txtTexto.setLineWrap(true);
        txtTexto.setRows(7);
        txtTexto.setTabSize(0);
        txtTexto.setMargin(new java.awt.Insets(5, 5, 5, 5));
        txtTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTextoFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txtTexto);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Texto");

        btnEscribir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/text.png"))); // NOI18N
        btnEscribir.setText("Escribir Pantalla");
        btnEscribir.setToolTipText("Publica en la pantalla el mensaje escrito en la caja de texto...");
        btnEscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscribirActionPerformed(evt);
            }
        });

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/limpiar.png"))); // NOI18N
        btnBorrar.setText("Borrar Pantalla");
        btnBorrar.setToolTipText("Borra el contenido de la pantalla solo deja la fecha y hora...");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Guarda un mensaje...");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/abrir.png"))); // NOI18N
        btnAbrir.setText("Abrir");
        btnAbrir.setToolTipText("Ver mensajes guardados en la base de datos...");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/velocidad.png"))); // NOI18N

        btnMasVelocidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/mas.png"))); // NOI18N
        btnMasVelocidad.setText("Más");
        btnMasVelocidad.setToolTipText("Incrementa la velocidad del paso de los mensajes por la pantalla.\n");
        btnMasVelocidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasVelocidadActionPerformed(evt);
            }
        });

        btnMenosVelocidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/menos.png"))); // NOI18N
        btnMenosVelocidad.setText("Menos");
        btnMenosVelocidad.setToolTipText("Disminuye la velocidad del paso de mensajes por la pantalla.");
        btnMenosVelocidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosVelocidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMenosVelocidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMasVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnMasVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(btnMenosVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Velocidad");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnFuente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fuente.png"))); // NOI18N
        btnFuente.setText("Cambiar Formato");
        btnFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuenteActionPerformed(evt);
            }
        });

        btnInvertir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/color.png"))); // NOI18N
        btnInvertir.setText("Invertir Colores");
        btnInvertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvertirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnInvertir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(btnFuente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFuente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInvertir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Formato de la letra");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tiempo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calendario.png"))); // NOI18N
        btnFecha.setText("Fecha");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });

        btnHora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reloj.png"))); // NOI18N
        btnHora.setText("Hora");
        btnHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoraActionPerformed(evt);
            }
        });

        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHora.setToolTipText("<html>Ingresar hora en formato <b>hora:minutos:segundos</b>,<br/>\no simplemente <b>HoraMinutosSegundos</b> sin los \":\" ni espacios...</html>");
        txtHora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoraFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHora, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(btnFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHora)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFecha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 27));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cartel Pasa Mensajes Modelo - KER001");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Pantalla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEscribir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(10, 10, 10)
                        .addComponent(btnAbrir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEscribir)
                    .addComponent(btnBorrar)
                    .addComponent(btnGuardar)
                    .addComponent(btnAbrir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(jcPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtTextoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTextoFocusLost
        //txtTexto.setText(txtTexto.getText().toUpperCase());
    }//GEN-LAST:event_txtTextoFocusLost

    private void btnHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoraActionPerformed
        String hora = txtHora.getText();
        if (!hora.equals("")) {
            //<TIME$\r11:07:51\r\r
            String comando = "t" + hora + "\r";
            enviarDatosPantalla(comando);
            System.out.println("Enviar: " + comando);
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una hora...", "Error...", 0);
        }
    }//GEN-LAST:event_btnHoraActionPerformed

    private void txtHoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraFocusLost
        String hora = txtHora.getText();
        txtHora.setText(Utilitarios.formatearHora(hora));
    }//GEN-LAST:event_txtHoraFocusLost

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String mensaje = txtTexto.getText();
        if (!mensaje.equals("")) {
            if (bd.guardarMensajePantalla(Utilitarios.quitarEnterTexto(mensaje), "ACT", "GUARDADO")) {
                ic = new ImageIcon(getClass().getResource("/iconos/correcto.png"));
                JOptionPane.showMessageDialog(this,
                        "Mensaje guardado correctamente...",
                        "OK...",
                        JOptionPane.INFORMATION_MESSAGE,
                        ic);
                txtTexto.setText("");
            } else {
                ic = new ImageIcon(getClass().getResource("/iconos/error.png"));
                JOptionPane.showMessageDialog(this,
                        "No se pudo guardar el mensaje...",
                        "Error...",
                        JOptionPane.ERROR_MESSAGE,
                        ic);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (mg == null) {
            mg = new MensajesGuardados(bd);
        } else {
            mg.MostrarVentana();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // BORRAR
        String comando = "2\r";
        bd.borrarUltimoMensajeGuardadoPantalla();
        enviarDatosPantalla(comando);
        txtTexto.setText("");
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnEscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscribirActionPerformed
        if (!txtTexto.getText().equals("")) {
            String txt = Utilitarios.quitarEnterTexto(txtTexto.getText());
            //GRABAR UN MENSAJE
            String comandoBorrar = "2\r";
            bd.borrarUltimoMensajeGuardadoPantalla();
            String comandoEscribir = "1   " + txt + "       \r";
            bd.guardarMensajePantalla(txt, "INA", "GUARDADO");
            enviarDatosPantalla(comandoBorrar + "&%" + comandoEscribir);
            txtTexto.setText("");
        }
    }//GEN-LAST:event_btnEscribirActionPerformed

    private void btnMasVelocidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasVelocidadActionPerformed
        // <MENSv
        String comando = "V";
        enviarDatosPantalla(comando);
    }//GEN-LAST:event_btnMasVelocidadActionPerformed

    private void btnMenosVelocidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosVelocidadActionPerformed
        // <MENSV
        String comando = "v";
        enviarDatosPantalla(comando);
    }//GEN-LAST:event_btnMenosVelocidadActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        // <DATE$\r10/09/10\r
        Date fecha = jxFecha.getDate();
        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yy");
        String comando = "d" + sfd.format(fecha) + "\r";
        enviarDatosPantalla(comando);
    }//GEN-LAST:event_btnFechaActionPerformed

    private void btnInvertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvertirActionPerformed
        String comando = "I";
        enviarDatosPantalla(comando);
    }//GEN-LAST:event_btnInvertirActionPerformed

    private void btnFuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuenteActionPerformed
        String comando = "T\r";
        enviarDatosPantalla(comando);
    }//GEN-LAST:event_btnFuenteActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnEscribir;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnFuente;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHora;
    private javax.swing.JButton btnInvertir;
    private javax.swing.JButton btnMasVelocidad;
    private javax.swing.JButton btnMenosVelocidad;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcPantalla;
    private org.jdesktop.swingx.JXDatePicker jxFecha;
    private javax.swing.JFormattedTextField txtHora;
    private static javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables
}
