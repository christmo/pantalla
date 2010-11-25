/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package PantallaGUI.reportes.plantillas.FormateoCamposReportes;
package PantallaGUI.reportes.plantillas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

/**
 *
 * @author kradac
 */
public class FormateoCamposReportes extends JRDefaultScriptlet {

    public String getTiempoFormatoHora(String segundos) throws JRScriptletException{
        System.out.println("Entrooooo ;-)");
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        try {
            Date tiempo = sdf.parse(segundos);
            sdf = new SimpleDateFormat("hh:mm:ss");
            return sdf.format(tiempo);
        } catch (ParseException ex) {
            Logger.getLogger(FormateoCamposReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
