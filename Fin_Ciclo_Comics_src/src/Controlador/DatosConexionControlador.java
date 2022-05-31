package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DatosConexionControlador {
	public static void traducir(ResourceBundle rb, ArrayList<JLabel> listalabels, ArrayList<JButton> listabotones) {
		for (JLabel lbl : listalabels) {
			lbl.setText(rb.getString(lbl.getName()));

		}
		for (JButton btn : listabotones) {
			btn.setText(rb.getString(btn.getName()));
		}
	}

	public static void escribirFichero(Properties prop,JTextField txtPuerto, JTextField txtIP) throws IOException {
		prop.clear();
		prop.setProperty("Puerto", txtPuerto.getText());
		prop.setProperty("IP", txtIP.getText());
		String path = "./src/data/Connexion.properties";
		File f=new File(path);
		FileOutputStream text=new FileOutputStream(f);
		prop.store(text, null);
		text.close();
	}
}
