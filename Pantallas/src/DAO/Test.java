/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kradac
 */
public class Test {
    public static void main(String[] args) {
        DAOFabricaBasesDatos mysql = DAOFabricaBasesDatos.getDAOFabricaBasesDatos(DAOFabricaBasesDatos.MYSQL);
        ManejadorBaseDAO manejarMySQL = mysql.getManejadorBaseDAO();
        ResultSet rs = manejarMySQL.ejecutarConsulta("Select * from mensajes");
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            manejarMySQL.cerrarConexionBaseDatos();
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
