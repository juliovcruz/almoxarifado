package view.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import models.Funcionario;

public class FuncionarioTableModel extends AbstractTableModel{
	
	public ArrayList<Funcionario> dados = new ArrayList<>();
    private String[] colunas = {"Nome","Matricula"};

    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                return dados.get(linha).getNome();
            case 1:
                return dados.get(linha).getMatricula();
        }
        
        return null;
        
    }
    
    public void addRow(Funcionario f){
        this.dados.add(f);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

}
