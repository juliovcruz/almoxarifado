package modelsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import models.Item;
import models.Trade;
import models.User;

public class TradeDB {
	
	public void create(Trade trade) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO trades (id_user,id_item,descr,day,month,year,user_name,item_name,user_reg,amount)VALUES(?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, trade.getUser().getId());
			stmt.setInt(2, trade.getItem().getId());
			stmt.setString(3, trade.getDescr());
			stmt.setInt(4, trade.getDay());
			stmt.setInt(5, trade.getMonth());
			stmt.setInt(6, trade.getYear());
			stmt.setString(7, trade.getUser().getName());
			stmt.setString(8, trade.getItem().getName());
			stmt.setString(9, trade.getUser().getReg());
			stmt.setString(10, trade.getAmount());
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
		}finally {
			ConnectionFactory.closeConnection(con,stmt);
		}
	}
	
	public void update(Trade trade) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("UPDATE trades SET id_user = ?,id_item = ?,descr = ?,day = ?,month = ?,year = ?,user_name = ?,item_name = ?,user_reg = ?,amount = ? WHERE id = ?");
			stmt.setInt(1, trade.getUser().getId());
			stmt.setInt(2, trade.getItem().getId());
			stmt.setString(3, trade.getDescr());
			stmt.setInt(4, trade.getDay());
			stmt.setInt(5, trade.getMonth());
			stmt.setInt(6, trade.getYear());
			stmt.setString(7, trade.getUser().getName());
			stmt.setString(8, trade.getItem().getName());
			stmt.setString(9, trade.getUser().getReg());
			stmt.setString(10, trade.getAmount());
			stmt.setInt(11, trade.getId());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Editado com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
		}finally {
			ConnectionFactory.closeConnection(con,stmt);
		}
	}
	
	public ArrayList<Trade> read(){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
	     ResultSet rs = null;
	     ArrayList<Trade> trds = new ArrayList<Trade>();
	     
	     try {
			stmt = con.prepareStatement("SELECT * FROM trades");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Trade trd = new Trade();
	            trd.setId(rs.getInt("id"));
	            trd.setId_item(rs.getInt("id_item"));
	            trd.setId_user(rs.getInt("id_user"));
	            trd.setUserName(rs.getString("user_name"));
	            trd.setItemName(rs.getString("item_name"));
	            trd.setDay(rs.getInt("day"));
	            trd.setMonth(rs.getInt("month"));
	            trd.setYear(rs.getInt("year"));
	            trd.setDescr(rs.getString("descr"));
	            trd.setUserReg(rs.getString("user_reg"));
	            trd.setAmount(rs.getString("amount"));
	            trds.add(trd);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	     
	     return trds;
	     
	}
	
	public ArrayList<Trade> readFilter(String filter){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
	     ResultSet rs = null;
	     ArrayList<Trade> trds = new ArrayList<Trade>();
	     
	     try {
			stmt = con.prepareStatement("SELECT * FROM trades WHERE user_name LIKE ? OR item_name LIKE ? OR user_reg LIKE ?");
			stmt.setString(1, "%"+filter+"%");
			stmt.setString(2, "%"+filter+"%");
			stmt.setString(3, "%"+filter+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Trade trd = new Trade();
	            trd.setId(rs.getInt("id"));
	            trd.setUserName(rs.getString("user_name"));
	            trd.setItemName(rs.getString("item_name"));
	            trd.setDay(rs.getInt("day"));
	            trd.setMonth(rs.getInt("month"));
	            trd.setYear(rs.getInt("year"));
	            trd.setDescr(rs.getString("descr"));
	            trd.setUserReg(rs.getString("user_reg"));
	            trd.setAmount(rs.getString("amount"));
	            trds.add(trd);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	     
	     return trds;
	     
	}
	
	public ArrayList<Trade> readFilterDate(String filter, int day,int month){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
	     ResultSet rs = null;
	     ArrayList<Trade> trds = new ArrayList<Trade>();
	          
	     try {
	    	if(filter.equals("") && day != 0 && month != 0) {
	    		stmt = con.prepareStatement("SELECT * FROM trades WHERE day = ? AND month = ?");
	    		stmt.setInt(1, day);
	    		stmt.setInt(2, month);    		
	    	}
	    	else if(filter.equals("") && day != 0) {
	    		stmt = con.prepareStatement("SELECT * FROM trades WHERE day = ?");
	    		stmt.setInt(1, day);
	    	}
	    	else if(filter.equals("") && month != 0 ) {
	    		stmt = con.prepareStatement("SELECT * FROM trades WHERE month = ?");
	    		stmt.setInt(1, month);
	    	}
	    	else {
	    		stmt = con.prepareStatement("SELECT * FROM trades WHERE user_name LIKE ? OR item_name LIKE ? OR user_reg LIKE ?");
				stmt.setString(1, "%"+filter+"%");
				stmt.setString(2, "%"+filter+"%");
				stmt.setString(3, "%"+filter+"%");
	    	}

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Trade trd = new Trade();
	            trd.setId(rs.getInt("id"));
	            trd.setUserName(rs.getString("user_name"));
	            trd.setItemName(rs.getString("item_name"));
	            trd.setDay(rs.getInt("day"));
	            trd.setMonth(rs.getInt("month"));
	            trd.setYear(rs.getInt("year"));
	            trd.setDescr(rs.getString("descr"));
	            trd.setUserReg(rs.getString("user_reg"));
	            trd.setAmount(rs.getString("amount"));
	            trds.add(trd);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro: "+e);
		}finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	     
	     return trds;
	     
	}
	
	public int lastID(){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet rset = null;
		int aux=0;
		try {
		    stmt = con.prepareStatement("SELECT * FROM trades ORDER BY id DESC LIMIT 1");
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
	
	public void remove(Trade t){
		  Connection con = ConnectionFactory.getConnection();
		  PreparedStatement stmt = null;
		      
		      try {
		          stmt = con.prepareStatement("DELETE FROM trades WHERE id = ?");
		          stmt.setInt(1, t.getId());
		          stmt.executeUpdate();
		          JOptionPane.showMessageDialog(null,"removido com sucesso");
		      } catch (SQLException ex) {
		          JOptionPane.showMessageDialog(null,"Erro ao remover "+ex);
		      }finally{
		       ConnectionFactory.closeConnection(con, stmt);
		      
		      }
	}
		

}
