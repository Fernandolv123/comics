/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Hilos;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import BD.UsuariosDAO;
import Modelo.Coleccion;
import Modelo.Comic;
import Modelo.Transaccion;
import Modelo.Usuario;
import Vista.InfoUser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fernandolv
 */
public class HiloCliente extends Thread implements Serializable {

	Socket socketCliente;
	String orden;
	JTextArea txtaoe;
	JLabel lblcon;
	String consulta;
	byte[] img;
	private UsuariosDAO udao = new UsuariosDAO();
	public static boolean broke = false;
	public static ArrayList<Comic> listaC = new ArrayList<Comic>();
	public static ArrayList<Usuario> listaU = new ArrayList<Usuario>();
	public static ArrayList<Transaccion> listat = new ArrayList<Transaccion>();
	public static ArrayList<Coleccion> listaCol = new ArrayList<Coleccion>();

	public HiloCliente() {
	}

	public HiloCliente(Socket socketCliente, String orden, JTextArea txtaoe, JLabel lblcon) {
		this.socketCliente = socketCliente;
		this.orden = orden;
		this.txtaoe = txtaoe;
		this.lblcon = lblcon;

	}

	public HiloCliente(Socket socketCliente, String orden, String consulta) {
		this.socketCliente = socketCliente;
		this.orden = orden;
		this.consulta = consulta;
	}

	public HiloCliente(Socket socketCliente, String orden, String consulta, byte[] img) {
		this.socketCliente = socketCliente;
		this.orden = orden;
		this.consulta = consulta;
		this.img = img;
	}

	public HiloCliente(Socket socketCliente, String orden, JLabel lblcon) {
		this.socketCliente = socketCliente;
		this.orden = orden;
		this.lblcon = lblcon;

	}

	@Override
	public void run() {
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		// DataInputStream entrada = null;
		// DataOutputStream salida = null;

		String opcionsalidaserver;
		if (socketCliente != null) {
			try {
				salida = new ObjectOutputStream(socketCliente.getOutputStream());
				entrada = new ObjectInputStream(socketCliente.getInputStream());
				salida.writeObject(orden);
				salida.writeObject(consulta);
				switch (orden) {
				case "getComics":
					listaC = (ArrayList<Comic>) entrada.readObject();
					break;
				case "getUsuarios":
					listaU = (ArrayList<Usuario>) entrada.readObject();
					break;
				case "getColecciones":
					listaCol = (ArrayList<Coleccion>) entrada.readObject();
					break;
				case "insertUser":
					salida.writeObject(img);
					break;
				case "getUser":
					Usuario u = (Usuario) entrada.readObject();
					System.out.println(u);
					Usuario.miUser(u);
					break;
				case "insertComic":
					salida.writeObject(img);
					break;
				case "informeComics":
					try {
						JasperReport report = JasperCompileManager.compileReport(consulta);
						JasperPrint visor = JasperFillManager.fillReport(report, null,
								new JRBeanCollectionDataSource(HiloCliente.listaC));
						JasperViewer.viewReport(visor, false);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "informeUsuarios":
					try {
						UsuariosDAO udao = new UsuariosDAO();
						JasperReport reporte = JasperCompileManager.compileReport(consulta);
						JasperPrint visora = JasperFillManager.fillReport(reporte, null,
								new JRBeanCollectionDataSource(HiloCliente.listaU));
						JasperViewer.viewReport(visora, false);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "updateComic":
					salida.writeObject(img);
					break;
				case "crearMovimiento":
					break;
				case "getTransacciones":
					listat = (ArrayList<Transaccion>) entrada.readObject();
					break;
				case "informeTransacciones":
					try {
						ArrayList<Transaccion> nuevalistat = new ArrayList<Transaccion>();
						for (Transaccion transaccion : HiloCliente.listat) {
							if (transaccion.getUsumail().equals(Usuario.miUser().getEmail())) {
								nuevalistat.add(transaccion);
							}
						}
						JasperReport report = JasperCompileManager.compileReport(consulta);
						JasperPrint visor = JasperFillManager.fillReport(report, null,
								new JRBeanCollectionDataSource(nuevalistat));
						JasperViewer.viewReport(visor, false);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "informeTransaccionesConcretas":
					try {
						ArrayList<Transaccion> nuevalistat = new ArrayList<Transaccion>();
						for (Transaccion transaccion : HiloCliente.listat) {
							if (transaccion.getTransaccion()
									.equals(InfoUser.cmbtransaccion.getSelectedItem().toString())) {
								nuevalistat.add(transaccion);
							}
						}
						JasperReport report = JasperCompileManager.compileReport(consulta);
						JasperPrint visor = JasperFillManager.fillReport(report, null,
								new JRBeanCollectionDataSource(nuevalistat));
						JasperViewer.viewReport(visor, false);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			} catch (IOException | ClassNotFoundException ex) {
				try {
					ex.printStackTrace();
					// lblcon.setText("Desconectado");
					// txtaoe.setText("No se ha podido conectar");
					socketCliente.close();
					broke = true;
				} catch (IOException ex1) {
					broke = true;
					Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex1);
				}

			} catch (ClassCastException ex) {
				broke = true;
			}
		} else {
			System.out.println("Error");
		}
	}

}
