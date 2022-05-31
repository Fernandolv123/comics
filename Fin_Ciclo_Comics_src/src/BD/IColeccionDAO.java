package BD;

import java.util.ArrayList;

import Modelo.Coleccion;

public interface IColeccionDAO {
	public void getColeccion(Coleccion c);
	public ArrayList<Coleccion> getColecciones();
}
