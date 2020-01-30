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

import models.User;
import modelsDB.UserDB;
import view.models.*;

import view.MyUtil;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelUsers extends JPanel{
	
	private JTextField txtFilter;
	private UserTableModel modelFuncs = new UserTableModel();
	private JTable JTableFuncs;
	private JScrollPane TableFuncs;
	
	public UserTableModel getModel() {
		return modelFuncs;
	}
	
	public void readFuncs() {
		
		modelFuncs.resetRow();
		ArrayList<User> funcs = new ArrayList<>();
		
		UserDB fbd = new UserDB();
		
		for(User f: fbd.read()) {
			funcs.add(f);	
		}
		
		for(int i =0;i<funcs.size();i++) {
			modelFuncs.addRow(funcs.get(i));
		}
	}
	
	public void readFuncsFilter(String filter, UserTableModel model) {
		
		modelFuncs.resetRow();
		ArrayList<User> funcs = new ArrayList<>();
		
		UserDB fbd = new UserDB();
		
		for(User f: fbd.readFilter(filter)) {
			funcs.add(f);	
		}
		
		for(int i =0;i<funcs.size();i++) {
			model.addRow(funcs.get(i));
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
				readFuncsFilter(txtFilter.getText(), modelFuncs);
			}
		});
		IconSearch.setBounds(190, 12, 16, 16);
		add(IconSearch);
	}
	
	public PanelUsers(){
		setBackground(MyUtil.BG);
		setBounds(230, 125, 770, 475);
		setLayout(null);
		setVisible(false);
		
		txtFilter = new JTextField();
		txtFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){  
					readFuncsFilter(txtFilter.getText(), modelFuncs);
				}
			}
		});
		txtFilter.setText("Filtro");
		txtFilter.setBounds(20, 11, 160, 20);		
		add(txtFilter);
		txtFilter.setColumns(10);
		
		iconButton16px();
		
		JTableFuncs = new JTable(modelFuncs);
		TableFuncs = new JScrollPane(JTableFuncs);
		MyUtil.TableFuncionario(JTableFuncs,TableFuncs);
		add(TableFuncs);
		
		readFuncs();
		
	}

}
