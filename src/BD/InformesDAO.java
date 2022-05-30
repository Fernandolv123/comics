package BD;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

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
    
	public void getInforme(String informe) {
		try {
			socketClient = new Socket(Conexion.ip,Conexion.puerto);
			hc = new HiloCliente(socketClient, "informe", "asd");
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
}
