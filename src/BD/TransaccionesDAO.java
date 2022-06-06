package BD;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Conexion;
import Modelo.Transaccion;
import Vista.LogginScreen;
import Vista.Hilos.HiloCliente;

public class TransaccionesDAO implements ITransaccionesDAO {
	Socket socketClient;
	HiloCliente hc;

	@Override
	public void insertarMovimiento(Transaccion t) {
		try {
			socketClient = new Socket(Conexion.ip, Conexion.puerto);
			hc = new HiloCliente(socketClient, "crearMovimiento", "insert into transacciones (email_usuario,transaccion,isbn_comic) values('" + t.getUsumail()
					+ "','" + t.getTransaccion() + "','" + t.getIsbncomic() + "');");
			hc.start();
			hc.join();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void getTransacciones() {
		try {
			socketClient = new Socket(Conexion.ip, Conexion.puerto);
			if (socketClient == null || !socketClient.isClosed() || !socketClient.isConnected()) {
				hc = new HiloCliente(socketClient, "getTransacciones",
						"Select * from transacciones");
				hc.start();
				hc.join();
			}
		} catch (InterruptedException ex) {
			// System.out.println("ERROR");
			ex.printStackTrace();
			Logger.getLogger(LogginScreen.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
