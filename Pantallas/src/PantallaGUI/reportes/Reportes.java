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
//import BaseDatos.ConexionBase;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kradac
 */
public class Reportes extends javax.swing.JFrame {

    private BaseDatos bd;

    /** Creates new form Reportes */
    public Reportes(BaseDatos db) {
        super.setIconImage(new ImageIcon(getClass().getResource("/iconos/kradac_icono.png")).getImage());
        initComponents();
        //bd = new BaseDatos();
        bd = db;
        configuracionInicial();
    }

    private void configuracionInicial() {
        cargarCajasCombo();
        activarTipoReporte(4);
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
        jrNumeroClientesHora = new javax.swing.JRadioButton();
        jrNumeroClientesDias = new javax.swing.JRadioButton();
        jrNumeroClientesMes = new javax.swing.JRadioButton();
        jrTiemposAtencion = new javax.swing.JRadioButton();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Número de caja:");

        jLabel3.setText("Periodo de reporte:");

        bgPeriodoReporte.add(jrDia);
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
        jrTodo.setSelected(true);
        jrTodo.setText("Todo");
        jrTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrTodoActionPerformed(evt);
            }
        });

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

        rgTipoReporte.add(jrNumeroClientesHora);
        jrNumeroClientesHora.setText("Número de clientes por hora");
        jrNumeroClientesHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrNumeroClientesHoraActionPerformed(evt);
            }
        });

        rgTipoReporte.add(jrNumeroClientesDias);
        jrNumeroClientesDias.setText("Número de clientes por día");
        jrNumeroClientesDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrNumeroClientesDiasActionPerformed(evt);
            }
        });

        rgTipoReporte.add(jrNumeroClientesMes);
        jrNumeroClientesMes.setText("Número de clientes por mes");
        jrNumeroClientesMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrNumeroClientesMesActionPerformed(evt);
            }
        });

        rgTipoReporte.add(jrTiemposAtencion);
        jrTiemposAtencion.setText("Tiempos de atención por caja");
        jrTiemposAtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrTiemposAtencionActionPerformed(evt);
            }
        });

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
                            .addComponent(jrNumeroClientesHora)
                            .addComponent(jrNumeroClientesDias)
                            .addComponent(jrNumeroClientesMes)
                            .addComponent(jrTiemposAtencion)))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrMes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrAnual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrTodo)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jrNumeroClientesHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrNumeroClientesDias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrNumeroClientesMes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrTiemposAtencion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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
                .addGap(39, 39, 39))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
                        .addComponent(btnGenerar))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerar)
                    .addComponent(btnSalir))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-650)/2, (screenSize.height-476)/2, 650, 476);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        //bd.cerrarConexionBase();
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
        ocultarPeriodosTiempo(5, false);
    }//GEN-LAST:event_jrTotalClientesTodasLasCajasActionPerformed

    private void jrNumeroClientesCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrNumeroClientesCajaActionPerformed
        jcNumeroCaja.setEnabled(true);
        ocultarPeriodosTiempo(5, false);
    }//GEN-LAST:event_jrNumeroClientesCajaActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if (jrNumeroClientesCaja.isSelected()) {
            GenerarReporte(0);
        } else {
            if (jrTotalClientesTodasLasCajas.isSelected()) {
                GenerarReporte(1);
            } else {
                if (jrNumeroClientesHora.isSelected()) {
                    GenerarReporte(2);
                } else {
                    if (jrNumeroClientesDias.isSelected()) {
                        GenerarReporte(3);
                    } else {
                        if (jrNumeroClientesMes.isSelected()) {
                            GenerarReporte(4);
                        } else {
                            if (jrTiemposAtencion.isSelected()) {
                                GenerarReporte(5);
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void jrTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrTodoActionPerformed
        activarTipoReporte(4);
    }//GEN-LAST:event_jrTodoActionPerformed

    private void jrNumeroClientesHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrNumeroClientesHoraActionPerformed
        jcNumeroCaja.setEnabled(true);
        ocultarPeriodosTiempo(1, true);
    }//GEN-LAST:event_jrNumeroClientesHoraActionPerformed

    private void jrNumeroClientesMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrNumeroClientesMesActionPerformed
        jcNumeroCaja.setEnabled(true);
        ocultarPeriodosTiempo(3, true);
    }//GEN-LAST:event_jrNumeroClientesMesActionPerformed

    private void jrNumeroClientesDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrNumeroClientesDiasActionPerformed
        jcNumeroCaja.setEnabled(true);
        ocultarPeriodosTiempo(2, true);
    }//GEN-LAST:event_jrNumeroClientesDiasActionPerformed

    private void jrTiemposAtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrTiemposAtencionActionPerformed
        jcNumeroCaja.setEnabled(true);
        ocultarPeriodosTiempo(1, true);
    }//GEN-LAST:event_jrTiemposAtencionActionPerformed

    /**
     * Oculta o mustra las opciones de los periodos que se puede escojer para
     * sacar los reportes
     * @param op
     */
    private void ocultarPeriodosTiempo(int periodo, boolean op) {
        switch (periodo) {
            case 1:
                jrDia.setVisible(op);
                jrMes.setVisible(!op);
                jrAnual.setVisible(!op);
                jrTodo.setVisible(!op);
                break;
            case 2:
                jrDia.setVisible(!op);
                jrMes.setVisible(op);
                jrAnual.setVisible(!op);
                jrTodo.setVisible(!op);
                break;
            case 3:
                jrDia.setVisible(!op);
                jrMes.setVisible(!op);
                jrAnual.setVisible(op);
                jrTodo.setVisible(!op);
                break;
            case 4:
                jrDia.setVisible(!op);
                jrMes.setVisible(!op);
                jrAnual.setVisible(!op);
                jrTodo.setVisible(op);
                break;
            case 5:
                jrDia.setVisible(!op);
                jrMes.setVisible(!op);
                jrAnual.setVisible(!op);
                jrTodo.setVisible(!op);
                jrTodo.setSelected(true);
                break;
        }
        activarTipoReporte(4);
    }

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
                jcAnio.setVisible(true);
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
    private HashMap getDatosReporte() throws NullPointerException {
        HashMap map = new HashMap();
        if (jrNumeroClientesCaja.isSelected()) {
            map.put("op", "n_clientes");
            try {
                map.put("caja", jcNumeroCaja.getSelectedItem().toString());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "No hay cajas registradas aun...", "Error...", 0);
                return null;
            }
        } else {
            if (jrTotalClientesTodasLasCajas.isSelected()) {
                map.put("op", "todo");
            } else {
                if (jrNumeroClientesHora.isSelected()
                        || jrNumeroClientesDias.isSelected()
                        || jrNumeroClientesMes.isSelected()) {
                    map.put("op", "clientesHora");
                    try {
                        map.put("caja", jcNumeroCaja.getSelectedItem().toString());
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "No hay cajas registradas aun...", "Error...", 0);
                        return null;
                    }
                } else {
                    if (jrTiemposAtencion.isSelected()) {
                        map.put("op", "tiempoAtencion");
                        try {
                            map.put("caja", jcNumeroCaja.getSelectedItem().toString());
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(null, "No hay cajas registradas aun...", "Error...", 0);
                            return null;
                        }
                    }
                }
            }
        }

        if (jrDia.isSelected()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            map.put("tiempo", "dia");
            map.put("dia", sdf.format(jxDia.getDate()));
        } else if (jrMes.isSelected()) {
            map.put("tiempo", "mes");
            map.put("mes", jcMes.getSelectedIndex() + 1);
            map.put("ano", jcAnio.getSelectedItem().toString());
        } else if (jrAnual.isSelected()) {
            map.put("tiempo", "ano");
            try {
                map.put("ano", jcAnio.getSelectedItem().toString());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "No hay registros de año...", "Error...", 0);
                return null;
            }
        } else if (jrTodo.isSelected()) {
            map.put("tiempo", "todo");
        }

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
    private javax.swing.JComboBox jcAnio;
    private javax.swing.JComboBox jcMes;
    private javax.swing.JComboBox jcNumeroCaja;
    private javax.swing.JPanel jpTipoReporte;
    private javax.swing.JRadioButton jrAnual;
    private javax.swing.JRadioButton jrDia;
    private javax.swing.JRadioButton jrMes;
    private javax.swing.JRadioButton jrNumeroClientesCaja;
    private javax.swing.JRadioButton jrNumeroClientesDias;
    private javax.swing.JRadioButton jrNumeroClientesHora;
    private javax.swing.JRadioButton jrNumeroClientesMes;
    private javax.swing.JRadioButton jrTiemposAtencion;
    private javax.swing.JRadioButton jrTodo;
    private javax.swing.JRadioButton jrTotalClientesTodasLasCajas;
    private org.jdesktop.swingx.JXDatePicker jxDia;
    private javax.swing.ButtonGroup rgTipoReporte;
    // End of variables declaration//GEN-END:variables

    private void GenerarReporte(int idx) {
        switch (idx) {
            case 0:
                GenerarReporteClientes nClientes = new GenerarReporteClientes(getDatosReporte());
                nClientes.Generar();
                break;
            case 1:
                GenerarReporteTotalesCajas totalesCajas = new GenerarReporteTotalesCajas(getDatosReporte());
                totalesCajas.Generar();
                break;
            case 2:
                GenerarReporteClientesPorHora clientePorHora = new GenerarReporteClientesPorHora(getDatosReporte());
                clientePorHora.GenerarPorHora();
                break;
            case 3:
                GenerarReporteClientesPorHora clientePorDia = new GenerarReporteClientesPorHora(getDatosReporte());
                clientePorDia.GenerarPorDia();
                break;
            case 4:
                GenerarReporteClientesPorHora clientePorMes = new GenerarReporteClientesPorHora(getDatosReporte());
                clientePorMes.GenerarPorMes();
                break;
            case 5:
                GenerarReporteTiempoAtencion tiempoAtencion = new GenerarReporteTiempoAtencion(getDatosReporte());
                tiempoAtencion.Generar();
                break;
        }
    }
}
