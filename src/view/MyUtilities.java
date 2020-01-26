package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class MyUtilities {
	
	private JTable table;
	private FuncionarioTableModel fixo;
	
	public static void setCenter(JTable table, int alignment){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

	public static void TableFuncionario(JTable table,JScrollPane scroll) {
		setCenter(table, SwingConstants.CENTER);
		table.setBorder(null);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setBackground(Color.white);
		table.setGridColor(Color.white);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setBorder(null);
		
		scroll.getViewport().setBackground(Color.white);
		scroll.setViewportBorder(null);
		scroll.setForeground(Color.white);
		scroll.setBorder(null);
		scroll.setBounds(20,50,730,380);
		scroll.setBackground(Color.white);
	}

}