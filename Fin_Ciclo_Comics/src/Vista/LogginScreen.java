package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.ComicsDAO;
import Controlador.LogginControlador;
import Modelo.Conexion;
import Modelo.Usuario;
import Vista.Hilos.HiloCliente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;

public class LogginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtPuerto = new JTextField();
	private JTextField txtIP = new JTextField();
	private JTextField textField_3;
	private JLabel lblconexion;
	private JLabel lblPuerto;
	private JLabel lblEmail;
	private JLabel lblContrasenha;
	private JLabel lblIP;
	private JMenuItem mnitmEspanhol;
	private JMenuItem mnitmGallego;
	private JMenuItem mnitmIngles;
	private JButton btntest;
	private JButton btnDesconectar;

	private Properties prop = new Properties();
	// private static InputStream inpstr = null;
	private InputStream inpstr = this.getClass().getClassLoader().getResourceAsStream("./Connexion.properties");
	Socket socketClient;
	HiloCliente hc;
	boolean encendido = false;
	private ResourceBundle rb = ResourceBundle.getBundle("Idiomas.idioms");
	private ArrayList<JLabel> listalabels = new ArrayList<JLabel>();
	private ArrayList<JButton> listabotones = new ArrayList<JButton>();
	private JButton btnCrearUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// inpstr = new FileInputStream("./Connexion.properties");
					LogginScreen frame = new LogginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogginScreen() {
		LogginControlador.updateProperties(inpstr,prop,txtIP,txtPuerto);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 342);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnIdioma = new JMenu("Idioma");
		mnIdioma.setName("mnIdioma");
		menuBar.add(mnIdioma);

		mnitmEspanhol = new JMenuItem("Espa\u00F1ol");
		mnitmEspanhol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es", "ES"));
				rb = ResourceBundle.getBundle("Idiomas.Idioms");
				LogginControlador.traductor(rb, listalabels, listabotones);
				setTitle(rb.getString("tituloCliente"));
				mnIdioma.setText(rb.getString(mnIdioma.getName()));
				mnitmEspanhol.setText(rb.getString(mnitmEspanhol.getName()));
				mnitmGallego.setText(rb.getString(mnitmGallego.getName()));
				mnitmIngles.setText(rb.getString(mnitmIngles.getName()));
			}
		});
		mnitmEspanhol.setName("mnitmEspanhol");
		mnIdioma.add(mnitmEspanhol);

		mnitmGallego = new JMenuItem("Gallego");
		mnitmGallego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("gl", "ES"));
				rb = ResourceBundle.getBundle("Idiomas.Idioms");
				LogginControlador.traductor(rb, listalabels, listabotones);
				setTitle(rb.getString("tituloCliente"));
				mnIdioma.setText(rb.getString(mnIdioma.getName()));
				mnitmEspanhol.setText(rb.getString(mnitmEspanhol.getName()));
				mnitmGallego.setText(rb.getString(mnitmGallego.getName()));
				mnitmIngles.setText(rb.getString(mnitmIngles.getName()));
			}
		});
		mnitmGallego.setName("mnitmGallego");
		mnIdioma.add(mnitmGallego);

		mnitmIngles = new JMenuItem("Ingles");
		mnitmIngles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en", "GB"));
				rb = ResourceBundle.getBundle("Idiomas.Idioms");
				LogginControlador.traductor(rb, listalabels, listabotones);
				setTitle(rb.getString("tituloCliente"));
				mnIdioma.setText(rb.getString(mnIdioma.getName()));
				mnitmEspanhol.setText(rb.getString(mnitmEspanhol.getName()));
				mnitmGallego.setText(rb.getString(mnitmGallego.getName()));
				mnitmIngles.setText(rb.getString(mnitmIngles.getName()));
			}
		});
		mnitmIngles.setName("mnitmIngles");
		mnIdioma.add(mnitmIngles);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(51, 54, 67, 14);
		lblPuerto.setName("lblPuerto");

		txtPuerto.setBounds(121, 51, 67, 20);
		txtPuerto.setEditable(false);

		lblIP = new JLabel("IP");
		lblIP.setBounds(198, 54, 26, 14);
		lblIP.setName("lblIP");

		txtIP.setBounds(234, 52, 128, 20);
		txtIP.setEditable(false);
		txtIP.setColumns(10);

		btntest = new JButton();
		btntest.setBounds(121, 82, 241, 23);
		btntest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (socketClient != null) {
					if (!socketClient.isClosed()) {
						JOptionPane.showMessageDialog(rootPane,
								"El cliente ya está iniciado (desconecte y vuelva a conectar)", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				// Si lo hemos conectado al menos 1 vez y desconectado volvemos a conectar
				lblconexion.setText("Conectado");
				System.out.println(txtIP.getText()+" : ");
					System.out.println(Integer.parseInt(txtPuerto.getText()));
				try {

					// Nos Conectamos a un Servidor mediante IP+PUERTO
					socketClient = new Socket(txtIP.getText(), Integer.parseInt(txtPuerto.getText()));
					Conexion.ip = txtIP.getText();
					Conexion.puerto = Integer.parseInt(txtPuerto.getText());

				} catch (Exception ex) {
					if (e.getClass().getName().equals("java.net.ConnectException")) {
						// txtaoe.setText("No se ha podido conectar");
						lblconexion.setText("Desconectado");

					}

				}

			}
		});
		btntest.setText("Conectar");
		btntest.setName("btnTest");

		btnDesconectar = new JButton();
		btnDesconectar.setBounds(251, 116, 111, 23);
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (socketClient == null) {
					JOptionPane.showMessageDialog(rootPane, "El cliente no está conectado", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (socketClient.isClosed()) {
					JOptionPane.showMessageDialog(rootPane, "El cliente ya está desconectado", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						// mando orden salir, cierro conexion y resto num
						HiloCliente hilo = new HiloCliente(socketClient, "Salir", lblconexion);
						hilo.start();
						hilo.join();

						socketClient.close();

						lblconexion.setText("Desconectado");

						// txtaoe.setText("Saliendo...");
					} catch (IOException ex) {
						Logger.getLogger(LogginScreen.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InterruptedException ex) {
						Logger.getLogger(LogginScreen.class.getName()).log(Level.SEVERE, null, ex);
					}

				}
			}
		});
		btnDesconectar.setText("Desconectar");
		btnDesconectar.setName("btnDesconectar");

		lblContrasenha = new JLabel("Contrase\u00F1a");
		lblContrasenha.setBounds(51, 192, 67, 14);
		lblContrasenha.setName("lblContrasenha");

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(51, 154, 67, 14);
		lblEmail.setName("lblEmail");

		textField_3 = new JTextField();
		textField_3.setBounds(121, 151, 241, 20);
		textField_3.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(121, 220, 103, 23);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Usuario u = new Usuario();
				InfoUser iu = new InfoUser(u);
				iu.setVisible(true);
				dispose();*/
				
				
				ComicsDAO cdao = new ComicsDAO();
				try { 
					TablaComicScreen tcs = new TablaComicScreen();
					tcs.setVisible(true);
					} catch (UnknownHostException e1) {
					  e1.printStackTrace();
				  } catch (IOException e1){
					  e1.printStackTrace();
				  }
				
			}
		});
		btnConectar.setName("btnConectar");
		btnConectar.setMinimumSize(new Dimension(0, 0));

		lblconexion = new JLabel();
		lblconexion.setBounds(121, 11, 178, 29);
		lblconexion.setText("Desconectado");
		lblconexion.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JButton btnChange = new JButton("Cambiar");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosConexion dc = new DatosConexion(prop);
				dc.setVisible(true);
				LogginControlador.updateProperties(inpstr,prop,txtIP,txtPuerto);
			}
		});
		btnChange.setBounds(121, 116, 100, 23);
		btnChange.setName("btnChange");

		contentPane.setLayout(null);
		contentPane.add(lblconexion);
		contentPane.add(lblPuerto);
		contentPane.add(txtPuerto);
		contentPane.add(lblIP);
		contentPane.add(txtIP);
		contentPane.add(btntest);
		contentPane.add(btnChange);
		contentPane.add(btnDesconectar);
		contentPane.add(lblEmail);
		contentPane.add(textField_3);
		contentPane.add(lblContrasenha);
		contentPane.add(btnConectar);

		btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario createuser = new CrearUsuario();
				createuser.setVisible(true);
			}
		});
		btnCrearUsuario.setName("btnCrearUsuario");
		btnCrearUsuario.setMinimumSize(new Dimension(0, 0));
		btnCrearUsuario.setBounds(234, 220, 128, 23);
		contentPane.add(btnCrearUsuario);
		listalabels.add(lblPuerto);
		listalabels.add(lblconexion);
		listalabels.add(lblIP);
		listalabels.add(lblContrasenha);
		listalabels.add(lblEmail);
		listabotones.add(btnConectar);
		listabotones.add(btnChange);
		listabotones.add(btntest);
		listabotones.add(btnCrearUsuario);

		passwordField = new JPasswordField();
		passwordField.setBounds(121, 189, 241, 20);
		contentPane.add(passwordField);
	}
}
