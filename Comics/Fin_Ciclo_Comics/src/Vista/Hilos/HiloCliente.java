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
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author fernandolv
 */
public class HiloCliente extends Thread {

    Socket socketCliente;
    String orden;
    JTextArea txtaoe;
    JLabel lblcon;
    

    public HiloCliente() {
    }

    public HiloCliente(Socket socketCliente, String orden, JTextArea txtaoe, JLabel lblcon) {
        this.socketCliente = socketCliente;
        this.orden = orden;
        this.txtaoe = txtaoe;
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
                salida.writeObject(orden);
                txtaoe.setText(txtaoe.getText()+"\n"+entrada.readUTF());
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

            } catch (IOException ex) {
                try {
                    lblcon.setText("Desconectado");
                    txtaoe.setText("No se ha podido conectar");
                    socketCliente.close();
                } catch (IOException ex1) {
                    Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex1);
                }

            }
        } else {
            txtaoe.setText("No conectado");
        }
    }

}
