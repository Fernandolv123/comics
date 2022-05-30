package Controlador;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;

public class InfoUserControler {

	public static void traducir(ArrayList<JLabel> listalabels, ArrayList<JButton> listabotones) {
		ResourceBundle rb = ResourceBundle.getBundle("Idiomas.Idioms");
		for (JLabel lbl : listalabels) {
			lbl.setText(rb.getString(lbl.getName()));
		}
			
		for (JButton btn : listabotones) {
			btn.setText(rb.getString(btn.getName()));
		}
	}

}
