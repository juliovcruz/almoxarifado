package view.components;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import models.Trade;
import modelsDB.TradeDB;
import view.models.*;
import view.Home;
import view.MyUtil;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelTrades extends JPanel{
	
	private JTextField txtFilter;
	private TradeTableModel modelTrades = new TradeTableModel();
	private ArrayList<Trade> trades = new ArrayList<>();;
	private JTable JTableFuncs;
	private JScrollPane TableFuncs;
	public JLabel IconAdd;
	public JLabel IconEdit;
	
	public TradeTableModel getModel() {
		return modelTrades;
	}
	
	public void readTrades() {
		
		modelTrades.resetRow();

		trades.removeAll(trades);

		
		TradeDB tdb = new TradeDB();
		
		for(Trade i: tdb.read()) {
			trades.add(i);	
		}
		
		for(int i =0;i<trades.size();i++) {
			modelTrades.addRow(trades.get(i));
		}
	}
	
	public void readTrades(String filter, TradeTableModel model) {
		
		modelTrades.resetRow();
		trades.removeAll(trades);
		
		TradeDB tdb = new TradeDB();
		
		for(Trade i: tdb.readFilter(filter)) {
			trades.add(i);	
		}
		
		for(int i =0;i<trades.size();i++) {
			model.addRow(trades.get(i));
		}
	}
	
	private void iconButton16px(){
		ImageIcon imgSearch = new ImageIcon("src/imgs/search.png");
		JLabel IconSearch = new JLabel(imgSearch);
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
				readTrades(txtFilter.getText(), modelTrades);
				txtFilter.setText("");
			}
		});
		IconSearch.setBounds(190, 12, 16, 16);
		add(IconSearch);
	}
	
	public PanelTrades(){
		setBackground(MyUtil.BG);
		setBounds(230, 125, 770, 475);
		setLayout(null);
		setVisible(false);
		
		txtFilter = new JTextField();
		txtFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){  
					readTrades(txtFilter.getText(), modelTrades);
					txtFilter.setText("");
				}
			}
		});
		txtFilter.setText("");
		txtFilter.setBounds(20, 11, 160, 20);		
		add(txtFilter);
		txtFilter.setColumns(10);
		
		iconButton16px();
		
		JTableFuncs = new JTable(modelTrades);
		TableFuncs = new JScrollPane(JTableFuncs);
		MyUtil.TableFuncionario(JTableFuncs,TableFuncs);
		add(TableFuncs);
		
		ImageIcon imgEdit= new ImageIcon("src/imgs/edit.png");
		IconEdit= new JLabel(imgEdit);
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
				Home.eventEdit(trades.get(JTableFuncs.getSelectedRow()));
			}
		});
		IconEdit.setBounds(720, 15, 16, 16);
		add(IconEdit);
		
		ImageIcon imgAdd= new ImageIcon("src/imgs/plus.png");
		IconAdd= new JLabel(imgAdd);
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
		
		ImageIcon imgRemove= new ImageIcon("src/imgs/close.png");
		JLabel IconRemove= new JLabel(imgRemove);
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
				
			}
		});
		IconRemove.setBounds(670, 15, 16, 16);
		add(IconRemove);
		
		readTrades();
		
	}

}
