package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import models.Item;
import models.Trade;
import models.User;
import modelsDB.ItemDB;
import modelsDB.TradeDB;
import modelsDB.UserDB;

public class MyUtil {
	
	public static Color BG = new Color(238,240,242);
	
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
		table.setBackground(BG);
		table.setGridColor(BG);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		table.getTableHeader().setBackground(BG);
		table.getTableHeader().setBorder(null);
		
		scroll.getViewport().setBackground(BG);
		scroll.setViewportBorder(null);
		scroll.setForeground(BG);
		scroll.setBorder(null);
		scroll.setBounds(20,50,730,380);
		scroll.setBackground(BG);
	}
	
	public static Color BG() {
		return new Color(238,240,242);
	}
	
	public static void addUser(String matricula, String nome) {
		UserDB fbd = new UserDB();
		
		User f = new User(matricula,nome);
		fbd.create(f);
		
	}
	
	public static void addItem(String name, int amount) {
		ItemDB idb = new ItemDB();
		
		Item i = new Item(name,amount);
		idb.create(i);
		
	}
	
	public static void addTrade(User user, Item item,String descr, int day, int month, int year,int amount) {
		String strAmount;
		if(amount <= 0) {
			amount *= -1;
			strAmount = " - " + amount;
		}
		else strAmount = " + " + amount;
			
		TradeDB tdb = new TradeDB();
		
		Trade t = new Trade(user,item, descr,day,month,year,strAmount);
		tdb.create(t);
	}
	
	 public static void LoadListUser(DefaultListModel<User> lista){
		 ArrayList<User> users = new ArrayList<>();
		 UserDB ubd = new UserDB();
			
			for(User f: ubd.read()) {
				users.add(f);	
			}
         lista.clear();
         for (User user: users) {
             lista.addElement(user);
             }
     }
	 
	 public static void LoadListItem(DefaultListModel<Item> lista) {
		 ArrayList<Item> items = new ArrayList<>();
		 ItemDB ibd = new ItemDB();
			
			for(Item f: ibd.read()) {
				items.add(f);	
			}
         lista.clear();
         for (Item item: items) {
             lista.addElement(item);
             }
	 }

}