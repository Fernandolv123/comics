package BD;

import java.util.ArrayList;

import Modelo.Coleccion;
import Modelo.Comic;
import Vista.Hilos.HiloCliente;

public class ColeccionDAO implements IColeccionDAO{

	@Override
	public void getColeccion(Coleccion col) {
		ArrayList<String> listas= new ArrayList();
		for (Comic c: HiloCliente.listaC) {
			if (c.getColection().getNombre().equals(col.getNombre())) {
				col.setId(c.getColection().getId());
				System.out.println("Entro"+col.getNombre());
			}
		}
		
	}
	
}
