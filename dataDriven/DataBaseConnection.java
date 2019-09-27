package dataDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class DataBaseConnection {
	static Connection con;
	

	static{
	
		// TO ESTABLISH DATA BASE CONNECTION.
		String ConnectionUrl = "jdbc:sqlserver://192.168.1.15:1433;databaseName=XTBillsMaster_QA;user=XTBMQAuser;password=XTBMQAuser";
	
		try {
			 
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver()); 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		try {
			con = DriverManager.getConnection(ConnectionUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getData() throws SQLException{
		String sql_query="select * from mtbluser";
		PreparedStatement pst=con.prepareStatement(sql_query);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			String UserName=rs.getString("mUserName");
			System.out.println(UserName);
		}
				
	}
	
	public static void main(String[] args) throws SQLException {
		getData();
		con.close();
	}
	


	
	
}
