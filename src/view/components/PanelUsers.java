package view.components;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Principal;
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
import view.Home;
import view.MyUtil;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelUsers extends JPanel{
	
	private JTextField txtFilter;
	private UserTableModel modelFuncs = new UserTableModel();
	private JTable JTableFuncs;
	private JScrollPane TableFuncs;
	public JLabel IconAdd;
	public JLabel IconEdit;
	private ArrayList<User> users = new ArrayList<>();
	
	public UserTableModel getModel() {
		return modelFuncs;
	}
	
	public void readUsers() {
		
		modelFuncs.resetRow();
		
		users.removeAll(users);
		
		UserDB fbd = new UserDB();
		
		for(User f: fbd.read()) {
			users.add(f);	
		}
		
		for(int i =0;i<users.size();i++) {
			modelFuncs.addRow(users.get(i));
		}
	}
	
	public void readUsers(String filter, UserTableModel model) {
		
		modelFuncs.resetRow();
		
		users.removeAll(users);
		
		UserDB fbd = new UserDB();
		
		for(User f: fbd.readFilter(filter)) {
			users.add(f);	
		}
		
		for(int i =0;i<users.size();i++) {
			model.addRow(users.get(i));
		}
	}
	
	private void iconButton16px(){
		//ImageIcon imgSearch = new ImageIcon("src/imgs/search.png");
		ImageIcon imgSearch = new ImageIcon(Home.class.getResource("/search.png"));
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
				readUsers(txtFilter.getText(), modelFuncs);
				txtFilter.setText("");
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
					readUsers(txtFilter.getText(), modelFuncs);
					txtFilter.setText("");
				}
			}
		});
		txtFilter.setText("");
		txtFilter.setBounds(20, 11, 160, 20);		
		add(txtFilter);
		txtFilter.setColumns(10);
		
		iconButton16px();
		
		JTableFuncs = new JTable(modelFuncs);
		TableFuncs = new JScrollPane(JTableFuncs);
		MyUtil.TableFuncionario(JTableFuncs,TableFuncs);
		add(TableFuncs);
		
		//ImageIcon imgEdit= new ImageIcon("src/imgs/edit.png");
		ImageIcon imgEdit = new ImageIcon(Home.class.getResource("/edit.png"));
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
				Home.eventEdit(users.get(JTableFuncs.getSelectedRow()));
				System.out.println(users.get(JTableFuncs.getSelectedRow()));
			}
		});
		IconEdit.setBounds(720, 15, 16, 16);
		add(IconEdit);
		
		//ImageIcon imgAdd= new ImageIcon("src/imgs/plus.png");
		ImageIcon imgAdd = new ImageIcon(Home.class.getResource("/plus.png"));
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
		});
		IconAdd.setBounds(695, 15, 16, 16);
		add(IconAdd);
		
		//ImageIcon imgRemove= new ImageIcon("src/imgs/close.png");
		ImageIcon imgRemove = new ImageIcon(Home.class.getResource("/close.png"));
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
				MyUtil.delUser(users.get(JTableFuncs.getSelectedRow()));
				readUsers();				
			}
		});
		IconRemove.setBounds(670, 15, 16, 16);
		add(IconRemove);
		
		readUsers();
		
	}
}
