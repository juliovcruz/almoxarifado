package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import models.Item;
import models.Trade;
import models.User;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.JTable;

import view.components.PanelAdd;
import view.components.PanelItems;
import view.components.PanelTrades;
import view.components.PanelUsers;
import view.components.btnMenuLeft;

import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class Home extends JFrame {

	public static int IDADD = 2; // 1 = TRADE | 2 = USER | 3 = ITEM | 4 = EDITTRADE | 5 = EDITUSER | 6 = EDITITEM
	private JPanel backMain;
	private JTextField txtName;
	private JTextField txtRegOrAmount;
	private static PanelAdd panelAdd;
	private static PanelUsers panelUsers;
	private static PanelItems panelItems;
	private static PanelTrades panelTrades;
	private ArrayList<btnMenuLeft> btnsMenuLeft;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setColorBtnNoHover(JPanel jp) {
		jp.setBackground(new Color(0,0,52));
	}
	public void setColorBtnHover(JPanel jp) {
		jp.setBackground(new Color(0,0,60));
	}

	public Home() {
		//Image image = new ImageIcon(Home.class.getResource("/box.png")).getImage();
		//Image image = new ImageIcon("src/imgs/edit.png").getImage();
		//setIconImage(image);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(251,255,241));
		setBounds(100, 100, 1000, 600);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
		backMain = new JPanel();
		backMain.setBackground(MyUtil.BG);
		backMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(backMain);
		backMain.setLayout(null);
		
		JPanel JHeader = new JPanel();
		JHeader.setBackground(new Color(0,0,52));
		JHeader.setBounds(230, 0, 770, 125);
		backMain.add(JHeader);
		JHeader.setLayout(null);
		
		final JLabel lblExit = new JLabel("X");	
		lblExit.setBounds(750, 0, 17, 14);
		lblExit.setForeground(Color.RED);
		lblExit.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(new Color(150,0,0));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.RED);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		JHeader.add(lblExit);
		
		JLabel lblHeader = new JLabel("Principal");
		lblHeader.setBounds(48, 27, 550, 60);
		lblHeader.setForeground(Color.white);
		lblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 45));
		JHeader.add(lblHeader);
		
		JPanel backMenuLateral = new JPanel();
		backMenuLateral.setBackground(new Color(0,0,52));
		backMenuLateral.setBounds(0, 0, 230, 600);
		backMain.add(backMenuLateral);
		backMenuLateral.setLayout(null);
		
		panelUsers = new PanelUsers();
		backMain.add(panelUsers);
		
		panelItems = new PanelItems();
		backMain.add(panelItems);
		
		panelTrades = new PanelTrades();
		backMain.add(panelTrades);
		
		panelAdd = new PanelAdd(1);
		panelAdd.setVisible(false);
		backMain.add(panelAdd);
		 
		// Adicionando Menus Laterais
		btnsMenuLeft = new ArrayList<>();
		String strMenuLeft[] = {"Principal","Usuarios","Trocas", "Itens"};
		for(int j=125,i=0; i<strMenuLeft.length ;j+=40,i++) {
			btnsMenuLeft.add(new btnMenuLeft(j,strMenuLeft[i]));
			backMenuLateral.add(btnsMenuLeft.get(i));
		}

		for(int i=0;i<btnsMenuLeft.size();i++){
			final int index = i;
			btnsMenuLeft.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int indexj = index;
					for(int j =0;j<btnsMenuLeft.size();j++ ) {
						
						lblHeader.setText(btnsMenuLeft.get(indexj).getTxt());
						if(indexj == 1) {
							//panelUsers.readUsers();
							panelUsers.setVisible(true);
						}else {
							panelUsers.setVisible(false);
						}
						if(indexj == 2) {
							//panelTrades.readTrades();
							panelTrades.setVisible(true);
						}else {
							panelTrades.setVisible(false);
						}
						if(indexj == 3) {
							//panelItems.readItems();
							panelItems.setVisible(true);
						}else {
							panelItems.setVisible(false);
						}
						
						
						
						if(indexj != j && btnsMenuLeft.get(j).getID() == 1) {
							btnsMenuLeft.get(j).setID(0);
							btnsMenuLeft.get(j).setColorBtnNoHover();
						}
					}
				}
			
		});
		}
		
		panelUsers.IconAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				panelUsers.setVisible(false);
				IDADD = 2;
				panelAdd.AddMode(2);
				panelAdd.setVisible(true);
			}
		});
		panelTrades.IconAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				panelTrades.setVisible(false);
				IDADD = 1;
				panelAdd.AddMode(1);
				panelAdd.setVisible(true);
			}
		});
		panelItems.IconAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				panelItems.setVisible(false);
				IDADD = 3;
				panelAdd.AddMode(3);
				panelAdd.setVisible(true);
			}
		});
	
	}

	public static void eventAdd(int id) {
		if(id == 1) {
			panelTrades.readTrades();
		}else if(id == 2) {
			panelUsers.readUsers();
		}else if(id == 3) {
			panelItems.readItems();
		}else if(id == 4) {
			panelAdd.setVisible(false);
			panelTrades.setVisible(true);
			panelTrades.readTrades();
		}else if(id == 5) {
			panelAdd.setVisible(false);
			panelUsers.readUsers();
			panelUsers.setVisible(true);
		}else if(id == 6) {
			panelAdd.setVisible(false);
			panelItems.readItems();
			panelItems.setVisible(true);
		}
	}
	
	public static void eventEdit(Trade trd) {
		panelAdd.eventEdit(trd);
		panelTrades.setVisible(false);
		IDADD = 4;
		panelAdd.IDEDIT = trd.getId();
		panelAdd.AddMode(1);
		panelAdd.setVisible(true);
	}
	
	public static void eventEdit(Item item) {
		panelAdd.eventEdit(item);
		panelItems.setVisible(false);
		IDADD = 6;
		panelAdd.IDEDIT = item.getId();
		panelAdd.AddMode(3);
		panelAdd.setVisible(true);
	}
	
	public static void eventEdit(User user) {
		panelAdd.eventEdit(user);
		panelUsers.setVisible(false);
		IDADD = 5;
		panelAdd.IDEDIT = user.getId();
		panelAdd.AddMode(2);
		panelAdd.setVisible(true);
	}
	
	
}