package Controlador;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JComboBox;

import Modelo.Coleccion;
import Modelo.Comic;
import Vista.Hilos.HiloCliente;

public class TablaComicControlador {

	public static ArrayList<Comic> sort(String sortCol, String name) {
		ArrayList<Comic> nuevalista = new ArrayList();
		for (Comic c : HiloCliente.listaC) {
			if ((c.getColection().getNombre().equals(sortCol) && c.getNombre().startsWith(name))) {
				nuevalista.add(c);
			} else if (c.getNombre().startsWith(name) && sortCol.equals("*")) {
				nuevalista.add(c);
			}
		}
		return nuevalista;

	}

	public static void RellenarCombo(JComboBox cmbCol,ResourceBundle rb) {
		for (Coleccion c : HiloCliente.listaCol) {
			cmbCol.addItem(rb.getString(c.getNombre()));
		}
	}

}
