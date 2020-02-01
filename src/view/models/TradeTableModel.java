package view.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import models.Trade;

public class TradeTableModel extends AbstractTableModel{
	
	public ArrayList<Trade> data = new ArrayList<>();
    private String[] collumns = {"Matricula","Nome","Item","Data","Descricao","Quantidade"};

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
    	
    	String date = data.get(linha).getDay() + "/" +  data.get(linha).getMonth() + "/" +  data.get(linha).getYear();
        
        switch(coluna){
            case 0:
                return data.get(linha).getUserReg();
            case 1:
            	return data.get(linha).getUserName();
            case 2:
                return data.get(linha).getItemName();
            case 3:
            	return date;
            case 4:
            	return data.get(linha).getDescr();
            case 5:
            	return data.get(linha).getAmount();
        }
        
        return null;
        
    }
    
    public void addRow(Trade f){
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
