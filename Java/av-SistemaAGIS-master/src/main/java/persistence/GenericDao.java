package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {
	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String hostname = "localhost";
		String port = "1433";
		String db = "agis";
		String user ="sa";
		String pass ="luiz2002";
		String host = "jdbc:jtds:sqlserver://";
		String uri = host+hostname+":"+port+";databaseName="+db+";user="+user+";password="+pass;
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(uri);
		return c;
	}
}