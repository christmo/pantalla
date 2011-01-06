/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kradac
 */
public class MySQLTransaccionesDAO implements ManejadorBaseDAO {

    /**
     * Logger para guardar los log en un archivo y enviar por mail los de error
     */
    private static final Logger log = LoggerFactory.getLogger(MySQLTransaccionesDAO.class);
    private MySQLConexionDAO conMySQL = new MySQLConexionDAO();
    private Statement st;
    private Connection con;
    private ResultSet rs;

    public MySQLTransaccionesDAO() {
        st = conMySQL.getStatement();
        System.out.println("Statement OK");
    }

    /**
     * Trae la cadena de conexion a la base de datos
     * @return Connection
     */
    public Connection getConexion() {
        return con;
    }

    /**
     * Ejecuta una consulta en la base de datos, que devuelve valores
     * es necesario recorrer el resultset
     * @param sql - debe ser Select
     * @return ResultSet
     */
    public ResultSet ejecutarConsulta(String sql) throws NullPointerException {
        System.out.println("Consultar: " + sql);
        log.trace("Consultar: {}", sql);
        try {
            rs = st.executeQuery(sql);

        } catch (SQLException ex) {
            if (ex.getMessage().equals("No operations allowed after statement closed.")) {
                log.trace("Statement cerrado...");
            } else {
                log.trace("Error el consultar", ex);
            }
            //return null;
        } catch (NullPointerException ex) {
            log.trace("Statemen st esta null");
            return null;
        }
        return rs;
    }

    /**
     * Ejecuta una consulta en la base de datos, que devuelve valores
     * no es necesario recorrer el resultset
     * @param sql - debe ser Select
     * @return ResultSet
     */
    public ResultSet ejecutarConsultaUnDato(String sql) {
        System.out.println("Consultar: " + sql);
        log.trace("Consultar: {}", sql);
        try {
            rs = st.executeQuery(sql);
            rs.next();
        } catch (SQLException ex) {
            if (!ex.getMessage().equals("No operations allowed after statement closed.")) {
                log.trace("Statement cerrado");
            }
        } catch (NullPointerException ex) {
            log.error("Revisar los parametros de configuración de la base de datos...");
            JOptionPane.showMessageDialog(null, "Revisar los parametros de la base de datos...", "Error...", 0);
            System.exit(1);
        }
        return rs;
    }

    /**
     * Ejecuta una sentencia en la base esta puede ser de INSERT, UPDATE O
     * DELETE
     * @param sql - Sentencias INSERT, UPDATE, DELETE
     * @return int - confirmacion del resultado 1 valido || 0 invalido
     */
    public boolean ejecutarSentencia(String sql) {
        try {
            System.out.println("Ejecutar: " + sql);

            log.info("Ejecutar: {}", sql);

            int rta = st.executeUpdate(sql);

            if (rta >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            //System.out.println("EX:" + ex.getMessage());
            String txt = ex.getMessage();
            if (ex.getMessage().equals("Got timeout reading communication packets")) {
                System.err.println("No hay Conexion NO se pueden guardar los datos en la tabla del servidor...");
                log.trace("No hay Conexion a internet -> no se pueden guardar los datos en la tabla del servidor...");
                return false;
            } else if (ex.getMessage().equals("No operations allowed after statement closed.")) {
                System.err.println("****************\n* MySQL no se pudo conectar con la tabla del servidor, error al ejecutar la sentencia...\n****************");
                log.trace("MySQL no se pudo conectar con la tabla del servidor, error al ejecutar la sentencia...");
                return false;
            } else if (txt.equals("Duplicate entry")) {
                System.err.println("****************\n*" + "Error de Clave Primaria -> Turno ya ingresado..." + "...\n****************");
                log.trace("Error de Clave Primaria -> Turno ya ingresado...");
                return false;
            } else {
                log.trace("", ex);
                return false;
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error en la configuración de la base de datos...", "Error...", 0);
            return false;
        }
    }

    /**
     * Cierra la conexion con la base de datos
     */
    public void cerrarConexionBaseDatos() {
        try {
            con.close();
            System.out.println("Base de datos Cerrada...");
        } catch (SQLException ex) {
            //java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
        }
    }
}
