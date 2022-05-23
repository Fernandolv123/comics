package BD;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Comic;
import Modelo.Conexion;
import Vista.Cliente;
import Vista.Hilos.HiloCliente;

public class ComicsDAO implements IComicsDAO{
    Socket socketClient;
    HiloCliente hc;
    
	public ArrayList<Comic> obtenerComics() throws UnknownHostException, IOException {
		socketClient = new Socket(Conexion.ip,Conexion.puerto);
        if (socketClient == null || !socketClient.isClosed() || !socketClient.isConnected()) {
            hc = new HiloCliente(socketClient, "getComics", "Select * from Comics inner join colecciones on comics.id_coleccion = colecciones.id");
            // Select * from Comics inner join colecciones on comics.id_coleccion = colecciones.id
            hc.start();
            try {
                hc.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		return HiloCliente.listaC;
	}

	public Comic getComic(String valueAt) {
		for (Comic c : HiloCliente.listaC) {
			if (c.getIVN().equals(valueAt)) {
				return c;
			}
		}
		return null;
	}
	
}
