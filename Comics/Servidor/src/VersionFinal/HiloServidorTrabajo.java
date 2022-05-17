package VersionFinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
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
public class HiloServidorTrabajo extends Thread {

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
                
                switch (peticion) {
                    case "getComics":
                        //flujo_entrada.readObject();
                        break;
                    case "GetSortedComics":
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
                        flujo_salida.writeUTF("Total clientes conectados: "+ HiloServidor.nc);
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
        }
        finally{
            
        }
    }

}
