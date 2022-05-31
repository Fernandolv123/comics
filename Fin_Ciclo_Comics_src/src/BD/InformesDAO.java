package BD;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JComboBox;

import Modelo.Conexion;
import Vista.Hilos.HiloCliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InformesDAO {
    Socket socketClient;
    HiloCliente hc;
    
	public void getInformeComics(String informe) {
		try {
			socketClient = new Socket(Conexion.ip,Conexion.puerto);
			hc = new HiloCliente(socketClient, "informeComics", informe);
			try {
				hc.start();
				hc.join();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getInformeUsuarios(String informe) {
		try {
			socketClient = new Socket(Conexion.ip,Conexion.puerto);
			hc = new HiloCliente(socketClient, "informeUsuarios", informe);
			System.out.println("Entra"+HiloCliente.listaU);
				hc.start();
				hc.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getInformeTransacciones(String informe) {
		try {
			socketClient = new Socket(Conexion.ip,Conexion.puerto);
			hc = new HiloCliente(socketClient, "informeTransacciones", informe);
			System.out.println("Entra"+HiloCliente.listaU);
				hc.start();
				hc.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getInformeTransaccionesPorTipo(String informe) {
		try {
			socketClient = new Socket(Conexion.ip,Conexion.puerto);
			hc = new HiloCliente(socketClient, "informeTransaccionesConcretas", informe);
				hc.start();
				hc.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
