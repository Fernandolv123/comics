package Vista.Modelado;

import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.color.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import Modelo.Comic;



/**Clase que será enlazada a la tabla de productos maestra
 * @author Fernando
 * */
public class TablaComics extends AbstractTableModel{
	//private ResourceBundle rb= ResourceBundle.getBundle("Idiomas.idioms");
	private ArrayList<Comic> listacomics=null;
	String[] columnas = {"IVN","Coleccion","Nombre","Precio","Cantidad","foto" };
	
	
	public TablaComics(ArrayList<Comic> listauser) {
		super();
		this.listacomics = listauser;
	}

	public boolean IsCellEditable(int rowindex, int columnindex) {
		return false;
	}

	public String getColumnName(int col) {
		return (String) columnas[col];
	}

	public int getRowCount() {
		return listacomics.size();
	}

	public int getColumnCount() {
		return columnas.length;
	}
	
	public Class getColumnClass(int column) {
		return (getColumnName(column).equals("foto")) ? ImageIcon.class : Object.class;
    }
	
	
	@Override
	public Object getValueAt(int row, int col) {
		if (row != -1 && listacomics.size() > row) {
			Comic c = listacomics.get(row);
			switch (col) {
			case 0:
				return c.getIVN();
			case 1:
				return c.getColection().getNombre();
			case 2:
				return c.getNombre();
			case 3:
				return c.getPrecio();
			case 4:
				return c.getCantidad();
			case 5:
				Image imagen = new ImageIcon(c.getImg()).getImage().getScaledInstance(124, 124, Image.SCALE_SMOOTH);
				return new ImageIcon(imagen);
				
				/*BufferedImage notevoyadecuirunnombre = new BufferedImage(124,124,BufferedImage.TYPE_INT_RGB);
				
				notevoyadecuirunnombre.getGraphics().drawImage(imagen,0,0,null);
				return notevoyadecuirunnombre.getGraphics();*/
				/*byte[] bytes = c.getImg();
				int width = 160;
				int height = 160;
				DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
				    ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8}, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
				    return new BufferedImage(cm, Raster.createInterleavedRaster(buffer, width, height, width * 3, 3, new int[]{0, 1, 2}, null), false, null);*/
			default:
				return null;
			}

		} else {
			return null;
		}
	}
}
