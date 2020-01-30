package modelsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import models.Item;
import models.Item;

public class ItemDB {

		public void create(Item item) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO items (name,amount)VALUES(?,?)");
			stmt.setString(1, item.getName());
			stmt.setInt(2, item.getAmount());
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
		}finally {
			ConnectionFactory.closeConnection(con,stmt);
		}
	}
		
		public void update(Item item){
			  Connection con = ConnectionFactory.getConnection();
			  PreparedStatement stmt = null;
			      try {
			          stmt = con.prepareStatement("UPDATE produto SET name = ?,amount = ? WHERE id = ?");
			          stmt.setString(1, item.getName());
			          stmt.setInt(2, item.getAmount());
			          stmt.setInt(3, item.getId());
			          stmt.executeUpdate();
			          JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
			      } catch (SQLException ex) {
			          JOptionPane.showMessageDialog(null,"Erro ao atualizar "+ex);
			      }finally{
			       ConnectionFactory.closeConnection(con, stmt);
			      
			      } 
		}
		
	public ArrayList<Item> read(){
			java.sql.Connection con = ConnectionFactory.getConnection();
			java.sql.PreparedStatement stmt = null;
		     ResultSet rs = null;
		     ArrayList<Item> items = new ArrayList<Item>();
		     
		     try {
				stmt = con.prepareStatement("SELECT * FROM items");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
		            Item i= new Item();
		            i.setId(rs.getInt("id"));
		            i.setAmount(rs.getInt("amount"));
		            i.setName(rs.getString("name"));
		            items.add(i);
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Erro: "+e);
			}finally{
				ConnectionFactory.closeConnection(con, stmt, rs);
			}
		     
		     return items;
		     
		}
	
	public ArrayList<Item> readFilter(String filter){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
	     ResultSet rs = null;
	     ArrayList<Item> items = new ArrayList<Item>();
	     
	     try {
			stmt = con.prepareStatement("SELECT * FROM items WHERE name LIKE ?");
			stmt.setString(1, "%"+filter+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
	            Item i= new Item(); 
	            i.setId(rs.getInt("id"));
	            i.setAmount(rs.getInt("amount"));
	            i.setName(rs.getString("name"));
	            items.add(i);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	     
	     return items;
	     
	}
	
}
