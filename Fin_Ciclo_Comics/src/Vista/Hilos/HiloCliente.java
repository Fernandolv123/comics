/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Modelo.Comic;
import Modelo.Usuario;

/**
 *
 * @author fernandolv
 */
public class HiloCliente extends Thread implements Serializable{

    Socket socketCliente;
    String orden;
    JTextArea txtaoe;
    JLabel lblcon;
    String consulta;
    byte[] img;
    public static boolean broke = false;
    public static ArrayList<Comic> listaC=new ArrayList<Comic>();
    

    public HiloCliente() {
    }

    public HiloCliente(Socket socketCliente, String orden, JTextArea txtaoe, JLabel lblcon) {
        this.socketCliente = socketCliente;
        this.orden = orden;
        this.txtaoe = txtaoe;
        this.lblcon = lblcon;
        
    }
    
    public HiloCliente(Socket socketCliente, String orden, String consulta) {
        this.socketCliente = socketCliente;
        this.orden = orden;
        this.consulta = consulta;
    }
    
    public HiloCliente(Socket socketCliente, String orden, String consulta, byte[] img) {
        this.socketCliente = socketCliente;
        this.orden = orden;
        this.consulta = consulta;
        this.img = img;
    }

    public HiloCliente(Socket socketCliente, String orden, JLabel lblcon) {
        this.socketCliente = socketCliente;
        this.orden = orden;
        this.lblcon = lblcon;
        
    }
    
    @Override
    public void run() {
    	ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        //DataInputStream entrada = null;
        //DataOutputStream salida = null;
        
        String opcionsalidaserver;
        if (socketCliente != null) {
            try {
                
                //salida = new DataOutputStream(socketCliente.getOutputStream());
            	salida = new ObjectOutputStream(socketCliente.getOutputStream());
            	entrada = new ObjectInputStream(socketCliente.getInputStream());
                //entrada = new DataInputStream(socketCliente.getInputStream());
                
                //Escribimos la orden que queremos ejecutar
            	System.out.println(orden);
            	System.out.println(consulta);
                salida.writeObject(orden);
                salida.writeObject(consulta);
                switch(orden) {
                case "getComics":
                	listaC =  (ArrayList<Comic>) entrada.readObject();
                	break;
                case "insertUser":
                	salida.writeObject(img);
                	break;
                case "getUser":
                	Usuario u =(Usuario) entrada.readObject();
                	System.out.println(u);
                	Usuario.miUser(u);
                	break;
                }
                //System.out.println(entrada.readObject());
                
                //txtaoe.setText(txtaoe.getText()+"\n"+entrada.readUTF());
              //  opcionsalidaserver = entrada.readUTF();

//                switch (opcionsalidaserver) {
//                    case "svTime":
//                       // txtaoe.setText(entrada.readUTF());
//                            txtaoe.setText(txtaoe.getText()+"\n"+entrada.readUTF());
//                        break;
//                    case "svCuantos":
//                        //txtaoe.setText(entrada.readUTF());
//                        txtaoe.setText(txtaoe.getText()+"\n"+entrada.readUTF());
//                        break;
//                    case "svSalir":
//                        //txtaoe.setText(entrada.readUTF());
//                        txtaoe.setText(txtaoe.getText()+"\n"+entrada.readUTF());
//                        break;
//                    case "svServerClosed":
//                        //txtaoe.setText(entrada.readUTF());
//                        txtaoe.setText(txtaoe.getText()+"\n"+entrada.readUTF());
//                        Thread.currentThread().interrupt();
//                        break;
//                }

            } catch (IOException | ClassNotFoundException ex) {
                try {
                	ex.printStackTrace();
                    //lblcon.setText("Desconectado");
                    //txtaoe.setText("No se ha podido conectar");
                    socketCliente.close();
                } catch (IOException ex1) {
                    Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex1);
                }

            }  catch (ClassCastException ex) {
            	broke = true;
            }
        } else {
            txtaoe.setText("No conectado");
        }
    }

}
