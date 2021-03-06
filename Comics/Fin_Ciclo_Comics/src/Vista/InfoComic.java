package Vista;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import Modelo.Comic;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.Image;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.awt.image.BufferedImage;

/**
 * Dialogo de información, creación, actualización y modificación de Productos
 * @author Fernando
 * */
public class InfoComic extends JDialog {

	private JPanel contentPane;
	private JLabel lblNewLabel = new JLabel();
	private JTextField txtprecio = new JTextField();
	private JTextField txtnombre=new JTextField();
	private JTextArea txtaoedesc=new JTextArea();
	private JPanel panel_ocultar= new JPanel();
	private JButton btnEliminar=new JButton();
	private JButton btnupdate=new JButton();
	private JButton btnanhadircarro=new JButton();
	private ArrayList<JButton> listabotones=new ArrayList();
	//private ResourceBundle rb= ResourceBundle.getBundle("Idiomas.Idioms");
	private JLabel lblnombre;
	private JLabel lblprecio;
	private JLabel lbldescprod;
	private JButton cancelButton;
	private ArrayList<JLabel> listalabel=new ArrayList();
	private JTextField txtColeccion = new JTextField();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comic c=new Comic();
					InfoComic frame = new InfoComic(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * 
	 */
	public InfoComic(Comic c) {
		/*UIManager.put("FileChooser.saveButtonText",rb.getString("btnguardar"));
		UIManager.put("FileChooser.openButtonText",rb.getString("btnabrir"));
		UIManager.put("FileChooser.cancelButtonText",rb.getString("btncerrar"));
		UIManager.put("FileChooser.updateButtonText",rb.getString("btnacutalizar"));
		UIManager.put("FileChooser.helpButtonText",rb.getString("btnayuda"));
		
		
		UIManager.put("FileChooser.lookInLabelText", rb.getString("lblmiraren"));
		UIManager.put("FileChooser.fileNameLabelText", rb.getString("lblarchivonombre"));
		UIManager.put("FileChooser.filesOfTypeLabelText", rb.getString("lbltipodearchivos"));
		UIManager.put("FileChooser.saveInLabelText", rb.getString("lblguardaren"));
		UIManager.put("FileChooser.openButtonToolTipText", rb.getString("Abrirarchivo"));
		UIManager.put("FileChooser.cancelButtonToolTipText",rb.getString("btncancelar"));
		UIManager.put("FileChooser.saveButtonToolTipText",rb.getString("btnguardar"));
		
		UIManager.put("FileChooser.openButtonToolTipText",rb.getString("btnabrir"));
		
		UIManager.put("FileChooser.upFolderToolTipText", rb.getString("JFsubirunnivel"));
		UIManager.put("FileChooser.homeFolderToolTipText",rb.getString("JFescritorio"));
		UIManager.put("FileChooser.newFolderToolTipText",rb.getString("JFnuevofichero"));
		UIManager.put("FileChooser.listViewButtonToolTipText",rb.getString("JFlista"));
		UIManager.put("FileChooser.newFolderButtonText",rb.getString("crearficheronuevo"));
		UIManager.put("FileChooser.renameFileButtonText", rb.getString("nombrararchivo"));
		UIManager.put("FileChooser.deleteFileButtonText", rb.getString("eliminararchivo"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", rb.getString("JFdetails"));
		UIManager.put("FileChooser.fileSizeHeaderText",rb.getString("JFtamanho"));
		UIManager.put("FileChooser.fileDateHeaderText", rb.getString("JFfecha"));
		UIManager.put("FileChooser.fileNameHeaderText", rb.getString("lblnombre"));
		UIManager.put("FileChooser.fileTypeHeaderText", rb.getString("JFtipo"));*/
		//setTitle(rb.getString("TitleInfoProduct"));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel_ocultar.setVisible(false);
		
		/*if(u.getTipo().equals("Administrador")) {
			panel_ocultar.setVisible(true);
		}*/
		
		if(c.getIVN() != null) {
			if(c.getImg().length==0) {
				lblNewLabel.setIcon(
						new ImageIcon(InfoComic.class.getResource("/Recursos/img/no_product.png")));
				lblNewLabel.setBackground(Color.WHITE);
			}else {
			Image imagen = new ImageIcon(c.getImg()).getImage().getScaledInstance(124, 124, Image.SCALE_SMOOTH);
			lblNewLabel.setIcon(new ImageIcon(imagen));
			}
			//txtaoedesc.setText(c.getDescripcion());
			txtprecio.setText(c.getPrecio()+"");
			txtnombre.setText(c.getNombre());
			txtColeccion.setText(c.getColection().getNombre());
			//btnupdate.setText(rb.getString("btnacutalizar"));
		}else {
			lblNewLabel.setIcon(
					new ImageIcon(InfoComic.class.getResource("/Recursos/img/no_product.png")));
			lblNewLabel.setBackground(Color.WHITE);
			btnEliminar.setVisible(false);
			//btnupdate.setText(rb.getString("btnadd"));
			btnanhadircarro.setVisible(false);
		}
		
		
		JPanel buttonPane = new JPanel();
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnanhadircarro.setText("A\u00F1adir al carro");
		
		btnanhadircarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(!ControladorInfoProduct.existProducto(pDAO.listacompra,p)) {
					pDAO.listacompra.add(p);
				}else {
					JOptionPane.showConfirmDialog(null, rb.getString("JOartcarr"),"Error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
				}*/
			}
		});
		btnanhadircarro.setName("btnanhadircarro");
		btnanhadircarro.setActionCommand("OK");
		buttonPane.add(btnanhadircarro);
		
		cancelButton = new JButton("Cerrar");
		cancelButton.setName("btncerrar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*if(!u.getTipo().equals("Administrador")) {
					return;
				}*/
				String ruta = System.getProperty("user.home");
				
				
				JFileChooser fc = new JFileChooser(ruta + "\\dekstop");
				
				fc.addChoosableFileFilter(
						new FileNameExtensionFilter("Fotos(png/jpg/jpeg)", "png", "jpg","jpeg"));
				int a = fc.showOpenDialog(null);
				
				if (fc.APPROVE_OPTION == a) {
					Image imagen = new ImageIcon(fc.getSelectedFile().getAbsolutePath()).getImage()
							.getScaledInstance(124, 124, Image.SCALE_SMOOTH);
					//System.out.println(imagen.toString());
					String rutaimagen = fc.getSelectedFile().getAbsolutePath();
					try {
						BufferedImage bImage = ImageIO.read(new File(rutaimagen));
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						String[] extension = rutaimagen.toString().trim().split("\\.");
						System.out.println(extension.length+"\n"+extension[extension.length-1]);
					    ImageIO.write(bImage, extension[extension.length-1], bos );
						//p.setImg(bos.toByteArray());
						System.out.println(rutaimagen);
						//System.out.println(p.getImg().length);
						
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					lblNewLabel.setIcon(new ImageIcon(imagen));
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
		
		// lblfoto.setIcon(new ImageIcon(imagen));
		panel.add(lblNewLabel, BorderLayout.WEST);
		
		panel.add(panel_ocultar, BorderLayout.CENTER);
		btnEliminar.setName("btneliminar");
		
		btnEliminar.setText("Eliminar");
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pDAO.deleteProduct(p);
				dispose();
			}
		});
		panel_ocultar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnupdate.setName("btnacutalizar");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(!ControladorInfoProduct.tryMod(p,txtnombre,txtaoedesc,txtprecio)) {
					return;
				}
				if(p.getNombre() != null) {
					p.setNombre(txtnombre.getText());
					p.setDescripcion(txtaoedesc.getText());
					p.setPrecio(Integer.parseInt(txtprecio.getText()));
					System.out.println(p.getImg().length);
					pDAO.updateProduct(p);
				}else {
					p.setNombre(txtnombre.getText());
					p.setDescripcion(txtaoedesc.getText());
					p.setPrecio(Integer.parseInt(txtprecio.getText()));
					pDAO.addProducto(p);
				}
				dispose();*/
			}
		});
		panel_ocultar.add(btnupdate);
		panel_ocultar.add(btnEliminar);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		lblnombre = new JLabel("Nombre");
		lblnombre.setName("tblNombre");
		
		txtnombre.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblnombre)
					.addGap(18)
					.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtnombre, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblnombre)
							.addComponent(txtnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		txtprecio.setColumns(10);
		
		lblprecio = new JLabel("Precio");
		lblprecio.setName("tblPrecio");
		
		JPanel panel_12 = new JPanel();
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblprecio)
					.addGap(26)
					.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtprecio, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtprecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblprecio)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_5_2 = new JPanel();
		panel_3.add(panel_5_2);
		
		JLabel lblCol = new JLabel("Coleccion");
		lblCol.setName("lblcol");
		
		JPanel panel_12_1 = new JPanel();
		
		txtColeccion.setColumns(10);
		GroupLayout gl_panel_5_2 = new GroupLayout(panel_5_2);
		gl_panel_5_2.setHorizontalGroup(
			gl_panel_5_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCol)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_12_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtColeccion, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_5_2.setVerticalGroup(
			gl_panel_5_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5_2.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_5_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_12_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_5_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtColeccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCol)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel_5_2.setLayout(gl_panel_5_2);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.EAST);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, BorderLayout.WEST);
		
		lbldescprod = new JLabel("Descripcion");
		lbldescprod.setName("lbldescprod");
		panel_6.add(lbldescprod, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		panel_7.add(txtaoedesc);
		
		
		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10, BorderLayout.EAST);
		/*if(!u.getTipo().equals("Administrador")) {
			txtaoedesc.enable(false);
			txtprecio.enable(false);
			txtnombre.enable(false);
		}*/
		listabotones.add(btnEliminar);
		listabotones.add(cancelButton);
		listabotones.add(btnanhadircarro);
		listalabel.add(lbldescprod);
		listalabel.add(lblnombre);
		listalabel.add(lblprecio);
		//ControladorInfoProduct.traducir(listabotones,listalabel,rb);
	}
}
