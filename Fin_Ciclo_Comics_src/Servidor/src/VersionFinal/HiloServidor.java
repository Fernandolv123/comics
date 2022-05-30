/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VersionFinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernandolv
 */
public class HiloServidor extends Thread {

    String opcion;
    Socket socketCliente;
    int puerto;
    public static int nc;
    

 
    ServerSocket ss;

    ArrayList<Socket> listasockets = new ArrayList();

    public HiloServidor(int puerto) {
        this.puerto = puerto;
        opcion = "";
    }

    @Override
    public void run() {
        
        try {
            //Iniciamos el socket listener
            ss = new ServerSocket(puerto);    
            while (!ss.isClosed()) {
                socketCliente = ss.accept();
                listasockets.add(socketCliente);
                nc++;
                new HiloServidorTrabajo(socketCliente).start();
            }
          
        } catch (IOException ex) {
           
            
        } finally {         
            try {
                ss.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void salir() {
        try {
            if (ss == null) {
                return;
            }
            ss.close();
           
            for (Socket s : listasockets) {
              s.close();
            }
             nc=0;
        } catch (IOException ex) {
        }
    }
}
