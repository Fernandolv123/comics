package Controlador;

import java.util.ArrayList;

import javax.swing.JComboBox;

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
}
