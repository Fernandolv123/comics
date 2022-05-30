/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VersionFinal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AD
 */
public class Principal {
    public static void main(String[] args) {
        try {
            //       new Cliente().setVisible(true);
            new Servidor().setVisible(true);
            new Cliente().setVisible(true);
          //  new Cliente().setVisible(true);
            new Cliente().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}
