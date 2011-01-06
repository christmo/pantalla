/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PantallaGUI.reportes;

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

    private HashMap campos;
    private String empresa;
    private InputStream RutaJasper;
    private Properties arcConfig;

    public GenerarReporteTotalesCajas( HashMap camp) {
        this.campos = camp;
        arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
        empresa = arcConfig.getProperty("empresa");
        RutaJasper = getClass().getResourceAsStream("plantillas/TotalClientesTodasCajas.jrxml");
    }

    /**
     * Genera segun los campos que se haya llenado
     */
    public void Generar() {
        if (campos.get("op").toString().equals("todo")) {
            if (campos.get("tiempo").toString().equals("dia")) {
                GenerarTotalClientesPorDia();
            } else if (campos.get("tiempo").toString().equals("mes")) {
                GenerarTotalClientesPorMes();
            } else if (campos.get("tiempo").toString().equals("ano")) {
                GenerarTotalClientesPorAno();
            } else if (campos.get("tiempo").toString().equals("todo")) {
                GenerarTotalClientesCajas();
            }
        }
    }

    /**
     * Genera el reporte de todos los clientes a partir de una fecha, tomando en
     * cuenta solo los registris de ese dia
     */
    private void GenerarTotalClientesPorDia() {
        String fecha = campos.get("dia").toString();
        String sql = "SELECT "
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA,"
                + "COUNT(*) AS TOTAL "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND turnos.`FECHA` = '$P!{fecha}'"
                + "GROUP BY "
                + "turnos.`ID_CAJA`";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }

    /**
     * Genera el reporte de todos los registros pertenecientes a un mes especifico
     * en un año especifico
     */
    private void GenerarTotalClientesPorMes() {
        String fecha = campos.get("mes").toString();
        String ano = campos.get("ano").toString();
        String sql = "SELECT "
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA,"
                + "COUNT(*) AS TOTAL "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "MONTH(turnos.`FECHA`) = '$P!{fecha}' AND "
                + "YEAR(turnos.`FECHA`)='$P!{ano}'"
                + "GROUP BY "
                + "turnos.`ID_CAJA`";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("ano", ano);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }

    /**
     * Genera los registros correspondientes a todo un año de trabajo
     */
    private void GenerarTotalClientesPorAno() {
        String fecha = campos.get("ano").toString();
        String sql = "SELECT "
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA,"
                + "COUNT(*) AS TOTAL "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND YEAR(turnos.`FECHA`) = '$P!{fecha}'"
                + "GROUP BY "
                + "turnos.`ID_CAJA`";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }

    /**
     * Genera los registros consernientes a todos los años desde que se implmento
     * el sistema
     */
    private void GenerarTotalClientesCajas() {
        String sql = "SELECT "
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA,"
                + "COUNT(*) AS TOTAL "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' "
                + "GROUP BY "
                + "turnos.`ID_CAJA`";

        System.out.println(sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }
}
