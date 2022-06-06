package BD;

import Modelo.Transaccion;

public interface ITransaccionesDAO {
	public void insertarMovimiento(Transaccion t);
	public void getTransacciones();
}
