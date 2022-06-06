package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.DatosConexionControlador;
import Modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DatosConexion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPuerto;
	private JTextField txtIP;
	private ResourceBundle rb= ResourceBundle.getBundle("Idiomas.idioms");
    private ArrayList<JLabel> listalabels= new ArrayList<JLabel>();
    private ArrayList<JButton> listabotones= new ArrayList<JButton>();
    private JLabel lblPuerto;
    private JLabel lblIP;
    private JButton btnGuardar;
    private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosConexion dialog = new DatosConexion(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosConexion(Properties prop) {
		setModal(true);
		setBounds(100, 100, 197, 184);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblPuerto = new JLabel("Puerto");
			lblPuerto.setName("lblPuerto");
			lblPuerto.setBounds(10, 31, 46, 14);
			contentPanel.add(lblPuerto);
		}
		{
			lblIP = new JLabel("IP");
			lblIP.setName("lblIP");
			lblIP.setBounds(10, 76, 46, 14);
			contentPanel.add(lblIP);
		}
		{
			txtPuerto = new JTextField();
			txtPuerto.setText(prop.getProperty("Puerto"));
			txtPuerto.setBounds(66, 28, 105, 20);
			contentPanel.add(txtPuerto);
			txtPuerto.setColumns(10);
		}
		{
			txtIP = new JTextField();
			txtIP.setText(prop.getProperty("IP"));
			txtIP.setColumns(10);
			txtIP.setBounds(66, 73, 105, 20);
			contentPanel.add(txtIP);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							DatosConexionControlador.escribirFichero(prop,txtPuerto,txtIP);
							dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnGuardar.setName("btnGuardar");
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setName("btnCancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		listalabels.add(lblPuerto);
		listalabels.add(lblIP);
		listabotones.add(btnCancelar);
		listabotones.add(btnGuardar);
		DatosConexionControlador.traducir(rb,listalabels,listabotones);
	}

}
