/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author kradac
 */
public abstract class DAOFabricaBasesDatos {
    // List of DAO types supported by the factory

    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int SYBASE = 3;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract ManejadorBaseDAO getManejadorBaseDAO();

    public static DAOFabricaBasesDatos getDAOFabricaBasesDatos(int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySQLConexionDAO();
            case ORACLE:
            //return new OracleDAOFactory();
            case SYBASE:
            //return new SybaseDAOFactory();

            default:
                return null;
        }
    }
}
