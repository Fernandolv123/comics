package Controlador;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Modelo.Coleccion;
import Modelo.Comic;
import Vista.Hilos.HiloCliente;

public class InfoComicControlador {
	
	public static void RellenarCombo(JComboBox cmbCol) {
		ArrayList<String> listas= new ArrayList();
		for (Coleccion c: HiloCliente.listaCol) {
			cmbCol.addItem(c.getNombre());
		}
	}

	public static boolean IsWellDone(Comic c, String isbn) {
		String cant = c.getCantidad()+"";
		String precio = c.getPrecio()+"";
		if(c.isFormat()) {

			return true;
		}
		return false;
	}
}
