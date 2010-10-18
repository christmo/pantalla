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
}
