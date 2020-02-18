package connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// link: http://www.phpmyadmin.co/
// server: sql10.freemysqlhosting.net
/// user: sql10320407
// pass: XAUJQWRLez

public class ConnectionFactory {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://remotemysql.com:3306/fZERn5uokw";
    private static final String USER = "fZERn5uokw";
    private static final String PASS = "RJdTW3Xprw";
    
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
