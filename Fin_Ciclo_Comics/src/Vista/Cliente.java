/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import Vista.Hilos.HiloCliente;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import BD.ComicsDAO;
import Modelo.Conexion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author fernandolv
 */
public class Cliente extends javax.swing.JFrame {

    /**
     * Creates new form Cliente
     */
    Socket socketClient;
    HiloCliente hc;
    boolean encendido = false;

    public Cliente() throws IOException {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblconexion = new javax.swing.JLabel();
        txtpuerto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblconexion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblconexion.setText("jLabel1");

        txtpuerto.setText("2000");

        jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Desconectar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Time");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cuantos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        
        btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ComicsDAO cdao = new ComicsDAO();
        		try {
					TablaComicScreen tcs = new TablaComicScreen();
					tcs.setVisible(true);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		/*try {
					cdao.obtenerProductos();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
        	}
        });
        txtaoe = new javax.swing.JTextArea();
        
                txtaoe.setColumns(20);
                txtaoe.setRows(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(lblconexion, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnNewButton))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jButton1)
        							.addGap(31)
        							.addComponent(txtpuerto, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        							.addGap(32)
        							.addComponent(jButton2))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(10)
        							.addComponent(txtaoe, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(59)
        					.addComponent(jButton3)
        					.addGap(98)
        					.addComponent(jButton4)))
        			.addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(12)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblconexion)
        				.addComponent(btnNewButton))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtpuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton1)
        				.addComponent(jButton2))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(txtaoe, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
        			.addGap(13)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton3)
        				.addComponent(jButton4))
        			.addContainerGap(14, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            
            if (socketClient != null) {
                if (!socketClient.isClosed()) 
                {
                    JOptionPane.showMessageDialog(rootPane, "El cliente ya está iniciado (desconecte y vuelva a conectar)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }   
         //           Si lo hemos conectado al menos 1 vez y desconectado volvemos a conectar
            txtaoe.setText("");
            lblconexion.setText("Conectado");
            try { 
           //             Nos Conectamos a un Servidor mediante IP+PUERTO
                socketClient = new Socket("192.168.56.120", Integer.parseInt(txtpuerto.getText()));
                Conexion.ip = "192.168.56.120";
                Conexion.puerto = Integer.parseInt(txtpuerto.getText());

            } catch (Exception e) {
                if (e.getClass().getName().equals("java.net.ConnectException")) {
                    txtaoe.setText("No se ha podido conectar");
                    lblconexion.setText("Desconectado");

                }

            }
            

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (socketClient == null) {
            JOptionPane.showMessageDialog(rootPane, "El cliente no está conectado", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (socketClient.isClosed()) {
            JOptionPane.showMessageDialog(rootPane, "El cliente ya está desconectado", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                //mando orden salir, cierro conexion y resto num
                HiloCliente hilo = new HiloCliente(socketClient, "Salir", txtaoe, lblconexion);
                hilo.start();
                hilo.join();

                
                socketClient.close();

                
                lblconexion.setText("DESCONECTADO");
                

                txtaoe.setText("Saliendo...");
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (socketClient == null || !socketClient.isClosed() || !socketClient.isConnected()) {
            hc = new HiloCliente(socketClient, "Time", txtaoe, lblconexion);
            hc.start();
            try {
                hc.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
    if (socketClient == null || !socketClient.isClosed() || !socketClient.isConnected()) {
     
        hc = new HiloCliente(socketClient, "Cuantos", txtaoe, lblconexion);
        hc.start();
     }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Cliente().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel lblconexion;
    private javax.swing.JTextArea txtaoe;
    private javax.swing.JTextField txtpuerto;
    private JButton btnNewButton;
}
