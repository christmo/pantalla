/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PantallaGUI.utilitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JToggleButton;

/**
 *
 * @author kradac
 */
public class Utilitarios {

    public static Properties arcConfig;

    /**
     * Permite deseleccionar los botones de las fuentes para que solo este
     * seleccionado uno de todo el conjunto de 6
     * @param btn
     * @param botones
     */
    public static void botonesFuentes(int btn, ArrayList<JToggleButton> botones) {
        for (int i = 0; i < botones.size(); i++) {
            if (btn == (i + 1)) {
                botones.get(i).setSelected(true);
            } else {
                botones.get(i).setSelected(false);
            }
        }
    }

    /**
     * Permite formatear la hora para que no deje pasar horas no validad
     * @param hora
     * @return String
     */
    public static String formatearHora(String hora) {
        String[] arrHora = hora.split(":");
        String nuevaHora = "";
        int dato;

        if (arrHora.length == 3) {
            arrHora[2]="10";//siempre igualar a 10 segundos
            for (int i = 0; i < arrHora.length; i++) {
                try {
                    dato = Integer.parseInt(arrHora[i]);
                    if (i == 0) {
                        if (dato >= 0 && dato <= 23) {
                            if (arrHora[i].length() == 2) {
                                nuevaHora += "" + arrHora[i];
                            } else {
                                nuevaHora += "0" + arrHora[i];
                            }
                        }
                    } else {
                        if (dato >= 0 && dato <= 59) {
                            if (arrHora[i].length() == 2) {
                                nuevaHora += "" + arrHora[i];
                            } else {
                                nuevaHora += "0" + arrHora[i];
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    nuevaHora += "00";
                }
                nuevaHora += ":";
            }
            try {
                return nuevaHora.substring(0, 8);
            } catch (StringIndexOutOfBoundsException ex) {
                return "";
            }
        } else {
            if (arrHora.length == 1 && hora.length() == 6) {
                try {
                    if (Integer.parseInt(hora.substring(0, 2)) >= 0 && Integer.parseInt(hora.substring(0, 2)) <= 23) {
                        nuevaHora += hora.substring(0, 2) + ":";
                    } else {
                        nuevaHora += "00:";
                    }
                    if (Integer.parseInt(hora.substring(2, 4)) >= 0 && Integer.parseInt(hora.substring(2, 4)) <= 59) {
                        nuevaHora += hora.substring(2, 4) + ":";
                    } else {
                        nuevaHora += "00:";
                    }
                    if (Integer.parseInt(hora.substring(4, 6)) >= 0 && Integer.parseInt(hora.substring(4, 6)) <= 59) {
                        //nuevaHora += hora.substring(4, 6);
                        nuevaHora += "10"; //siempre se iguala a 10 segundos
                    } else {
                        nuevaHora += "00";
                    }
                    return nuevaHora;
                } catch (NumberFormatException ex) {
                    return "";
                }
            } else {
                return "";
            }
        }
    }

    /**
     * Reemplaza todos los enter que pueda tener el texto por un espacio
     * @param mensaje
     * @return String
     */
    public static String quitarEnterTexto(String mensaje) {
        return mensaje.replace("\n", " ");
    }

    /**
     * Trae el archivo properties que se encuentre en el direcctorio del jar
     * @param arc -> "configsystem.properties" nombre del archivo properties
     * @return Properties
     */
    public static Properties obtenerArchivoPropiedades(String arc) {

        try {
            CodeSource codeSource = Utilitarios.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            File jarDir = jarFile.getParentFile();

            if (jarDir != null && jarDir.isDirectory()) {
                File propFile = new File(jarDir, arc);
                arcConfig = new Properties();
                arcConfig.load(new BufferedReader(new FileReader(propFile.getAbsoluteFile())));
            }
        } catch (IOException ex) {
            Logger.getLogger(Utilitarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Utilitarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arcConfig;
    }

    /**
     * Obtiene la fecha actual del equipo, este metodo se puede consumir desde
     * todo el proyecto.
     * @return String
     */
    public static String getFechaAAMMdd() {
        Calendar c = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        System.out.println("f:" + sdf.format(c.getTime()));
        return sdf.format(c.getTime());
    }

    public static String getHora() {
        Calendar c = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("H:" + sdf.format(c.getTime()));
        return sdf.format(c.getTime());
    }
}
