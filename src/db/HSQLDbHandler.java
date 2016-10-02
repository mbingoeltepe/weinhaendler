package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author MURAT BINGOELTEPE
 * @MatrikelNr 0627342
 * 
 */

public class HSQLDbHandler {

	private static Connection con = null;	
	
	  private static void openConnection(){		
			try {
				Class.forName("org.hsqldb.jdbcDriver"); 
				con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/weinhaendler", "sa", "");
			} catch (Exception e) {
			e.printStackTrace();	
	        }
	   }
		
		public static Connection getConnection(){
			try {
				if(con==null || con.isClosed()) openConnection();
			} catch (SQLException e) {
			  e.printStackTrace();
	           }
			
			return con;
		}
		
		public static void closeConnection(){
			try {
				if(con!=null) con.close();
			} catch (SQLException e) {
	   	      e.printStackTrace();
	     }
	}	

	
	
}
