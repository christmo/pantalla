/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PantallaGUI.reportes;

import BaseDatos.ConexionBase;
import PantallaGUI.utilitarios.Utilitarios;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author christmo
 */
public class GenerarReporteTotalesCajas {

    private ConexionBase bd;
    private HashMap campos;
    private String empresa;
    private InputStream RutaJasper;
    private Properties arcConfig;

    public GenerarReporteTotalesCajas(ConexionBase cb, HashMap camp) {
        this.bd = cb;
        this.campos = camp;
        arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
        empresa = arcConfig.getProperty("empresa");
        RutaJasper = getClass().getResourceAsStream("plantillas/TotalClientesTodasCajas.jrxml");
    }

    /**
     * Genera segun los campos que se haya llenado
     */
    public void Generar() {
        if (campos.get("todo").toString().equals("true")) {
            //System.out.println("" + campos.get("todo"));
            GenerarTotalClientesCajas();
        } else {
            if (campos.get("todo").toString().equals("true")) {
                //System.out.println("" + campos.get("todo"));
                GenerarTotalClientesCajas();
            }
        }
    }

    /**
     * Genera el reporte de todos los clientes a partir de una fecha
     */
    private void GenerarTotalClientesPorFecha() {
        String fecha = campos.get("dia").toString();
        String sql = "SELECT "
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA,"
                + "COUNT(*) AS TOTAL "
                + "FROM "
                + "`turnos` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND turnos.`FECHA` = '$P!{fecha}'"
                + "GROUP BY "
                + "turnos.`ID_CAJA`";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper, bd);
    }

    private void GenerarTotalClientesCajas() {
        String sql = "SELECT "
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA,"
                + "COUNT(*) AS TOTAL "
                + "FROM "
                + "`turnos` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' "
                + "GROUP BY "
                + "turnos.`ID_CAJA`";

        System.out.println(sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper, bd);
    }
}
