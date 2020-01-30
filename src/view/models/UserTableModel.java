package view.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import models.User;

public class UserTableModel extends AbstractTableModel{
	
	public ArrayList<User> data = new ArrayList<>();
    private String[] collumns = {"Matricula","Nome"};

    @Override
    public String getColumnName(int column) {
        return collumns[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return collumns.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                return data.get(linha).getReg();
            case 1:
                return data.get(linha).getName();
        }
        
        return null;
        
    }
    
    public void addRow(User f){
        this.data.add(f);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.data.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public void resetRow() {
    	while(getRowCount() > 0)
    	{
    	    removeRow(0);
    	}
    }

}
