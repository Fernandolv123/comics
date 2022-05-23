package VersionFinal;

import BD.Conexion;
import Modelo.Coleccion;
import Modelo.Comic;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernandolv
 */
public class HiloServidorTrabajo extends Thread implements Serializable {

    Socket socketCliente;

    HiloServidorTrabajo(Socket skCliente) {
        this.socketCliente = skCliente;

    }

    @Override
    public void run() {
        try {
            String peticion = "";

            //Realiza las peticiones mientras no se pida desconectar el cliente
            while (!peticion.equalsIgnoreCase("Salir")) {
                //Creamos los flujos
                InputStream aux = socketCliente.getInputStream();

                //DataInputStream flujo_entrada = new DataInputStream(aux);              
                ObjectInputStream flujo_entrada = new ObjectInputStream(aux);

                OutputStream out = socketCliente.getOutputStream();

                //DataOutputStream flujo_salida = new DataOutputStream(out);
                ObjectOutputStream flujo_salida = new ObjectOutputStream(out);
                //flujo_salida.writeObject(out);

                peticion = flujo_entrada.readObject().toString();
                ArrayList<Comic> listaC = new ArrayList();
                switch (peticion) {
                    case "getComics":
                        String consulta = flujo_entrada.readObject().toString();
                        System.out.println(consulta);
                        try {
                            PreparedStatement sentencia = Conexion.getConnection().prepareStatement(consulta);
                            ResultSet resul = sentencia.executeQuery();
                            while (resul.next()) {
                                listaC.add(new Comic(resul.getString(2), resul.getInt(6),resul.getString(4),resul.getString(3),new Coleccion(resul.getInt(9), resul.getString(10)),resul.getString(5),resul.getFloat(7),resul.getBytes(8)));
                            }
                            Conexion.Close();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        };
                        flujo_salida.writeObject(listaC);
                        break;
                    case "GetSortedComics":

                        flujo_salida.writeObject(listaC);
                        break;
                    case "":
                        break;
                    case "Time":
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        Date now = Date.from(Instant.now());
                        String hora = format.format(now);

                        //Atendemos al cliente
                        flujo_salida.writeUTF("Hora actual: " + hora);
                        break;
                    case "Cuantos":
                        //Atendemos al cliente
                        flujo_salida.writeUTF("Total clientes conectados: " + HiloServidor.nc);
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

            // Se cierra la conexión
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
