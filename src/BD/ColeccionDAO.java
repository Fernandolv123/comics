package BD;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Coleccion;
import Modelo.Comic;
import Modelo.Conexion;
import Vista.LogginScreen;
import Vista.Hilos.HiloCliente;

public class ColeccionDAO implements IColeccionDAO {
	Socket socketClient;
	HiloCliente hc;

	@Override
	public void getColeccion(Coleccion col) {
		for (Coleccion c : HiloCliente.listaCol) {
			if (c.getNombre().equals(col.getNombre())) {
				col.setId(c.getId());
			}
		}

	}

	@Override
	public ArrayList<Coleccion> getColecciones() {
		try {
			socketClient = new Socket(Conexion.ip, Conexion.puerto);
			if (socketClient == null || !socketClient.isClosed() || !socketClient.isConnected()) {
				hc = new HiloCliente(socketClient, "getColecciones",
						"Select * from colecciones");
				// Select * from Comics inner join colecciones on comics.id_coleccion =
				// colecciones.id
				hc.start();
				hc.join();
			}
		} catch (InterruptedException | IOException ex) {
			// System.out.println("ERROR");
			ex.printStackTrace();
			Logger.getLogger(LogginScreen.class.getName()).log(Level.SEVERE, null, ex);
		}

		return HiloCliente.listaCol;
	}

}
