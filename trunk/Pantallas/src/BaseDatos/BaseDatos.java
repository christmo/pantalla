/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import DAO.DAOFabricaBasesDatos;
import DAO.ManejadorBaseDAO;
import DAO.MySQLConexionDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kradac
 */
public class BaseDatos {

    private ResultSet rs;
    private static ManejadorBaseDAO manejadorTransaccionesBaseDatos;

    public BaseDatos() {
        DAOFabricaBasesDatos mysql = DAOFabricaBasesDatos.getDAOFabricaBasesDatos(DAOFabricaBasesDatos.MYSQL);
        manejadorTransaccionesBaseDatos = mysql.getManejadorBaseDAO();
    }

    public static Connection getConexion() {
        Connection c = MySQLConexionDAO.con;
        return c;
    }

    /**
     * Guarda un mensaje en la base de datos para ponerlo en el futuro en la
     * pantalla
     * @param mensaje
     * @return boolean -> retorna true si guarda el dato
     */
    public boolean guardarMensajePantalla(String mensaje) {
        String sql = "INSERT INTO MENSAJES(MENSAJE) VALUES('" + mensaje + "')";
        return manejadorTransaccionesBaseDatos.ejecutarSentencia(sql);
    }

    /**
     * Guarda el registro de los mensajes que se escriben en la pantalla, fecha,
     * hora, con sus respectivos estados
     * TODO: poner el usuario registrado en el Active Directory
     * @param mensaje
     * @param estado -> MOSTRAR EN LA LISTA DE MENSAJES GUARDADOS -> ACT | INA
     * @param accion -> REGISTRO DE LA ACCION EJECUTADA GUARDADO O BORRADO
     * @return boolean
     */
    public boolean guardarMensajePantalla(String mensaje, String estado, String accion) {
        String usuario = System.getProperty("user.name");
        String sql = "INSERT INTO MENSAJES(MENSAJE,USUARIO,FECHA,HORA,ESTADO,ACCION) "
                + "VALUES('"
                + mensaje
                + "','"
                + usuario
                + "',"
                + "NOW()"
                + ","
                + "NOW()"
                + ",'"
                + estado
                + "','"
                + accion
                + "')";
        return manejadorTransaccionesBaseDatos.ejecutarSentencia(sql);
    }

    /**
     * Borrar mensajes guardados en la pantalla 
     * @return boolean
     */
    public boolean borrarUltimoMensajeGuardadoPantalla() {
        String ultimoMensaje = ultimoMensajeGuardadoPantalla();
        String usuario = System.getProperty("user.name");
        System.out.println("Ultimo mensaje: " + ultimoMensaje);
        String sql = "INSERT INTO MENSAJES(MENSAJE,USUARIO,FECHA,HORA,ESTADO,ACCION) "
                + "VALUES('"
                + ultimoMensaje
                + "','"
                + usuario
                + "',"
                + "NOW()"
                + ","
                + "NOW()"
                + ",'"
                + "INA"
                + "','"
                + "BORRADO"
                + "')";
        return manejadorTransaccionesBaseDatos.ejecutarSentencia(sql);
    }

    /**
     * Obtener el ultimo mensaje guardado en pantalla
     * @return String
     */
    private String ultimoMensajeGuardadoPantalla() {
        String sql = "SELECT MENSAJE "
                + "FROM MENSAJES "
                + "WHERE CONCAT(FECHA,' ',HORA) = ("
                + "SELECT MAX(CONCAT(FECHA,' ',HORA)) FROM MENSAJES WHERE ACCION = 'GUARDADO' AND ESTADO = 'INA'"
                + ") AND ACCION = 'GUARDADO' AND ESTADO = 'INA'";
        rs = manejadorTransaccionesBaseDatos.ejecutarConsultaUnDato(sql);
        try {
            return rs.getString("MENSAJE");
        } catch (SQLException ex) {
            return null;
        } finally {
            //manejadorTransaccionesBaseDatos.cerrarConexionBaseDatos();
        }
    }

    /**
     * Mensajes guardados
     * @return String[]
     */
    public String[] obtenerMensajesGuardados() {
        String sql = "SELECT MENSAJE FROM MENSAJES WHERE ESTADO='ACT' AND ACCION = 'GUARDADO' ORDER BY MENSAJE DESC";
        rs = manejadorTransaccionesBaseDatos.ejecutarConsulta(sql);
        ArrayList mensajes = new ArrayList<String>();
        try {
            while (rs.next()) {
                mensajes.add(rs.getString("MENSAJE"));
            }
        } catch (SQLException ex) {
        } finally {
            //manejadorTransaccionesBaseDatos.cerrarConexionBaseDatos();
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
        manejadorTransaccionesBaseDatos.ejecutarSentencia(sql);
    }

    /**
     * Guarda el turno de atencion al cliente en la base de datos
     * @param intIdCaja
     * @param estado
     */
    public boolean guardarTurno(int intIdCaja, String estado) {
        String sql = "INSERT INTO TURNOS(ID_CAJA,ESTADO,FECHA,HORA) "
                + "VALUES (" + intIdCaja + ",'" + estado + "',NOW(),NOW())";
        return manejadorTransaccionesBaseDatos.ejecutarSentencia(sql);
    }

    /**
     * Guarda el turno de atencion al cliente en la base de datos
     * @param intIdCaja
     * @param estado
     */
    public boolean guardarTurnoConUsuario(int intIdCaja, String estado, String usuario) {
        String sql = "INSERT INTO TURNOS(ID_CAJA,ESTADO,FECHA,HORA,USUARIO) "
                + "VALUES (" + intIdCaja + ",'" + estado + "',NOW(),NOW(),'" + usuario + "')";
        return manejadorTransaccionesBaseDatos.ejecutarSentencia(sql);
    }

    /**
     * Obtener las cajas que estan en la base de datos reportando y reciviendo
     * a los clientes
     * @return String[]
     */
    public String[] obtenerNumeroCajas() {
        String sql = "SELECT DISTINCT ID_CAJA FROM TURNOS ORDER BY ID_CAJA ASC";
        rs = manejadorTransaccionesBaseDatos.ejecutarConsulta(sql);
        ArrayList caja = new ArrayList();
        try {
            while (rs.next()) {
                caja.add(rs.getString("ID_CAJA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //manejadorTransaccionesBaseDatos.cerrarConexionBaseDatos();
        }
        String[] cajas = new String[caja.size()];
        return (String[]) caja.toArray(cajas);
    }

    /**
     * Obtiene los años dentro de la base de datos de los turnos registrados
     * @return String[]
     */
    public String[] obtenerAniosBase() {
        String sql = "SELECT DISTINCT YEAR(FECHA) AS ANIO FROM TURNOS;";
        rs = manejadorTransaccionesBaseDatos.ejecutarConsulta(sql);
        ArrayList anio = new ArrayList();
        try {
            while (rs.next()) {
                anio.add(rs.getString("ANIO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //manejadorTransaccionesBaseDatos.cerrarConexionBaseDatos();
        }
        String[] anios = new String[anio.size()];
        return (String[]) anio.toArray(anios);
    }

    public Date obtenerUltimaLlamadaCaja(int caja) {
        String sql = "SELECT CONCAT(FECHA,'-',MAX(HORA)) AS HORA "
                + "FROM TURNOS "
                + "WHERE  FECHA = DATE(NOW()) AND ID_CAJA = " + caja
                + " GROUP BY FECHA;";
        rs = manejadorTransaccionesBaseDatos.ejecutarConsultaUnDato(sql);
        try {
            String hora = rs.getString("HORA");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            Date horaUltimaLlamada = null;
            try {
                horaUltimaLlamada = sdf.parse(hora);
            } catch (ParseException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return horaUltimaLlamada;
        } catch (SQLException ex) {
            return null;
        } finally {
            //manejadorTransaccionesBaseDatos.cerrarConexionBaseDatos();
        }
    }

    public void cerrarConexionBaseDatos() {
        manejadorTransaccionesBaseDatos.cerrarConexionBaseDatos();
    }
}
