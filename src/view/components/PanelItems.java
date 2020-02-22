package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.security.Principal;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;

import models.Item;
import modelsDB.ItemDB;
import view.models.*;
import view.Home;
import view.MyUtil;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelItems extends JPanel{
	
	private JTextField txtFilter;
	private ItemTableModel modelItems = new ItemTableModel();
	private JTable JTableFuncs;
	private JScrollPane TableFuncs;
	public JLabel IconAdd;
	public JLabel IconEdit;
	private ArrayList<Item> items = new ArrayList<>();
	
	public ItemTableModel getModel() {
		return modelItems;
	}
	
	public void readItems() {
		
		modelItems.resetRow();
		items.removeAll(items);
		
		ItemDB idb = new ItemDB();
		
		for(Item i: idb.read()) {
			items.add(i);	
		}
		
		for(int i =0;i<items.size();i++) {
			modelItems.addRow(items.get(i));
		}
	}
	
	public void readItemsFilter(String filter, ItemTableModel model) {
		
		modelItems.resetRow();
		
		items.removeAll(items);
		
		ItemDB idb = new ItemDB();
		
		for(Item i: idb.readFilter(filter)) {
			items.add(i);	
		}
		
		for(int i =0;i<items.size();i++) {
			model.addRow(items.get(i));
		}
	}
	
	private void iconButton16px(){
		//ImageIcon imgSearch = new ImageIcon("src/imgs/search.png");
		JLabel IconSearch = new JLabel(Home.imgSearch);
		IconSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				readItemsFilter(txtFilter.getText(), modelItems);
				txtFilter.setText("");
			}
		});
		IconSearch.setBounds(190, 12, 16, 16);
		add(IconSearch);
	}
	
	public PanelItems(){
		setBackground(MyUtil.BG);
		setBounds(230, 125, 770, 475);
		setLayout(null);
		setVisible(false);
		
		txtFilter = new JTextField();
		txtFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){  
					readItemsFilter(txtFilter.getText(), modelItems);
					txtFilter.setText("");
				}
			}
		});
		txtFilter.setText("");
		txtFilter.setBounds(20, 11, 160, 20);		
		add(txtFilter);
		txtFilter.setColumns(10);
		
		iconButton16px();
		
		JTableFuncs = new JTable(modelItems);	
		TableFuncs = new JScrollPane(JTableFuncs);
		MyUtil.TableFuncionario(JTableFuncs,TableFuncs);
		add(TableFuncs);
		
		//ImageIcon imgEdit= new ImageIcon("src/imgs/edit.png");
		IconEdit= new JLabel(Home.imgEdit);
		IconEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {	
				Home.eventEdit(items.get(JTableFuncs.getSelectedRow()));
			}
		});
		IconEdit.setBounds(720, 15, 16, 16);
		add(IconEdit);
		
		//ImageIcon imgAdd= new ImageIcon("src/imgs/plus.png");
		IconAdd= new JLabel(Home.imgAdd);
		IconAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {	
				
			}
		});
		IconAdd.setBounds(695, 15, 16, 16);
		add(IconAdd);
		
		//ImageIcon imgRemove= new ImageIcon("src/imgs/close.png");
		JLabel IconRemove= new JLabel(Home.imgRemove);
		IconRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {	
				MyUtil.delItem(items.get(JTableFuncs.getSelectedRow()));
				readItems();				
			}
		});
		IconRemove.setBounds(670, 15, 16, 16);
		add(IconRemove);
		
		readItems();
		
	}

}
