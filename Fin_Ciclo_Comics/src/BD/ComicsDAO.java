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
import Vista.LogginScreen;
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
            	//System.out.println("ERROR");
            	ex.printStackTrace();
                Logger.getLogger(LogginScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		return HiloCliente.listaC;
	}

	public Comic getComic(String valueAt) {
		for (Comic c : HiloCliente.listaC) {
			if (c.getISBN().equals(valueAt)) {
				return c;
			}
		}
		return null;
	}
	
	public void deleteComic(Comic c) throws InterruptedException, UnknownHostException, IOException {
		socketClient = new Socket(Conexion.ip,Conexion.puerto);
		HiloCliente.listaC.remove(c);
        hc = new HiloCliente(socketClient, "deleteComic", "delete from comics where isbn=\"" + c.getISBN() + "\"");
        hc.start();
        hc.join();
        
	}

	@Override
	public void insertComic(Comic c) {
		try {
		socketClient = new Socket(Conexion.ip,Conexion.puerto);
		// insert into comics values(/*id_copleccion,nombre,isbn,genero,autor,cantidad,precio,foto,descripcion*/)
		hc = new HiloCliente(socketClient, "insertUser", "insert into usuarios values(\""+c.getAutor()+"\",\""+c.getAutor()+"\",?)", c.getImg());
		hc.start();
		hc.join();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
