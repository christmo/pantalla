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
public class GenerarReporteTiempoAtencion {

    private HashMap campos;
    private String empresa;
    private InputStream RutaJasper;
    private Properties arcConfig;

    public GenerarReporteTiempoAtencion(HashMap camp) {
        this.campos = camp;
        arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");
        empresa = arcConfig.getProperty("empresa");
        RutaJasper = getClass().getResourceAsStream("plantillas/TiemposAtencion.jrxml");
    }

    /**
     * Genera segun los campos que se haya llenado
     */
    public void Generar() {
        if (campos.get("op").toString().equals("tiempoAtencion")) {
            if (campos.get("tiempo").toString().equals("dia")) {
                GenerarTiemposAtencionPorDia();
            }
        }
    }

    /**
     * Genera el reporte de todos los clientes a partir de una fecha, tomando en
     * cuenta solo los registris de ese dia
     */
    private void GenerarTiemposAtencionPorDia() {
        String fecha = campos.get("dia").toString();
        String caja = campos.get("caja").toString();

        String sql = "SELECT "
                + "turnos.`FECHA` AS FECHA, "
                + "turnos.`HORA` AS HORA_INI, "
                + "(SELECT T2.`HORA` "
                + "FROM `turnos` T2 "
                + "WHERE T2.`HORA` > turnos.`HORA` AND T2.`FECHA` ='$P!{fecha}' AND T2.`ID_CAJA` = $P!{caja} "
                + "ORDER BY T2.`HORA` LIMIT 1) AS HORA_FIN, "
                + "SUBTIME((SELECT t2.`HORA` "
                + "FROM `turnos` t2 "
                + "WHERE T2.`HORA` > turnos.`HORA` AND T2.`FECHA` ='$P!{fecha}' AND T2.`ID_CAJA` = $P!{caja} "
                + "ORDER BY T2.`HORA` LIMIT 1),turnos.`HORA`) AS DIFERENCIA, "
                + "TIME_TO_SEC(SUBTIME((SELECT T2.`HORA` "
                + "FROM `turnos` T2 "
                + "WHERE T2.`HORA` > turnos.`HORA` AND T2.`FECHA` ='$P!{fecha}' AND T2.`ID_CAJA` = $P!{caja} "
                + "ORDER BY T2.`HORA` LIMIT 1),turnos.`HORA`)) AS SEGUNDOS "
                + "FROM "
                + "`turnos` turnos "
                + "WHERE turnos.`ESTADO`='ACTIVO' AND turnos.`FECHA` = '$P!{fecha}' AND turnos.`ID_CAJA` = $P!{caja}";

        System.out.println("SQL: " + sql);

        Map parametro = new HashMap();
        parametro.put("sql", sql);
        parametro.put("fecha", fecha);
        parametro.put("caja", caja);
        parametro.put("empresa", empresa);

        GenerarReporte.Generar(parametro, RutaJasper);
    }
}