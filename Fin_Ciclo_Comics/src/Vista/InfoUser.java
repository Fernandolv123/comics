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

import Modelo.Usuario;

import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

public class InfoUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblSaludo;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuario u = new Usuario();
			InfoUser dialog = new InfoUser(u);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoUser(Usuario u) {
		/*					if (u.getFoto() != null) {
						Image imagen = new ImageIcon(u.getFoto()).getImage().getScaledInstance(124, 124,
								Image.SCALE_SMOOTH);
						lblfoto.setIcon(new ImageIcon(imagen));
					}*/
		setBounds(100, 100, 450, 300);
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
							if (u.getFoto() != null) {
								Image imagen = new ImageIcon(u.getFoto()).getImage().getScaledInstance(84, 84,
										Image.SCALE_SMOOTH);
								lblNewLabel.setIcon(new ImageIcon(imagen));
							}
							panel_3.add(lblNewLabel);
						}
					}
					{
						JPanel panel_3 = new JPanel();
						panel_2.add(panel_3, BorderLayout.CENTER);
						panel_3.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblNewLabel_1 = new JLabel("Ferlorenzovazqeuz@gmail.com");
							panel_3.add(lblNewLabel_1);
						}
						{
							JPanel panel_4 = new JPanel();
							panel_3.add(panel_4, BorderLayout.WEST);
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Explorar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
