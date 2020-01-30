package modelsDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import models.Item;
import models.User;

public class UserDB {

	public void create(User f) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO users (reg,name)VALUES(?,?)");
			stmt.setString(1, f.getReg());
			stmt.setString(2, f.getName());
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally {
			ConnectionFactory.closeConnection(con,stmt);
		}
		 
	}
	
	public void update(User f) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE users SET reg = ?,name = ? WHERE id = ?");
			stmt.setString(1, f.getReg());
			stmt.setString(2, f.getName());
			stmt.setInt(3,f.getId());
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Editado com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally {
			ConnectionFactory.closeConnection(con,stmt);
		}
		 
	}
	
	public ArrayList<User> read(){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
	     ResultSet rs = null;
	     ArrayList<User> funcs = new ArrayList<User>();
	     
	     try {
			stmt = con.prepareStatement("SELECT * FROM users");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
	            User f= new User();
	            f.setId(rs.getInt("id"));
	            f.setReg(rs.getString("reg"));
	            f.setName(rs.getString("name"));
	            funcs.add(f);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	     
	     return funcs;
	     
	}
	
	public ArrayList<User> readFilter(String filter){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
	     ResultSet rs = null;
	     ArrayList<User> funcs = new ArrayList<User>();
	     
	     try {
			stmt = con.prepareStatement("SELECT * FROM users WHERE name LIKE ? OR reg LIKE ?");
			stmt.setString(1, "%"+filter+"%");
			stmt.setString(2, "%"+filter+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				User f= new User();
	            f.setId(rs.getInt("id"));
	            f.setReg(rs.getString("reg"));
	            f.setName(rs.getString("name"));
	            funcs.add(f);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	     return funcs;
	}
	
	public int lastID(){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet rset = null;
		int aux=0;
		try {
		    stmt = con.prepareStatement("SELECT * FROM users ORDER BY id DESC LIMIT 1");
		    rset = stmt.executeQuery();
		    while(rset.next()){
		       aux = rset.getInt("id");
		    }
		      } catch (SQLException ex) {
		          JOptionPane.showMessageDialog(null,"Erro: "+ex);
		      }finally{
		       ConnectionFactory.closeConnection(con, stmt);
		      }
		      return aux;
	}
	
	public void remove(User u){
		  Connection con = ConnectionFactory.getConnection();
		  PreparedStatement stmt = null;
		      
		      try {
		          stmt = con.prepareStatement("DELETE FROM users WHERE id = ?");
		          stmt.setInt(1, u.getId());
		          stmt.executeUpdate();
		          JOptionPane.showMessageDialog(null,"removido com sucesso");
		      } catch (SQLException ex) {
		          JOptionPane.showMessageDialog(null,"Erro ao remover "+ex);
		      }finally{
		       ConnectionFactory.closeConnection(con, stmt);
		      
		      }
		      
	}
	
}
