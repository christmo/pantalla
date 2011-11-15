/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PantallaGUI.reloj;

import PantallaGUI.PrincipalGUI;
import java.security.Principal;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Reloj {

    SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

    public Reloj() {
        Cronometro c = new Cronometro();
        Timer timer = new Timer();
        timer.schedule(c, 0, 1000);
    }

    class Cronometro extends TimerTask {

        String hora = "";

        public void run() {
            GregorianCalendar c = new GregorianCalendar();

            hora = sdfHora.format(c.getTime());
            //System.out.println(c.getTimeInMillis() + " = " + hora);

            if (hora.equals("12:59:10")) {
                igualarHoraPantallas(hora);
            }
            
            if (hora.equals("12:59:13")) {
                igualarHoraPantallas(hora);
            }
            
            if (hora.equals("12:59:16")) {
                igualarHoraPantallas(hora);
            }
        }

        private void igualarHoraPantallas(String hora) {
            PrincipalGUI.igualarHoras(hora, 1);
            PrincipalGUI.igualarHoras(hora, 2);
        }
    }
}
