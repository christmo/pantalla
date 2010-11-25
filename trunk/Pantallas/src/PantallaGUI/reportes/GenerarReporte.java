/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PantallaGUI.reportes;

import BaseDatos.BaseDatos;
import java.io.InputStream;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author christmo
 */
public class GenerarReporte {

    public GenerarReporte() {
    }

    /**
     * Genera el reporte para imprimirlo
     * @param par
     * @param ruta
     * @param bd
     */
    public static void Generar(Map par, InputStream ruta) {
        try {
            Map parameters = par;

            //JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("plantillas/Clientes.jrxml"));
            JasperReport report = JasperCompileManager.compileReport(ruta);

            //InputStream report= GenerarReporteClientes.class.getResourceAsStream("/interfaz/Reportes/Clientes.jasper");
            //JasperReport report = JasperCompileManager.compileReport("/interfaz/Reportes/prueba.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, BaseDatos.getConexion());
            // Exporta el informe a PDF
            //JasperExportManager.exportReportToPdfFile(print,"/tmp/demodos.pdf");
            //Para visualizar el pdf directamente desde java
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // BaseDatos.manejadorTransaccionesBaseDatos.cerrarConexionBaseDatos();
        }
    }
}
