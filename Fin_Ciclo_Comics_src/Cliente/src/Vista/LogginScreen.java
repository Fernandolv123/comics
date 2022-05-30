package Vista;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.ComicsDAO;
import BD.InformesDAO;
import BD.UsuariosDAO;
import Controlador.LogginControlador;
import Modelo.Comic;
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
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;

public class LogginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtPuerto = new JTextField();
	private JTextField txtIP = new JTextField();
	private JTextField txtemail;
	private JLabel lblConexion;
	private JLabel lblPuerto;
	private JLabel lblEmail;
	private JLabel lblContrasenha;
	private JLabel lblIP;
	private JMenuItem mnitmEspanhol;
	private JMenuItem mnitmGallego;
	private JMenuItem mnitmIngles;
	private JButton btntest;
	private JButton btnDesconectar = new JButton();

	private UsuariosDAO udao = new UsuariosDAO();
	private Properties prop = new Properties();
	// private static InputStream inpstr = null;
	private InputStream inpstr = this.getClass().getClassLoader().getResourceAsStream("./Connexion.properties");
	Socket socketClient;
	HiloCliente hc;
	boolean encendido = false;
	private ResourceBundle rb = ResourceBundle.getBundle("Idiomas.Idioms");
	private ArrayList<JLabel> listalabels = new ArrayList<JLabel>();
	private ArrayList<JButton> listabotones = new ArrayList<JButton>();
	private JButton btnCrearUsuario;
	private JPasswordField txtpasswd;
	private JMenu mnAyuda;
	private JMenuItem mnitmAyuda;

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
		
		
		URL urlhelp = this.getClass().getResource("/ayuda/help.hs");
		
		HelpSet helpset;
		try {
			helpset = new HelpSet(null,urlhelp);
			HelpBroker broker = helpset.createHelpBroker();
			broker.enableHelpOnButton(btnDesconectar, "LogginScreen", helpset);
			//broker.enableHelpKey(frmEjercicio.getRootPane(), "Menu", helpset);
		} catch (HelpSetException e2) {
			System.out.println("Va");
			e2.printStackTrace();
		}

		
		Usuario.miUser("emailaa", "contraseÒa", null);
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
				LogginControlador.traductor(rb, listalabels, listabotones, lblConexion,encendido,mnitmAyuda,mnAyuda);
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
				LogginControlador.traductor(rb, listalabels, listabotones, lblConexion,encendido,mnitmAyuda,mnAyuda);
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
				LogginControlador.traductor(rb, listalabels, listabotones, lblConexion, encendido,mnitmAyuda,mnAyuda);
				setTitle(rb.getString("tituloCliente"));
				mnIdioma.setText(rb.getString(mnIdioma.getName()));
				mnitmEspanhol.setText(rb.getString(mnitmEspanhol.getName()));
				mnitmGallego.setText(rb.getString(mnitmGallego.getName()));
				mnitmIngles.setText(rb.getString(mnitmIngles.getName()));
			}
		});
		mnitmIngles.setName("mnitmIngles");
		mnIdioma.add(mnitmIngles);
		
		mnAyuda = new JMenu("Ayuda"); //$NON-NLS-1$
		mnAyuda.setName(rb.getString("LogginScreen.mnNewMenu.name")); //$NON-NLS-1$
		menuBar.add(mnAyuda);
		
		mnitmAyuda = new JMenuItem("Ayuda"); //$NON-NLS-1$
		mnitmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnitmAyuda.setName("mnAyuda"); //$NON-NLS-1$
		mnAyuda.add(mnitmAyuda);
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
								"El cliente ya est√° iniciado (desconecte y vuelva a conectar)", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				try {

					socketClient = new Socket(txtIP.getText(), Integer.parseInt(txtPuerto.getText()));
					Conexion.ip = txtIP.getText();
					Conexion.puerto = Integer.parseInt(txtPuerto.getText());
					encendido = true;
					lblConexion.setText(rb.getString("lblConexionCon"));

				} catch (Exception ex) {
					if (ex.getClass().getName().equals("java.net.ConnectException")) {
						lblConexion.setText(rb.getString("lblConexionDes"));
						encendido = false;
						JOptionPane.showMessageDialog(rootPane, rb.getString("JOConexionFallida"), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btntest.setText(rb.getString("btnTest")); //$NON-NLS-1$
		btntest.setName("btnTest");

		btnDesconectar.setBounds(243, 116, 119, 23);
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (socketClient == null) {
					JOptionPane.showMessageDialog(rootPane, "El cliente no est· conectado", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (socketClient.isClosed()) {
					JOptionPane.showMessageDialog(rootPane, "El cliente ya est· desconectado", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						HiloCliente hilo = new HiloCliente(socketClient, "Salir", lblConexion);
						hilo.start();
						hilo.join();

						socketClient.close();

						lblConexion.setText(rb.getString("lblConexionDes"));
						encendido = false;

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

		lblEmail = new JLabel(rb.getString("lblEmail")); //$NON-NLS-1$
		lblEmail.setBounds(51, 154, 67, 14);
		lblEmail.setName("lblEmail");

		txtemail = new JTextField();
		txtemail.setText(rb.getString("LogginScreen.txtemail.text")); //$NON-NLS-1$
		txtemail.setBounds(121, 151, 241, 20);
		txtemail.setColumns(10);

		JButton btnConectar = new JButton(rb.getString("btnConectar")); //$NON-NLS-1$
		btnConectar.setBounds(121, 220, 111, 23);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*if (socketClient != null) {
					if (!socketClient.isClosed()) {
						JOptionPane.showMessageDialog(rootPane,
								"El cliente ya est√° iniciado (desconecte y vuelva a conectar)", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}*/
				lblConexion.setText(rb.getString("lblConexionCon"));
				encendido = true;
				try {

					socketClient = new Socket(txtIP.getText(), Integer.parseInt(txtPuerto.getText()));
					Conexion.ip = txtIP.getText();
					Conexion.puerto = Integer.parseInt(txtPuerto.getText());
					//Usuario.miUser(udao.getUser(txtemail.getText(), txtpasswd.getText()));
					udao.getUser(txtemail.getText(), txtpasswd.getText());
					if(Usuario.miUser().getEmail() == null) {
						JOptionPane.showMessageDialog(rootPane, rb.getString("JOUserNotFound"), "Error",
								JOptionPane.ERROR_MESSAGE);
					}else {
					Usuario u = new Usuario();
					InfoUser iu = new InfoUser();
					iu.setVisible(true);
					}

				} catch (Exception ex) {
					if (ex.getClass().getName().equals("java.net.ConnectException")) {
						lblConexion.setText(rb.getString("lblConexionDes"));
						encendido=false;
						JOptionPane.showMessageDialog(rootPane, rb.getString("JOConexionFallida"), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				
				
				/*try { 
					TablaComicScreen tcs = new TablaComicScreen();
					tcs.setVisible(true);
					} catch (UnknownHostException e1) {
						System.out.println("ERROR");
					  e1.printStackTrace();
				  } catch (IOException e1){
					  JOptionPane.showConfirmDialog(null, rb.getString("JOConexionFallida"),"Error",JOptionPane.CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
					  //e1.printStackTrace();
				  }*/
				
			}
		});
		btnConectar.setName("btnConectar");
		btnConectar.setMinimumSize(new Dimension(0, 0));

		lblConexion = new JLabel();
		lblConexion.setName("lblConexion");
		lblConexion.setBounds(121, 11, 178, 29);
		lblConexion.setText(rb.getString("lblConexionDes"));
		lblConexion.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JButton btnChange = new JButton("Cambiar");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosConexion dc = new DatosConexion(prop);
				dc.setVisible(true);
				LogginControlador.updateProperties(inpstr,prop,txtIP,txtPuerto);
			}
		});
		btnChange.setBounds(121, 116, 103, 23);
		btnChange.setName("btnChange");

		contentPane.setLayout(null);
		contentPane.add(lblConexion);
		contentPane.add(lblPuerto);
		contentPane.add(txtPuerto);
		contentPane.add(lblIP);
		contentPane.add(txtIP);
		contentPane.add(btntest);
		contentPane.add(btnChange);
		contentPane.add(btnDesconectar);
		contentPane.add(lblEmail);
		contentPane.add(txtemail);
		contentPane.add(lblContrasenha);
		contentPane.add(btnConectar);

		btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(encendido) {
					CrearUsuario createuser = new CrearUsuario();
					createuser.setVisible(true);
				} else {
					JOptionPane.showConfirmDialog(null, rb.getString("JOConexionFallida"),"Error",JOptionPane.CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrearUsuario.setName("btnCrearUsuario");
		btnCrearUsuario.setMinimumSize(new Dimension(0, 0));
		btnCrearUsuario.setBounds(243, 220, 119, 23);
		contentPane.add(btnCrearUsuario);
		listalabels.add(lblPuerto);
		listalabels.add(lblIP);
		listalabels.add(lblContrasenha);
		listalabels.add(lblEmail);
		listabotones.add(btnDesconectar);
		listabotones.add(btnConectar);
		listabotones.add(btnChange);
		listabotones.add(btntest);
		listabotones.add(btnCrearUsuario);

		txtpasswd = new JPasswordField();
		txtpasswd.setText(rb.getString("LogginScreen.txtpasswd.text")); //$NON-NLS-1$
		txtpasswd.setBounds(121, 189, 241, 20);
		contentPane.add(txtpasswd);
	}
}
