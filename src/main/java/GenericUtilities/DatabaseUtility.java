package GenericUtilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	
	public Connection con;
	
	/**
	 * This method is used to fetchdata from database
	 * @param url
	 * @param un
	 * @param pwd
	 * @param query
	 * @return Resultset
	 * @throws Exception
	 */
	public ResultSet FetchDataFromDatabase(String url,String un,String pwd,String query) throws Exception {
	
	//create the driver
	
	Driver driver=new Driver();
	
	//register the driver
	
	DriverManager.registerDriver(driver);
	
	//get connection
	
	 con=DriverManager.getConnection(url,un,pwd);
	 
	 //Create statement
	 
	Statement stmt=con.createStatement();
	
	//Execute query and enter data
	
		ResultSet result=stmt.executeQuery(query);
		
		return result;
	
	
	
	}
	
	/**
	 * This method is used to fetchdata from database
	 * @param query
	 * @return Resultset
	 * @throws Exception
	 */
	
	public ResultSet FetchDataFromDatabase(String query) throws Exception {
		
		//create the driver
		
		Driver driver=new Driver();
		
		//register the driver
		
		DriverManager.registerDriver(driver);
		
		//get connection
		
		 con=DriverManager.getConnection("jDBC:MySQL://Localhost:3306/project","root","root");
		 
		 //Create statement
		 
		Statement stmt=con.createStatement();
		
		//Execute query and enter data
		
			ResultSet result=stmt.executeQuery(query);
			
			return result;
			
	}
	
	/**
	 * This method is used to write back data to database
	 * @param query
	 * @return int
	 * @throws Exception
	 */

		public int WritebackDatatoDatabase(String query) throws Exception {
		
		//create the driver
		
		Driver driver=new Driver();
		
		//register the driver
		
		DriverManager.registerDriver(driver);
		
		//get connection
		
		 con=DriverManager.getConnection("jDBC:MySQL://Localhost:3306/project","root","root");
		 
		 //Create statement
		 
		Statement stmt=con.createStatement();
		
		int result=stmt.executeUpdate(query);
		
		return result;
		
		}
		/**
		 * This method is used to close database connection
		 * @throws SQLException
		 */

public void ClosetheDatabaseConnection() throws SQLException

{
	con.close();	
	
	
}


/**
 * @return 
 * @throws Exception 
 * 
 */
public Connection getDatabaseConnection() throws Exception {
	
	Driver driver=new Driver();
	
	DriverManager.registerDriver(driver);
	
	 con=DriverManager.getConnection("jDBC:MySQL://Localhost:3306/project","root","root");
	 
	 return con;
}
	
}
	
	
	
	
