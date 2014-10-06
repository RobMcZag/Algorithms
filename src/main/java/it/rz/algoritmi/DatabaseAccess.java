package it.rz.algoritmi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		String DRIVER_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
		// String dsn = "myXL";	// Nome del DNS
		// String urlDSN = "jdbc:odbc:"+dsn;
		String xlFilePath = "C:\\TMP\\DEV\\Java\\cena.xls"; 
				// "C:/Documents and Settings/myPath/Desktop/qa.xls";
		String driverName1 = "{Microsoft Excel Driver (*.xls)}";
		String driverName2 = "{Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)}";
		String urlFile = "Driver="+driverName2+";DBQ="+xlFilePath;
		String user = "";	// Con XL va bene vuoto
		String pw = "";		// Con XL va bene vuoto
		String query = "SELECT * FROM [partecipanti$];";
		
		Connection conn = null;
		Statement stmt = null;
		try {
			// Load driver
			Class.forName(DRIVER_NAME);
			// Connect to the DB
			conn = DriverManager.getConnection(urlFile, user, pw);
			stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery( query );
		    try {
		        while ( rs.next() ) {
		            int numColumns = rs.getMetaData().getColumnCount();
		            for ( int i = 1 ; i <= numColumns ; i++ ) {
		               // Column numbers start at 1.
		               // Also there are many methods on the result set to return
		               //  the column as a particular type. Refer to the Sun documentation
		               //  for the list of valid conversions.
		               System.out.println( "COLUMN " + i + " = " + rs.getObject(i) );
		            }
		        }
		    } finally {
		        try { rs.close(); } catch (Throwable ignore) { 
		        	/* Propagate the original exception
						instead of this one that you may want just logged */ 
		        	}
		    } // END of try WHILE
		} finally {
		    try { stmt.close(); } catch (Throwable ignore) { 
		    	/* Propagate the original exception
				instead of this one that you may want just logged */ 
		    	}
		} // END of try CONNECT
		
		
	} // END of main()

	public Connection getConnectionByName(String driver, String url, String user, String pw) 
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, pw);
		return conn;	
	}
	
	public Connection getConnection(String url, String user, String pw) 
			throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, pw);
		return conn;	
	}
	
}
