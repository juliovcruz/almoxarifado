package connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionFactory {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10320407";
    private static final String USER = "sql10320407";
    private static final String PASS = "XAUJQWRLez";
    
    public static java.sql.Connection getConnection() {
    	
    	try {
			Class.forName(DRIVER);
			
			return DriverManager.getConnection(URL, USER, PASS);
			
		} catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na Conexao do Banco",ex);
        }
    	
    }
    
    public static void closeConnection(java.sql.Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Error: "+ex);  
            }
        }
      }
    
    public static void closeConnection(java.sql.Connection con, PreparedStatement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Error: "+ex);
            }
        }
           closeConnection(con);
      }
    
    public static void closeConnection(java.sql.Connection con, PreparedStatement stmt,ResultSet rs){
    	
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Error: "+ex);
            }
        }
           closeConnection(con,stmt);
      }
    
    

}
