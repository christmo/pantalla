/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kradac
 */
public class BaseDatos {

    private ConexionBase cb;
    ResultSet rs;

    public BaseDatos() {
        //cb = new ConexionBase("localhost", "turnoskradac", "root", "");
        cb = new ConexionBase();
    }

    /**
     * Guarda un mensaje en la base de datos para ponerlo en el futuro en la
     * pantalla
     * @param mensaje
     * @return boolean -> retorna true si guarda el dato
     */
    public boolean guardarMensajePantalla(String mensaje) {
        String sql = "INSERT INTO MENSAJES(MENSAJE) VALUES('" + mensaje + "')";
        return cb.ejecutarSentencia(sql);
    }

    /**
     * Cierra la conexion de la base de datos
     */
    public void cerrarConexionBase() {
        cb.CerrarConexion();
    }

    /**
     * Mensajes guardados
     * @return String[]
     */
    public String[] obtenerMensajesGuardados() {
        String sql = "SELECT MENSAJE FROM MENSAJES ORDER BY MENSAJE DESC";
        rs = cb.ejecutarConsulta(sql);
        ArrayList mensajes = new ArrayList<String>();
        try {
            while (rs.next()) {
                mensajes.add(rs.getString("MENSAJE"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] cast = new String[mensajes.size()];
        cast = (String[]) mensajes.toArray(cast);
        return cast;
    }

    /**
     * Eliminar un mensaje de la base de datos
     * @param dato
     */
    public void eliminarMensaje(String dato) {
        String sql = "DELETE FROM MENSAJES WHERE MENSAJE='" + dato + "'";
        cb.ejecutarSentencia(sql);
    }

    /**
     * Guarda el turno de atencion al cliente en la base de datos
     * @param string
     * @param string0
     */
    public boolean guardarTurno(int intIdCaja, String estado) {
        String sql = "INSERT INTO TURNOS(ID_CAJA,ESTADO,FECHA,HORA) VALUES (" + intIdCaja + ",'" + estado + "',NOW(),NOW())";
        return cb.ejecutarSentencia(sql);
    }

    /**
     * Obtener las cajas que estan en la base de datos reportando y reciviendo
     * a los clientes
     * @return String[]
     */
    public String[] obtenerNumeroCajas() {
        String sql = "SELECT DISTINCT ID_CAJA FROM TURNOS ORDER BY ID_CAJA ASC";
        rs = cb.ejecutarConsulta(sql);
        ArrayList caja = new ArrayList();
        try {
            while (rs.next()) {
                caja.add(rs.getString("ID_CAJA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String [] cajas = new String[caja.size()];
        return (String[]) caja.toArray(cajas);
    }

    /**
     * Obtiene los años dentro de la base de datos de los turnos registrados
     * @return String[]
     */
    public String[] obtenerAniosBase() {
        String sql = "SELECT DISTINCT YEAR(FECHA) AS ANIO FROM TURNOS;";
        rs = cb.ejecutarConsulta(sql);
        ArrayList anio = new ArrayList();
        try {
            while (rs.next()) {
                anio.add(rs.getString("ANIO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String [] anios = new String[anio.size()];
        return (String[]) anio.toArray(anios);
    }
}
