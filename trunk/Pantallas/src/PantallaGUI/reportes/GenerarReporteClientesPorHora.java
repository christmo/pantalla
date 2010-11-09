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
public class GenerarReporteClientesPorHora {

    private ConexionBase bd;
    private HashMap campos;
    private String empresa;
    private InputStream RutaJasper;
    private Properties arcConfig;

    public GenerarReporteClientesPorHora(ConexionBase cb, HashMap camp) {
        this.bd = cb;
        this.campos = camp;
        arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
        empresa = arcConfig.getProperty("empresa");

    }

    /**
     * Genera segun los campos que se haya llenado
     */
    public void GenerarPorHora() {
        if (campos.get("op").toString().equals("clientesHora")) {
            if (campos.get("tiempo").toString().equals("dia")) {
                RutaJasper = getClass().getResourceAsStream("plantillas/ClientesPorHora.jrxml");
                GenerarClientesPorDia();
            }
        }
    }

    public void GenerarPorDia() {
        if (campos.get("op").toString().equals("clientesHora")) {
            if (campos.get("tiempo").toString().equals("mes")) {
                RutaJasper = getClass().getResourceAsStream("plantillas/ClientesPorMes.jrxml");
                GenerarClientesPorMes();
            }
        }
    }

    public void GenerarPorMes() {
        if (campos.get("op").toString().equals("clientesHora")) {
            if (campos.get("tiempo").toString().equals("ano")) {
                RutaJasper = getClass().getResourceAsStream("plantillas/ClientesPorAnio.jrxml");
                GenerarTotalClientesPorAno();
            }
        }
    }

    /**
     * Genera el reporte de todos los clientes a partir de una fecha, tomando en
     * cuenta solo los registris de ese dia
     */
    private void GenerarClientesPorDia() {
        String fecha = campos.get("dia").toString();
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "Hour(turnos.`HORA`) AS turnos_HORA,"
                + "turnos.`FECHA` AS FECHA,"
                + "count(*) AS TOTAL "
                + "FROM "
                + "`turnos` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "turnos.`FECHA` = '$P!{fecha}' AND "
                + "turnos.`ID_CAJA` = $P!{caja} "
                + "GROUP BY "
                + "Hour(turnos.`HORA`)";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper, bd);
    }

    private void GenerarClientesPorMes() {
        String fecha = campos.get("mes").toString();
        String ano = campos.get("ano").toString();
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "DAY(turnos.`FECHA`) AS turnos_DIA,"
                + "COUNT(*) AS TOTAL "
                + "FROM "
                + "`turnos` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "MONTH(turnos.`FECHA`) = '$P!{fecha}' AND "
                + "turnos.`ID_CAJA` = $P!{caja} "
                + "GROUP BY "
                + "DAY(turnos.`FECHA`)";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("ano", ano);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper, bd);
    }

    private void GenerarTotalClientesPorAno() {
        String fecha = campos.get("ano").toString();
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "MONTHNAME(turnos.`FECHA`) AS Nombre_mes,"
                + "MONTH(turnos.`FECHA`) AS MES,"
                + "count(*) AS TOTAL "
                + "FROM "
                + "`turnos` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "YEAR(turnos.`FECHA`) = '$P!{fecha}' AND "
                + "turnos.`ID_CAJA` = $P!{caja} "
                + "group by "
                + "MONTH(turnos.`FECHA`)";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper, bd);
    }
}
