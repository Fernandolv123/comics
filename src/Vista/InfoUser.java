package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import BD.ComicsDAO;
import BD.InformesDAO;
import BD.TransaccionesDAO;
import BD.UsuariosDAO;
import Controlador.InfoUserControler;
import Modelo.Usuario;
import Vista.Hilos.HiloCliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InfoUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblSaludo;
	private ArrayList<JButton> listabotones = new ArrayList<JButton>();
	private ArrayList<JLabel> listalabels = new ArrayList<JLabel>();
	private ResourceBundle rb = ResourceBundle.getBundle("Idiomas.Idioms");
	private JButton btnExplorar;
	private JButton btnCancelar;
	private JLabel lblInformes;
	private JLabel lblNewLabel;
	private InformesDAO iDAO = new InformesDAO();
	private UsuariosDAO uDAO = new UsuariosDAO();
	private TransaccionesDAO tDAO = new TransaccionesDAO();
	private ComicsDAO cDAO = new ComicsDAO();
	public static JComboBox cmbtransaccion = new JComboBox();
	private JButton btninformesusuarios;
	private JButton btninformecomics;
	private JButton btninformetransaccioens;
	private JButton btninformetipot;
	private JPanel panelfoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuario u = new Usuario();
			InfoUser dialog = new InfoUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoUser() {
		/*					if (u.getFoto() != null) {
						Image imagen = new ImageIcon(u.getFoto()).getImage().getScaledInstance(124, 124,
								Image.SCALE_SMOOTH);
						lblfoto.setIcon(new ImageIcon(imagen));
					}*/
		setBounds(100, 100, 331, 468);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				lblSaludo = new JLabel("BIENVENIDO");
				lblSaludo.setName("lblSaludo");
				lblSaludo.setForeground(new ColorUIResource(153, 255, 153));
				lblSaludo.setHorizontalAlignment(SwingConstants.CENTER);
				lblSaludo.setFont(new Font("Tahoma", Font.PLAIN, 32));
				panel.add(lblSaludo, BorderLayout.NORTH);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(2, 1, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 1, 0, 0));
				
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel_3 = new JPanel();
						panel_2.add(panel_3, BorderLayout.WEST);
						panel_3.setLayout(new BorderLayout(0, 0));
						{
							lblNewLabel = new JLabel("");
							if (Usuario.miUser().getFoto() != null) {
								Image imagen = new ImageIcon(Usuario.miUser().getFoto()).getImage().getScaledInstance(184, 184,
										Image.SCALE_SMOOTH);
								lblNewLabel.setIcon(new ImageIcon(imagen));
							}
							panel_3.add(lblNewLabel);
						}
					}
					{
						panelfoto = new JPanel();
						panel_2.add(panelfoto, BorderLayout.CENTER);
						panelfoto.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblNewLabel_1 = new JLabel(Usuario.miUser().getEmail());
							panelfoto.add(lblNewLabel_1);
						}
						{
							JPanel panel_4 = new JPanel();
							panelfoto.add(panel_4, BorderLayout.WEST);
						}
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				
				btninformecomics = new JButton("Comics");
				btninformecomics.setName("btninformecomics");
				btninformecomics.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							cDAO.obtenerComics();
							System.out.println("asd ::"+ HiloCliente.listaU);
							iDAO.getInformeComics("./src/Recursos/informes/Comics.jrxml");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
				btninformesusuarios = new JButton("Usuario");
				btninformesusuarios.setName("btninformesusuarios");
				btninformesusuarios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					try {
						uDAO.getAllUsers();
						iDAO.getInformeUsuarios("./src/Recursos/informes/Usuarios.jrxml");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				});
				
				btninformetipot = new JButton("Transacciones tipo");
				btninformetipot.setName("btninformetipot");
				btninformetipot.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tDAO.getTransacciones();
						iDAO.getInformeTransaccionesPorTipo("./src/Recursos/informes/Transacciones.jrxml");
					}
				});
				
				btninformetransaccioens = new JButton("Tus transacciones");
				btninformetransaccioens.setName("btninformetransaccioens");
				btninformetransaccioens.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tDAO.getTransacciones();
						iDAO.getInformeTransacciones("./src/Recursos/informes/Transacciones.jrxml");
					}
				});
				
				lblInformes = new JLabel("New label");
				lblInformes.setName("lblInformes");
				lblInformes.setFont(new Font("Tahoma", Font.PLAIN, 25));
				lblInformes.setHorizontalAlignment(SwingConstants.CENTER);
				
				
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btninformetipot, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
								.addComponent(btninformecomics, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
								.addComponent(btninformetransaccioens, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
								.addComponent(btninformesusuarios, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbtransaccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInformes, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btninformesusuarios, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btninformecomics, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btninformetransaccioens, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
								.addComponent(lblInformes, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btninformetipot, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cmbtransaccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(47))
				);
				cmbtransaccion.setModel(new DefaultComboBoxModel(new String[] {"Insert", "Update", "Delete"}));
				panel_1.setLayout(gl_panel_1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnExplorar = new JButton("Explorar");
				btnExplorar.setName("btnExplorar");
				btnExplorar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try { 
							TablaComicScreen tcs = new TablaComicScreen();
							tcs.setVisible(true);
							} catch (UnknownHostException e1) {
								System.out.println("ERROR");
							  e1.printStackTrace();
						  } catch (IOException e1){
							  JOptionPane.showConfirmDialog(null, rb.getString("JOConexionFallida"),"Error",JOptionPane.CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
							  //e1.printStackTrace();
						  }
						
					}
				});
				btnExplorar.setActionCommand("OK");
				buttonPane.add(btnExplorar);
				getRootPane().setDefaultButton(btnExplorar);
			}
			{
				btnCancelar = new JButton("Salir");
				btnCancelar.setName("btnCancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		listabotones.add(btnCancelar);
		listabotones.add(btnExplorar);
		listabotones.add(btninformetransaccioens);
		listabotones.add(btninformetipot);
		listabotones.add(btninformesusuarios);
		listabotones.add(btninformecomics);
		listalabels.add(lblSaludo);
		listalabels.add(lblInformes);
		InfoUserControler.traducir(listalabels,listabotones);
	}
}
