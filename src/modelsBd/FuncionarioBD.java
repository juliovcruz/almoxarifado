package modelsBd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import connection.ConnectionFactory;
import models.Funcionario;

public class FuncionarioBD {

	public void create(Funcionario f) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO funcionario (matricula,nome)VALUES(?,?)");
			stmt.setInt(1, f.getMatricula());
			stmt.setString(2, f.getNome());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
		}finally {
			ConnectionFactory.closeConnection(con,stmt);
		}
		 
	}
	
	public ArrayList<Funcionario> read(){
		java.sql.Connection con = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
	     ResultSet rs = null;
	     ArrayList<Funcionario> funcs = new ArrayList<Funcionario>();
	     
	     try {
			stmt = con.prepareStatement("SELECT * FROM funcionario");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
	            Funcionario f= new Funcionario();
	            f.setID(rs.getInt("id"));
	            f.setMatricula(rs.getInt("matricula"));
	            f.setNome(rs.getString("nome"));
	            funcs.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	     
	     return funcs;
	     
	}
	
}
