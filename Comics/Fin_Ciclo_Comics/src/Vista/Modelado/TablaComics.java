package Vista.Modelado;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import Modelo.Comic;



/**Clase que será enlazada a la tabla de productos maestra
 * @author Fernando
 * */
public class TablaComics extends AbstractTableModel{
	//private ResourceBundle rb= ResourceBundle.getBundle("Idiomas.idioms");
	private ArrayList<Comic> listaproductos=null;
	String[] columnas = { "Id","Nombre","Precio","Cantidad"/*,"foto"*/ };
	
	
	public TablaComics(ArrayList<Comic> listauser) {
		super();
		this.listaproductos = listauser;
	}

	public boolean IsCellEditable(int rowindex, int columnindex) {
		return false;
	}

	public String getColumnName(int col) {
		return columnas[col];
	}

	public int getRowCount() {
		return listaproductos.size();
	}

	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (row != -1 && listaproductos.size() > row) {
			Comic c = listaproductos.get(row);
			switch (col) {
			case 0:
				return c.getColection();
			case 1:
				return c.getNombre();
			case 2:
				return null;//return c.getPrecio();
			case 3:
				return c.getCantidad();
			//case 4:
			//	return c.getFoto();
			default:
				return null;
			}

		} else {
			return null;
		}
	}
}
