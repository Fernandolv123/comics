package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class CrearUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtemail;
	private JPasswordField txtpasswd;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JLabel lblContrasenha;
	private JLabel lblEmail;
	private JPanel panel_1;
	private JLabel lblfoto;
	private JPanel panel_2;
	private JButton btnNewButton;
	private Usuario u = new Usuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearUsuario dialog = new CrearUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearUsuario() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(2, 0, 0, 0));
			{
				JPanel panel_10 = new JPanel();
				panel.add(panel_10);
				lblEmail = new JLabel("Email      ");
				lblEmail.setName("lblEmail");
				JPanel panel_13 = new JPanel();
				txtemail = new JTextField();
				txtemail.setColumns(10);
				GroupLayout gl_panel_10 = new GroupLayout(panel_10);
				gl_panel_10.setHorizontalGroup(
					gl_panel_10.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_10.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEmail)
							.addGap(27)
							.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtemail, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
							.addContainerGap())
				);
				gl_panel_10.setVerticalGroup(
					gl_panel_10.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_10.createSequentialGroup()
							.addContainerGap(78, Short.MAX_VALUE)
							.addGroup(gl_panel_10.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblEmail)))
							.addContainerGap())
				);
				panel_10.setLayout(gl_panel_10);
			}
			{
				JPanel panel_8 = new JPanel();
				panel.add(panel_8);
				lblContrasenha = new JLabel("Contrase\u00F1a    ");
				lblContrasenha.setName("lblContrasenha");
				JPanel panel_14 = new JPanel();
				txtpasswd = new JPasswordField();
				txtpasswd.setColumns(10);
				GroupLayout gl_panel_8 = new GroupLayout(panel_8);
				gl_panel_8.setHorizontalGroup(
					gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblContrasenha)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtpasswd, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
							.addContainerGap())
				);
				gl_panel_8.setVerticalGroup(
					gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtpasswd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblContrasenha)))
							.addContainerGap(78, Short.MAX_VALUE))
				);
				panel_8.setLayout(gl_panel_8);
			}
		}
		
		panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblfoto = new JLabel("");
		panel_1.add(lblfoto, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ruta = System.getProperty("user.home");
				
				
				JFileChooser fc = new JFileChooser(ruta + "\\dekstop");
				
				fc.addChoosableFileFilter(
						new FileNameExtensionFilter("Fotos(png/jpg/jpeg)", "png", "jpg","jpeg"));
				int a = fc.showOpenDialog(null);
				
				if (fc.APPROVE_OPTION == a) {
					Image imagen = new ImageIcon(fc.getSelectedFile().getAbsolutePath()).getImage()
							.getScaledInstance(124, 224, Image.SCALE_SMOOTH);
					//System.out.println(imagen.toString());
					String rutaimagen = fc.getSelectedFile().getAbsolutePath();
					try {
						BufferedImage bImage = ImageIO.read(new File(rutaimagen));
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						String[] extension = rutaimagen.toString().trim().split("\\.");
					    ImageIO.write(bImage, extension[extension.length-1], bos );
						u.setFoto(bos.toByteArray());
						
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					//lblfoto.setIcon(new ImageIcon(imagen));
					lblfoto.setIcon(new ImageIcon(imagen));
					try {
						FileInputStream lector=new FileInputStream(rutaimagen);
						//byte[] image = IOUtils.toByteArray(lector);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(a==0) {
					
				} //a==1 se cierra
			}
		});
		panel_2.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						u.setEmail(txtemail.getText());
						u.setContraseña(txtpasswd.getText());
						if(u.isFormated()) {
							dispose();
						}
						
					}
				});
				btnGuardar.setName("btnInsertar");
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
	}

}
