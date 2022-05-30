package Controlador;

import java.util.ArrayList;

import javax.swing.JComboBox;

import Modelo.Comic;
import Vista.Hilos.HiloCliente;

public class InfoComicControlador {
	
	public static void RellenarCombo(JComboBox cmbCol) {
		ArrayList<String> listas= new ArrayList();
		for (Comic c: HiloCliente.listaC) {
			if (!listas.contains(c.getColection().getNombre())) {
				listas.add(c.getColection().getNombre());
				cmbCol.addItem(c.getColection().getNombre());
			}
		}
	}
}
