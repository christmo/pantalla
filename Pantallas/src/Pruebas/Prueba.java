/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pruebas;

import comunicacion.comm.CommPantalla;

/**
 *
 * @author kradac
 */
public class Prueba {
    public static void main(String[] args) {
        CommPantalla comm = new CommPantalla("COM2","<DATE$\r10/09/10\r");
        comm.start();
    }
}
