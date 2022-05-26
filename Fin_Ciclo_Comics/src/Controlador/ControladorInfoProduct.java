package Controlador;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ControladorInfoProduct {

	public static void traducir(ArrayList<JButton> listabotones, ArrayList<JLabel> listalabel, ResourceBundle rb) {
		for (JLabel lbl: listalabel) {
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

}
