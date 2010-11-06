/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Reportes.java
 *
 * Created on 27/10/2010, 11:25:16 AM
 */
package PantallaGUI.reportes;

import BaseDatos.BaseDatos;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author kradac
 */
public class Reportes extends javax.swing.JFrame {

    private BaseDatos bd;

    /** Creates new form Reportes */
    public Reportes() {
        super.setIconImage(new ImageIcon(getClass().getResource("/iconos/kradac_icono.png")).getImage());
        initComponents();
        bd = new BaseDatos();
        configuracionInicial();
    }

    private void configuracionInicial() {
        cargarCajasCombo();
        activarTipoReporte(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jxDia.setFormats(sdf);
        jxDia.setDate(GregorianCalendar.getInstance().getTime());
        this.setVisible(true);
    }

    /**
     * Obtiene las cajas de la base de datos y rellena el combobox de la gui
     */
    private void cargarCajasCombo() {
        String[] cajas = bd.obtenerNumeroCajas();
        for (int i = 0; i < cajas.length; i++) {
            jcNumeroCaja.addItem(cajas[i]);
        }

        String[] anios = bd.obtenerAniosBase();
        for (int i = 0; i < anios.length; i++) {
            jcAnio.addItem(anios[i]);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPeriodoReporte = new javax.swing.ButtonGroup();
        rgTipoReporte = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcNumeroCaja = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jrDia = new javax.swing.JRadioButton();
        jrMes = new javax.swing.JRadioButton();
        jrAnual = new javax.swing.JRadioButton();
        jrTodo = new javax.swing.JRadioButton();
        jpTipoReporte = new javax.swing.JPanel();
        jxDia = new org.jdesktop.swingx.JXDatePicker();
        jcMes = new javax.swing.JComboBox();
        jcAnio = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jrNumeroClientesCaja = new javax.swing.JRadioButton();
        jrTotalClientesTodasLasCajas = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reportes");

        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/grafico.png"))); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel2.setText("Número de caja:");

        jLabel3.setText("Periodo de reporte:");

        bgPeriodoReporte.add(jrDia);
        jrDia.setSelected(true);
        jrDia.setText("Día");
        jrDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrDiaActionPerformed(evt);
            }
        });

        bgPeriodoReporte.add(jrMes);
        jrMes.setText("Mes");
        jrMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrMesActionPerformed(evt);
            }
        });

        bgPeriodoReporte.add(jrAnual);
        jrAnual.setText("Año");
        jrAnual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrAnualActionPerformed(evt);
            }
        });

        bgPeriodoReporte.add(jrTodo);
        jrTodo.setText("Todo");

        jcMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        javax.swing.GroupLayout jpTipoReporteLayout = new javax.swing.GroupLayout(jpTipoReporte);
        jpTipoReporte.setLayout(jpTipoReporteLayout);
        jpTipoReporteLayout.setHorizontalGroup(
            jpTipoReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoReporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jxDia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcMes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTipoReporteLayout.setVerticalGroup(
            jpTipoReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoReporteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTipoReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Tipos de reporte:");

        rgTipoReporte.add(jrNumeroClientesCaja);
        jrNumeroClientesCaja.setSelected(true);
        jrNumeroClientesCaja.setText("Número de clientes por caja");
        jrNumeroClientesCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrNumeroClientesCajaActionPerformed(evt);
            }
        });

        rgTipoReporte.add(jrTotalClientesTodasLasCajas);
        jrTotalClientesTodasLasCajas.setText("Total de clientes de todas las cajas");
        jrTotalClientesTodasLasCajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrTotalClientesTodasLasCajasActionPerformed(evt);
            }
        });

        rgTipoReporte.add(jRadioButton4);
        jRadioButton4.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrTotalClientesTodasLasCajas)
                            .addComponent(jrNumeroClientesCaja)
                            .addComponent(jRadioButton4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcNumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jrDia)
                                .addGap(18, 18, 18)
                                .addComponent(jrMes)
                                .addGap(18, 18, 18)
                                .addComponent(jrAnual)
                                .addGap(18, 18, 18)
                                .addComponent(jrTodo)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jrNumeroClientesCaja))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrTotalClientesTodasLasCajas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcNumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jrDia)
                    .addComponent(jrMes)
                    .addComponent(jrAnual)
                    .addComponent(jrTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                        .addComponent(btnGenerar))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerar)
                    .addComponent(btnSalir))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-690)/2, (screenSize.height-506)/2, 690, 506);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        bd.cerrarConexionBase();
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jrDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrDiaActionPerformed
        activarTipoReporte(1);
    }//GEN-LAST:event_jrDiaActionPerformed

    private void jrMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrMesActionPerformed
        activarTipoReporte(2);
    }//GEN-LAST:event_jrMesActionPerformed

    private void jrAnualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrAnualActionPerformed
        activarTipoReporte(3);
    }//GEN-LAST:event_jrAnualActionPerformed

    private void jrTotalClientesTodasLasCajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrTotalClientesTodasLasCajasActionPerformed
        jcNumeroCaja.setEnabled(false);
    }//GEN-LAST:event_jrTotalClientesTodasLasCajasActionPerformed

    private void jrNumeroClientesCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrNumeroClientesCajaActionPerformed
        jcNumeroCaja.setEnabled(true);
    }//GEN-LAST:event_jrNumeroClientesCajaActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if (jrNumeroClientesCaja.isSelected()) {
            GenerarReporte(0);
        } else {
            if (jrTotalClientesTodasLasCajas.isSelected()) {
                GenerarReporte(1);
            } else {
                /*if (jrNumeroClientesCaja.isSelected()) {
                    GenerarReporte(2);
                } else {
                }*/
            }
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    /**
     * Activa las cajas de informacion para seleccionar de los dias, meses o años
     * @param tipo
     */
    private void activarTipoReporte(int tipo) {
        switch (tipo) {
            case 1:
                jxDia.setVisible(true);
                jcMes.setVisible(false);
                jcAnio.setVisible(false);
                break;
            case 2:
                jxDia.setVisible(false);
                jcMes.setVisible(true);
                jcAnio.setVisible(false);
                break;
            case 3:
                jxDia.setVisible(false);
                jcMes.setVisible(false);
                jcAnio.setVisible(true);
                break;
            case 4:
                jxDia.setVisible(false);
                jcMes.setVisible(false);
                jcAnio.setVisible(false);
                break;

        }
        jpTipoReporte.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

    public void run() {
    new Reportes().setVisible(true);
    }
    });
    }*/
    /**
     * Obtiene los datos de los estados del taxi
     * @return HashMap
     */
    private HashMap getDatosReporte() {
        HashMap map = new HashMap();
        map.put("todo", "true");

        return map;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgPeriodoReporte;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JComboBox jcAnio;
    private javax.swing.JComboBox jcMes;
    private javax.swing.JComboBox jcNumeroCaja;
    private javax.swing.JPanel jpTipoReporte;
    private javax.swing.JRadioButton jrAnual;
    private javax.swing.JRadioButton jrDia;
    private javax.swing.JRadioButton jrMes;
    private javax.swing.JRadioButton jrNumeroClientesCaja;
    private javax.swing.JRadioButton jrTodo;
    private javax.swing.JRadioButton jrTotalClientesTodasLasCajas;
    private org.jdesktop.swingx.JXDatePicker jxDia;
    private javax.swing.ButtonGroup rgTipoReporte;
    // End of variables declaration//GEN-END:variables

    private void GenerarReporte(int idx) {
        switch (idx) {
            case 1:
                GenerarReporteTotalesCajas totalesCajas = new GenerarReporteTotalesCajas(bd.obtenerConexionBaseDatos(), getDatosReporte());
                totalesCajas.Generar();
                break;
        }
    }
}
