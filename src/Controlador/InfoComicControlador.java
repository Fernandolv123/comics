package Controlador;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Modelo.Coleccion;
import Modelo.Comic;
import Vista.Hilos.HiloCliente;

public class InfoComicControlador {
	
	public static void RellenarCombo(JComboBox cmbCol,ResourceBundle rb) {
		System.out.println(HiloCliente.listaCol);
		for (Coleccion c: HiloCliente.listaCol) {
			System.out.println(c.getNombre() +" | "+rb.getString(c.getNombre()));
			cmbCol.addItem(rb.getString(c.getNombre()));
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
