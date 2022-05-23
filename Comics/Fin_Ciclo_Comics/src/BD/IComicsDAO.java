package BD;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Modelo.Comic;

public interface IComicsDAO {
	public ArrayList<Comic> obtenerComics() throws UnknownHostException, IOException;
	public Comic getComic(String value);
}
