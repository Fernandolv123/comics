package Controlador;

import java.util.ArrayList;

import javax.swing.JComboBox;

import Modelo.Comic;
import Vista.Hilos.HiloCliente;

public class TablaComicControlador {

	public static ArrayList<Comic> sort(String sortCol, String name) {
		ArrayList<Comic> nuevalista= new ArrayList();
		for (Comic c: HiloCliente.listaC) {
			if ((c.getColection().getNombre().equals(sortCol) && c.getNombre().startsWith(name))) {
				nuevalista.add(c);
			}else if(c.getNombre().startsWith(name)  && sortCol.equals("*")) {
				nuevalista.add(c);
			}
		}
		return nuevalista;
		
	}

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
