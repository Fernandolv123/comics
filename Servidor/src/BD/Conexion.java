package BD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.*;

/**
 * Clase que incluir� los metodos de conexion con la base de datos
 *  @author Fernando
 * */
public class Conexion {
//	private static ResourceBundle rb = ResourceBundle.getBundle("Idiomas.Idioms");
	static Connection Con;
	static BasicDataSource basicdatasource = new BasicDataSource();
	private static String BD = "comic_library";
        //private static String IP = "localhost";
	private static String IP = "192.168.56.120";

	/**
	 * Metodo que crea una conexi�n con los datos existentes en el fichero .properties
	 * @param propiedades objeto de tipo properties del que se sacar�n los datos
	 * */
	public static void IniciaPoolconFichero(Properties propiedades) throws SQLException {
		try {
			//Properties propiedades = new Properties();
			//propiedades.load(new FileInputStream("./connect.properties"));
			//propiedades.load(new FileInputStream("src//Datos//connect.properties"));
			System.out.println(propiedades.toString());
			//BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(propiedades);
			basicdatasource.setDriverClassName(propiedades.getProperty("driverClassName"));
			basicdatasource.setUsername(propiedades.getProperty("username"));
			basicdatasource.setPassword(propiedades.getProperty("password"));
			basicdatasource.setUrl(propiedades.getProperty("url"));
			basicdatasource.setValidationQuery(propiedades.getProperty("setValidationQuery"));
			basicdatasource.setMaxTotal(5);
			basicdatasource.setMinIdle(50);
			basicdatasource.setMaxIdle(100);
			basicdatasource.setMaxWaitMillis(1000);
			System.out.println(basicdatasource.getNumActive());
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, rb.getString("JOdberror"));
		}
	}
	
	/**
	 * Metodo que inicia sesi�n con unos valores predefinidos en cdigo
	 * */
	public static void Init() throws SQLException {
		try {

			basicdatasource.setDriverClassName("org.mariadb.jdbc.Driver");
			basicdatasource.setUsername("root");
			basicdatasource.setPassword("");
			basicdatasource.setUrl("jdbc:mariadb://" + IP + ":3306/" + BD);
			basicdatasource.setValidationQuery("Select 1");
			basicdatasource.setMaxTotal(5);
			basicdatasource.setMinIdle(50);
			basicdatasource.setMaxIdle(100);
			basicdatasource.setMaxWaitMillis(1000);
                        System.out.println(basicdatasource.getNumActive());

		} catch (Exception e) {
                        e.printStackTrace();
			//JOptionPane.showMessageDialog(null, rb.getString("JOdberror"));
		}
	}
	
	/**
	 * Metodo que obtendr� una estancia de la sesi�n en forma de conexi�n
	 * @return Objeto de la clase connection
	 * */
	public static Connection getConnection() throws SQLException {
		//try {
		Con = basicdatasource.getConnection();
		System.out.println("Conexiones activas" + basicdatasource.getNumActive());
		return Con;
		/*}catch (SQLException e) {
			return null;
		}*/
	}

	/**
	 * M�todo que cierra la conexion activa
	 * */
	public static void Close() {
		try {
			Con.close();
		} catch (SQLException ex) {
		}
	}
	/**
	 * M�todo que comprueba si ya hay una conexi�n existente, si ya existe, obtendr� una instancia de esta, si no, creara una nueva
	 * */
	public static Connection tryConnect(Connection con) {
		try {
			con=Conexion.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();

			// TODO Auto-generated catch block
			try {
				
				Conexion.Init();
				con= Conexion.getConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return con;
	}
}
