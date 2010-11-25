/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import PantallaGUI.utilitarios.Utilitarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kradac
 */
public class MySQLConexionDAO extends DAOFabricaBasesDatos {

    private static String ip;
    private static String usr;
    private static String pass;
    private static String bd;
    private static String puerto_base;
    private static Statement st;
    public static Connection con;

    public MySQLConexionDAO() {
        con = crearConexion();
        try {
            st = (Statement) con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConexionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexion Base MYSQL ok");
    }

    // method to create Cloudscape connections
    public static Connection crearConexion() {
        Properties arcConfig = Utilitarios.obtenerArchivoPropiedades("configsystem.properties");

        ip = arcConfig.getProperty("ip_base");
        bd = arcConfig.getProperty("base");
        usr = arcConfig.getProperty("user");
        pass = arcConfig.getProperty("pass");
        puerto_base = arcConfig.getProperty("puerto_base");

        // Use DRIVER and DBURL to create a connection
        // Recommend connection pool implementation/usage
        String DRIVER = "com.mysql.jdbc.Driver";
        String DBURL = "jdbc:mysql://" + ip + ":" + puerto_base + "/" + bd;
        Connection conexion = null;
        try {
            try {
                Class.forName(DRIVER).newInstance();
            } catch (InstantiationException ex) {
                //java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                //java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conexion = DriverManager.getConnection(DBURL, usr, pass);
        } catch (SQLException ex) {
            //log.trace("No se puede obtener la conexion...", ex);
        }

        return conexion;
    }

    @Override
    public ManejadorBaseDAO getManejadorBaseDAO() {
        return new MySQLTransaccionesDAO();
    }

    public Statement getStatement() {
        return st;
    }

    public Connection getConexion() {
        return con;
    }
}
