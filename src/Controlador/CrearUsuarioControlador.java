package Controlador;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

public class CrearUsuarioControlador {

	public static void traducir(ArrayList<JLabel> listalabels, ArrayList<JButton> listabotones) {
		ResourceBundle rb = ResourceBundle.getBundle("Idiomas.Idioms");
		for (JLabel lbl: listalabels) {
			if(lbl.getName() == null || lbl.getName().equals("")) {
				
			}else {
			lbl.setText(rb.getString(lbl.getName()));
			}
		}
		for (JButton btn: listabotones) {
			if(btn != null) {
				btn.setText(rb.getString(btn.getName()));
			}
		}
	}

	public static boolean Corrector(String email, String psswd, String psswdrepeat) {
		if(!psswd.equals(psswdrepeat)) {
			JOptionPane.showMessageDialog(null,
					"La contraseña no es igual", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
