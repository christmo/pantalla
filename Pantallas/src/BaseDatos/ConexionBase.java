package BaseDatos;

import com.mysql.jdbc.Statement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author root
 */
public class ConexionBase {

    /**
     * Logger para guardar los log en un archivo y enviar por mail los de error
     */
    private static final Logger log = LoggerFactory.getLogger(ConexionBase.class);
    private String driver, url, ip, usr, pass;
    /**
     * Nombre de la Base de datos
     */
    private String bd;
    private Connection conexion;
    private Statement st;
    private ResultSet rs = null;
    private ResourceBundle rb;
    private Properties arcConfig;

    /**
     * Crea la conexion directamente a la base de datos,
     * parametros de la conexion
     */
    public ConexionBase() {
        try {
            rb = ResourceBundle.getBundle("BaseDatos.configsystem");
            driver = "com.mysql.jdbc.Driver";
            this.ip = rb.getString("ip_base");
            this.bd = rb.getString("base");
            this.usr = rb.getString("user");
            this.pass = rb.getString("pass");

            url = "jdbc:mysql://" + ip + "/" + bd;


            try {
                try {
                    Class.forName(driver).newInstance();
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                conexion = DriverManager.getConnection(url, usr, pass);
            } catch (SQLException ex) {
                //java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
                if (ex.getMessage().equals("Communications link failure")) {
                    log.trace("Enlace de conexión con la base de datos falló, falta el archivo de configuración...");
                }
            }
            try {
                st = (Statement) conexion.createStatement();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("Conexion a Base de Datos: " + bd + " Ok");

            log.info("Iniciar conexion a la base de datos...");
        } catch (NullPointerException ex) {
            throw new UnsupportedOperationException("nulo");
        }
    }

    /**
     * Crea una conexion a cualquier base de datos mysql, con parametros
     * de conexion indepenientes
     * @param ip - IP del servidor
     * @param bd - Nombre de la Base de datos
     * @param usr -  Nombre de Usuario
     * @param pass - Clave de la Base de datos
     */
    public ConexionBase(String ip, String bd, String usr, String pass) {
        driver = "com.mysql.jdbc.Driver";
        this.bd = bd;
        this.usr = usr;
        this.pass = pass;
        url = "jdbc:mysql://" + ip + "/" + bd;
        try {
            try {
                Class.forName(driver).newInstance();
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conexion = DriverManager.getConnection(url, usr, pass);
        } catch (SQLException ex) {
            //java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            log.trace("No se puede obtener la conexion...", ex);
        }
        try {
            st = (Statement) conexion.createStatement();
        } catch (SQLException ex) {
            //java.util.logging.Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            log.trace("No se puede crear el estatement...", ex);
        } catch (NullPointerException ex) {
            log.trace("Conexión es NULL", ex);
        }
        System.out.println("Conexion a Base de Datos: " + bd + " Ok");
    }

    /**
     * Trae la cadena de conexion a la base de datos
     * @return Connection
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Ejecuta una consulta en la base de datos, que devuelve valores
     * es necesario recorrer el resultset
     * @param sql - debe ser Select
     * @return ResultSet
     */
    public ResultSet ejecutarConsulta(String sql) {
        System.out.println("Consultar: " + sql);
        //log.trace("{}", sql);
        try {
            rs = st.executeQuery(sql);

        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getMessage().equals("No operations allowed after statement closed.")) {
                log.trace("Statement cerrado...");
            } else {
                log.trace("Error el consultar", ex);
            }
        } catch (NullPointerException ex) {
            log.trace("Null es statement");
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
        try {
            rs = st.executeQuery(sql);
            rs.next();
        } catch (SQLException ex) {
            if (!ex.getMessage().equals("No operations allowed after statement closed.")) {
                //Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
                log.trace("Statement cerrado");
            }
        }
        return rs;
    }

    /**
     * Ejecuta una consulta en la base de datos, que devuelve valores
     * no es necesario recorrer el resultset, no imprime la consulta
     * para ponerla en hilos donde no es necesario ver lo que sale...
     * @param sql - debe ser Select
     * @return ResultSet
     */
    public ResultSet ejecutarConsultaUnDatoNoImprimir(String sql) {
        //System.out.println("Consultar: " + sql);
        try {
            rs = st.executeQuery(sql);
            rs.next();
        } catch (SQLException ex) {
            if (!ex.getMessage().equals("No operations allowed after statement closed.")) {
                //Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
                log.trace("Statement cerrado");
            }
        }
        return rs;
    }
    /**
     * Resul set auxiliar para las dobles consultas
     */
    ResultSet rsAux;

    /**
     * Ejecuta una consulta en la base de datos, que devuelve valores
     * no es necesario recorrer el resultset
     * @param sql - debe ser Select
     * @return ResultSet
     */
    public ResultSet ejecutarConsultaUnDatoAux(String sql) {
        System.out.println("Consultar: " + sql);
        try {
            rsAux = st.executeQuery(sql);
            rsAux.next();
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            log.trace("", ex);
        }
        return rsAux;
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
            try {
                txt = ex.getMessage().substring(0, 76);
                if (txt.equals("Unable to connect to foreign data source: Can't connect to MySQL server on '")) {
                    String[] ip_server = ex.getMessage().split("'");
                    System.err.println("****************\n* MySQL no se pudo conectar con la tabla del servidor KRADAC -> " + ip_server[2] + "...\n****************");
                    log.trace("MySQL no se pudo conectar con la tabla del servidor KRADAC -> " + ip_server[2] + "...", ex);
                    return false;
                } else if (txt.equals("Got error 10000 'Error on remote system: 2003: Can't connect to MySQL server")) {
                    String[] ip_server = ex.getMessage().split("'");
                    System.err.println("****************\n* MySQL no se pudo conectar con la tabla FEDERADA del servidor KRADAC -> " + ip_server[3] + "...\n****************");
                    //log.trace("MySQL no se pudo conectar con la tabla FEDERADA del servidor KRADAC -> " + ip_server[3] + "...", ex);
                    log.error("[Empresa: {}]MySQL no se pudo conectar con la tabla FEDERADA del servidor KRADAC -> " + ip_server[3] + "...", ex);
                    return false;
                } else if (txt.substring(0, 64).equals("Unable to connect to foreign data source: Access denied for user")) {
                    String[] ip_server = ex.getMessage().split("'");
                    System.err.println("****************\n* NO hay permiso para insertar en el servidor KRADAC -> " + ip_server[1] + " --> " + ip_server[3] + "\n****************");
                    //log.trace("NO hay permiso para insertar en el servidor KRADAC -> " + ip_server[1] + " --> " + ip_server[3], ex);
                    log.error("[Empresa: {}]NO hay permiso para insertar en el servidor KRADAC -> " + ip_server[1] + " --> " + ip_server[3], ex);
                    return false;
                } else {
                    txt = ex.getMessage().substring(0, 15);
                }
            } catch (StringIndexOutOfBoundsException sex) {
                txt = ex.getMessage().substring(0, 15);
            }
            if (ex.getMessage().equals("Table 'rastreosatelital.server' doesn't exist")) {
                //System.err.println("La tabla \"SERVER\" no esta creada localmente...");
                log.trace("La tabla \"SERVER\" no esta creada localmente...");
                return false;
            } else if (ex.getMessage().equals("Got timeout reading communication packets")) {
                System.err.println("No hay Conexion a internet -> no se pueden guardar los datos en la tabla del servidor...");
                log.trace("No hay Conexion a internet -> no se pueden guardar los datos en la tabla del servidor...");
                return false;
            } else if (ex.getMessage().equals("No operations allowed after statement closed.")) {
                System.err.println("****************\n* MySQL no se pudo conectar con la tabla del servidor, error al ejecutar la sentencia...\n****************");
                log.trace("MySQL no se pudo conectar con la tabla del servidor, error al ejecutar la sentencia...");
                return false;
            } else if (txt.equals("Duplicate entry")) {
                System.err.println("****************\n*" + "Error de Clave Primaria -> Turno ya ingresado..." + "...\n****************");
                log.trace("Error de Clave Primaria -> Usuario ya ingresado...");
                return false;
            } else {
                //Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
                log.trace("", ex);
                return false;
            }
        }
    }

    /**
     * Utilizar cuando de error de ResultSet cerrado por problemas de los hilos
     * @param sql
     * @return boolean
     */
    public boolean ejecutarSentenciaStatement2(String sql) {
        try {
            Statement st1 = (Statement) conexion.createStatement();

            //System.out.println("Ejecutar: " + sql);
            log.info("Ejecutar: {}", sql);

            int rta = st1.executeUpdate(sql);
            if (rta >= 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            //System.out.println("EX:" + ex.getMessage());
            String txt = ex.getMessage();
            try {
                txt = ex.getMessage().substring(0, 76);
                if (txt.equals("Unable to connect to foreign data source: Can't connect to MySQL server on '")) {
                    String[] ip_server = ex.getMessage().split("'");
                    System.err.println("****************\n* MySQL no se pudo conectar con la tabla del servidor KRADAC -> " + ip_server[2] + "...\n****************");
                    log.trace("MySQL no se pudo conectar con la tabla del servidor KRADAC -> " + ip_server[2] + "...", ex);
                    return false;
                } else if (txt.equals("Got error 10000 'Error on remote system: 2003: Can't connect to MySQL server")) {
                    String[] ip_server = ex.getMessage().split("'");
                    System.err.println("****************\n* MySQL no se pudo conectar con la tabla FEDERADA del servidor KRADAC -> " + ip_server[3] + "...\n****************");
                    //log.trace("MySQL no se pudo conectar con la tabla FEDERADA del servidor KRADAC -> " + ip_server[3] + "...", ex);
                    log.error("[Empresa: {}]MySQL no se pudo conectar con la tabla FEDERADA del servidor KRADAC -> " + ip_server[3] + "...", ex);
                    return false;
                } else if (txt.substring(0, 64).equals("Unable to connect to foreign data source: Access denied for user")) {
                    String[] ip_server = ex.getMessage().split("'");
                    System.err.println("****************\n* NO hay permiso para insertar en el servidor KRADAC -> " + ip_server[1] + " --> " + ip_server[3] + "\n****************");
                    //log.trace("NO hay permiso para insertar en el servidor KRADAC -> " + ip_server[1] + " --> " + ip_server[3], ex);
                    log.error("[Empresa: {}]NO hay permiso para insertar en el servidor KRADAC -> " + ip_server[1] + " --> " + ip_server[3], ex);
                    return false;
                } else {
                    txt = ex.getMessage().substring(0, 15);
                }
            } catch (StringIndexOutOfBoundsException sex) {
                txt = ex.getMessage().substring(0, 15);
            }
            if (ex.getMessage().equals("Table 'rastreosatelital.server' doesn't exist")) {
                //System.err.println("La tabla \"SERVER\" no esta creada localmente...");
                log.trace("La tabla \"SERVER\" no esta creada localmente...");
                return false;
            } else if (ex.getMessage().equals("Got timeout reading communication packets")) {
                System.err.println("No hay Conexion a internet -> no se pueden guardar los datos en la tabla del servidor...");
                log.trace("No hay Conexion a internet -> no se pueden guardar los datos en la tabla del servidor...");
                return false;
            } else if (ex.getMessage().equals("No operations allowed after statement closed.")) {
                System.err.println("****************\n* MySQL no se pudo conectar con la tabla del servidor, error al ejecutar la sentencia...\n****************");
                log.trace("MySQL no se pudo conectar con la tabla del servidor, error al ejecutar la sentencia...");
                return false;
            } else if (txt.equals("Duplicate entry")) {
                System.err.println("****************\n*" + "Error de Clave Primaria -> Turno ya ingresado..." + "...\n****************");
                log.trace("Error de Clave Primaria -> Usuario ya ingresado...");
                return false;
            } else {
                //Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
                log.trace("", ex);
                return false;
            }
        }
    }

    /**
     * Ejecuta una sentencia en la base esta puede ser de INSERT, UPDATE O
     * DELETE el la misma solo que no presenta los errores en la base de datos
     * ni imprime la ejecucion del sql
     * @param sql - Sentencias INSERT, UPDATE, DELETE
     * @return int - confirmacion del resultado 1 valido || 0 invalido
     */
    public boolean ejecutarSentenciaHilo(String sql, String unidad) {
        try {
            int rta = st.executeUpdate(sql);
            //System.err.println("Unidad: " + unidad + " --> Coordenadas Nuevas");
            if (rta >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            //System.out.println("Unidad: " + unidad + " --> Coordenadas ya ingresadas");
            return false;
        }
    }

    /**
     * Cierra la conexion con la base de datos
     * @return Connection
     * @throws SQLException
     */
    public Connection CerrarConexion() {
        try {
            conexion.close();
            //System.out.println("Base de datos Cerrada...");
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            log.trace("", ex);
        }
        conexion = null;
        return conexion;
    }

    /**
     * Solo funciona donde MySQL este instalado fisicamente, si se usa con wamp
     * poner la ruta donde se intalo wamp y mysql se puede usuatilizar para hacer
     * actualizaciones a las base de datos...
     * @param scriptpath
     * @param verbose
     * @return String
     */
    public String executeScript(String scriptpath, boolean verbose) {
        String output = null;
        try {
            String[] cmd = new String[]{"mysql",
                this.bd,
                "--user=" + this.usr,
                "--password=" + this.pass,
                "-e",
                "\"source " + scriptpath + "\""
            };
            System.err.println(cmd[0] + " " + cmd[1] + " "
                    + cmd[2] + " " + cmd[3] + " "
                    + cmd[4] + " " + cmd[5]);
            Process proc = Runtime.getRuntime().exec(cmd);
            if (verbose) {
                InputStream inputstream = proc.getInputStream();
                InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
                BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

                // read the output
                String line;
                while ((line = bufferedreader.readLine()) != null) {
                    System.out.println(line);
                }

                // check for failure
                try {
                    if (proc.waitFor() != 0) {
                        System.err.println("exit value = "
                                + proc.exitValue());
                    }
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
