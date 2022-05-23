package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import BD.ComicsDAO;
import Modelo.Comic;
import Vista.Modelado.TablaComics;
import Controlador.TablaComicControlador;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Dialogo de Visualización de todos los productos de la base de datos mediante una tabla maestra
 * @author Fernando
 * */
public class TablaComicScreen extends JDialog {

	private JPanel contentPane;
	private JTable tablaProductos;
	private JButton btnAnhadirProducto;
	private JPanel panel_1;
	private JButton btnfiltro;
	private JLabel lblfilto;
	private JTextField txtnombrefiltro;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panelOculto;
	private JComboBox cmbCol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaComicScreen frame = new TablaComicScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public TablaComicScreen() throws UnknownHostException, IOException {
		ComicsDAO cdao=new ComicsDAO();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		panelOculto = new JPanel();
		panelOculto.setVisible(false);
		panel.add(panelOculto, BorderLayout.SOUTH);
		panelOculto.setLayout(new GridLayout(0, 1, 0, 0));

		btnAnhadirProducto = new JButton("A\u00F1adir Producto");
		btnAnhadirProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAnhadirProducto.setName("btnAnhadirProducto");
		panelOculto.add(btnAnhadirProducto);

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		btnfiltro = new JButton("Filtrar");
		btnfiltro.setName("btnfiltro");
		btnfiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tablaProductos.setModel(new TablaProductos(pDAO.sortProductos(textField.getText())));
			}
		});
		
		cmbCol = new JComboBox();
		cmbCol.setModel(new DefaultComboBoxModel(new String[] {"Todos"}));
		cmbCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaProductos.setModel(new TablaComics(TablaComicControlador.sort(cmbCol.getSelectedItem().toString(),txtnombrefiltro.getText())));
			}
		});
		panel_1.add(cmbCol);
		panel_1.add(btnfiltro);

		panel_3 = new JPanel();
		panel_1.add(panel_3);

		lblfilto = new JLabel("Filtro por nombre");
		lblfilto.setName("lblfilto");
		panel_1.add(lblfilto);

		panel_4 = new JPanel();
		panel_1.add(panel_4);

		txtnombrefiltro = new JTextField();
		txtnombrefiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tablaProductos.setModel(new TablaComics(TablaComicControlador.sort(cmbCol.getSelectedItem().toString(),txtnombrefiltro.getText())));
			}
		});

		panel_1.add(txtnombrefiltro);
		txtnombrefiltro.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		tablaProductos = new JTable();
		tablaProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					//System.out.println(pDAO.listacompra.size());
					//Comic p = cdao.getComic((int) (tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0)));
					System.out.println(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0));
					Comic c = cdao.getComic((String) tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0));
					InfoComic infc = new InfoComic(c);
					infc.setVisible(true);
					//InfoProducto infprd = new InfoProducto(p, u);
					//infprd.setVisible(true);
					//tablaProductos.setModel(new TablaComics(cdao.obtenerComics()));
				}
			}
		});
		tablaProductos.setRowHeight(124);
		tablaProductos.setAutoCreateRowSorter(true);
		tablaProductos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablaProductos);
		tablaProductos.setModel(new TablaComics(cdao.obtenerComics()));
		TablaComicControlador.RellenarCombo(cmbCol);
	}

}
