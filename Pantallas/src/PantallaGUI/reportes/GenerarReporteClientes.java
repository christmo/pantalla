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
public class GenerarReporteClientes {

    //private ConexionBase bd;
    private HashMap campos;
    private String empresa;
    private InputStream RutaJasper;
    private Properties arcConfig;

    public GenerarReporteClientes( HashMap camp) {
        //this.bd = cb;
        this.campos = camp;
        arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
        empresa = arcConfig.getProperty("empresa");
        RutaJasper = getClass().getResourceAsStream("plantillas/ClientesPorCaja.jrxml");
    }

    /**
     * Genera segun los campos que se haya llenado
     */
    public void Generar() {
        if (campos.get("op").toString().equals("n_clientes")) {
            if (campos.get("tiempo").toString().equals("dia")) {
                GenerarClientesPorDia();
            } else if (campos.get("tiempo").toString().equals("mes")) {
                GenerarClientesPorMes();
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
    private void GenerarClientesPorDia() {
        String fecha = campos.get("dia").toString();
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "turnos.`HORA` AS turnos_HORA,"
                + "turnos.`FECHA` AS turnos_FECHA,"
                + "turnos.`ESTADO` AS turnos_ESTADO,"
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "turnos.`FECHA` = '$P!{fecha}' AND "
                + "turnos.`ID_CAJA` = $P!{caja}";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }

    /**
     * Genera el reporte de todos los registros pertenecientes a un mes especifico
     * en un año especifico
     */
    private void GenerarClientesPorMes() {
        String fecha = campos.get("mes").toString();
        String ano = campos.get("ano").toString();
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "turnos.`HORA` AS turnos_HORA,"
                + "turnos.`FECHA` AS turnos_FECHA,"
                + "turnos.`ESTADO` AS turnos_ESTADO,"
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "MONTH(turnos.`FECHA`) = '$P!{fecha}' AND "
                + "YEAR(turnos.`FECHA`)='$P!{ano}' AND "
                + "turnos.`ID_CAJA` = $P!{caja}";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("ano", ano);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }

    /**
     * Genera los registros correspondientes a todo un año de trabajo
     */
    private void GenerarTotalClientesPorAno() {
        String fecha = campos.get("ano").toString();
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "turnos.`HORA` AS turnos_HORA,"
                + "turnos.`FECHA` AS turnos_FECHA,"
                + "turnos.`ESTADO` AS turnos_ESTADO,"
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "YEAR(turnos.`FECHA`) = '$P!{fecha}' AND "
                + "turnos.`ID_CAJA` = $P!{caja}";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }

    /**
     * Genera los registros consernientes a todos los años desde que se implmento
     * el sistema
     */
    private void GenerarTotalClientesCajas() {
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "turnos.`HORA` AS turnos_HORA,"
                + "turnos.`FECHA` AS turnos_FECHA,"
                + "turnos.`ESTADO` AS turnos_ESTADO,"
                + "turnos.`ID_CAJA` AS turnos_ID_CAJA "
                + "FROM "
                + "`TURNOS` turnos "
                + "WHERE "
                + "turnos.`ESTADO` = 'ACTIVO' AND "
                + "turnos.`ID_CAJA` = $P!{caja}";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }
}
