package JDBCProjet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	static Connection conn=null ;
	public static Connection getConnection() 
	{
		if(conn ==null) {
			try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/demoJDBC", "root", "");
		}
			catch (SQLException e){
				System.err.println("Error opening SQL connection:"+ e.getMessage());
			}
		

	}
		return conn;
	
	
}
}