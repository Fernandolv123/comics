package VersionFinal;

import BD.Conexion;
import Modelo.Coleccion;
import Modelo.Comic;
import Modelo.Transaccion;
import Modelo.Usuario;
import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author fernandolv
 */
public class HiloServidorTrabajo extends Thread implements Serializable {

    Socket socketCliente;
    private ResourceBundle rb = ResourceBundle.getBundle("Idiomas.Idioms");

    HiloServidorTrabajo(Socket skCliente) {
        this.socketCliente = skCliente;

    }

    @Override
    public void run() {
        try {
            String peticion = "";
            String consulta = "";

            // Realiza las peticiones mientras no se pida desconectar el cliente
            while (!peticion.equalsIgnoreCase("Salir")) {
                // Creamos los flujos
                InputStream aux = socketCliente.getInputStream();

                // DataInputStream flujo_entrada = new DataInputStream(aux);
                ObjectInputStream flujo_entrada = new ObjectInputStream(aux);

                OutputStream out = socketCliente.getOutputStream();

                // DataOutputStream flujo_salida = new DataOutputStream(out);
                ObjectOutputStream flujo_salida = new ObjectOutputStream(out);
                // flujo_salida.writeObject(out);

                peticion = flujo_entrada.readObject().toString();

                switch (peticion) {
                    case "getColecciones":
                        ArrayList<Coleccion> listaCol = new ArrayList();
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            ResultSet resul = sentencia.executeQuery();
                            while (resul.next()) {
                                listaCol.add(new Coleccion(resul.getInt(1), resul.getString(2)));
                            }
                            Conexion.Close();
                        } catch (SQLException e) {
                            flujo_salida.writeObject("Error");
                            //JOptionPane.showConfirmDialog(null, rb.getString("JOErrorAlConectarBD"),"Error",JOptionPane.CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                            //e.printStackTrace();
                        }
                        ;
                        flujo_salida.writeObject(listaCol);
                        break;
                    case "getComics":
                        ArrayList<Comic> listaC = new ArrayList();
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            ResultSet resul = sentencia.executeQuery();
                            while (resul.next()) {
                                listaC.add(new Comic(resul.getString(3), resul.getInt(6), resul.getString(4),
                                        resul.getString(1), new Coleccion(resul.getInt(10), resul.getString(11)),
                                        resul.getString(5), resul.getFloat(7), resul.getBytes(8), resul.getString(9)));
                            }
                            Conexion.Close();
                        } catch (SQLException e) {
                            flujo_salida.writeObject("Error");
                            //JOptionPane.showConfirmDialog(null, rb.getString("JOErrorAlConectarBD"),"Error",JOptionPane.CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                            //e.printStackTrace();
                        }
                        ;
                        flujo_salida.writeObject(listaC);
                        break;
                    case "getUsuarios":
                        ArrayList<Usuario> listaU = new ArrayList();
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            ResultSet resul = sentencia.executeQuery();
                            while (resul.next()) {
                                listaU.add(new Usuario(resul.getString(1), resul.getString(2), resul.getBytes(3)));
                                System.out.println(listaU);
                            }
                            Conexion.Close();
                        } catch (SQLException e) {
                            flujo_salida.writeObject("Error");
                            //JOptionPane.showConfirmDialog(null, rb.getString("JOErrorAlConectarBD"),"Error",JOptionPane.CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                            //e.printStackTrace();
                        }
                        ;
                        System.out.println(listaU.size());
                        flujo_salida.writeObject(listaU);
                        break;
                    case "deleteComic":
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            sentencia.executeUpdate();
                            Conexion.getConnection().close();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        ;
                        //flujo_salida.writeObject(listaC);
                        break;
                    case "updateComic":
                        consulta = flujo_entrada.readObject().toString();
                        System.out.println(consulta);
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            byte[] bite = (byte[]) flujo_entrada.readObject();
                            System.out.println(bite.length);
                            sentencia.setBytes(1,  bite);
                            sentencia.executeUpdate();
                            Conexion.getConnection().close();
                            Conexion.Close();
                        } catch (SQLException e) {
                        }
                        ;
                        break;
                    case "insertComic":
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            System.out.println(consulta);
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            byte[] bite = (byte[]) flujo_entrada.readObject();
                            sentencia.setBytes(1,  bite);
                            sentencia.executeUpdate();
                            Conexion.Close();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        ;
                        break;
                    case "getUser":
                        Usuario u = new Usuario();
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            ResultSet resul = sentencia.executeQuery();

                            while (resul.next()) {
                                System.out.println(resul.getString(1)+", "+resul.getString(2)+", "+resul.getBytes(3));
                                u = new Usuario(resul.getString(1), resul.getString(2), resul.getBytes(3));
                            }
                            flujo_salida.writeObject(u);
                            Conexion.Close();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            break;
                        }
                        ;
                        
                        break;
                    case "insertUser":
                        System.out.println("Entra");
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            byte[] bite = (byte[]) flujo_entrada.readObject();
                            System.out.println(bite.length);
                            sentencia.setBytes(1,  bite);
                            ResultSet resul = sentencia.executeQuery();
                            Conexion.Close();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        ;
                        break;
                    case "deleteUser":
                        // WIP
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            sentencia.executeUpdate();
                            Conexion.Close();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        ;
                        break;
                    case "informe":
                        System.out.println("asd");
                        //flujo_salida.writeObject(Conexion.getConnection());
                        break;
                    case "crearMovimiento":
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            sentencia.executeUpdate();
                            Conexion.Close();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        ;
                        break;
                    case "getTransacciones":
                        ArrayList<Transaccion> listat = new ArrayList();
                        consulta = flujo_entrada.readObject().toString();
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            ResultSet resul = sentencia.executeQuery();
                            while (resul.next()) {
                                listat.add(new Transaccion(resul.getString(2), resul.getString(3), resul.getString(4)));
                            }
                            Conexion.Close();
                        } catch (SQLException e) {
                            flujo_salida.writeObject("Error");
                            //JOptionPane.showConfirmDialog(null, rb.getString("JOErrorAlConectarBD"),"Error",JOptionPane.CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                            //e.printStackTrace();
                        }
                        ;
                        flujo_salida.writeObject(listat);
                        break;
                    case "Salir":
                        HiloServidor.nc--;
                        flujo_salida.writeUTF("Saliendo...");
                        break;
                    default:
                        break;
                }
                flujo_salida.flush();

            }

            // Se cierra la conexi??n
            socketCliente.close();
            // Servidor.restarNumClientes();

        } catch (IOException ex) {

            // Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloServidorTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

}
