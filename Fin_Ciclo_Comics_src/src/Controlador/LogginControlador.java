package Controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class LogginControlador {
	

	public static void traductor(ResourceBundle rb, ArrayList<JLabel> listalabels, ArrayList<JButton> listabotones, JLabel lblConexion, boolean conectado, JMenuItem mnitmAyuda, JMenu mnAyuda) {
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
		mnitmAyuda.setText(rb.getString(mnitmAyuda.getName()));
		mnAyuda.setText(rb.getString(mnAyuda.getName()));
		
		if(conectado){
			lblConexion.setText(rb.getString("lblConexionCon"));
		}else {
			lblConexion.setText(rb.getString("lblConexionDes"));
		}
	}

	public static void updateProperties(InputStream inpstr,Properties prop, JTextField txtIP, JTextField txtPuerto) {
		try {
			prop.load(inpstr);
			txtIP.setText(prop.getProperty("IP"));
			txtPuerto.setText(prop.getProperty("Puerto"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
