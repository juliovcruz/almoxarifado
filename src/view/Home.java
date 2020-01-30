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
import models.User;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.FlowLayout;
import javax.swing.JTable;

import view.components.PanelAdd;
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

	public static int IDADD = 2; // 1 = TRADE | 2 = USER | 3 = ITEM
	private JPanel backMain;
	PanelUsers panelUsers;
	private JTextField txtName;
	private JTextField txtRegOrAmount;
	private PanelAdd panelAdd;

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
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(251,255,241));
		setBounds(100, 100, 1000, 600);
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
		
		JLabel lblHeader = new JLabel("Vai estudar Java");
		lblHeader.setBounds(48, 27, 550, 60);
		lblHeader.setForeground(Color.white);
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 45));
		JHeader.add(lblHeader);
		
		JPanel backMenuLateral = new JPanel();
		backMenuLateral.setBackground(new Color(0,0,52));
		backMenuLateral.setBounds(0, 0, 230, 600);
		backMain.add(backMenuLateral);
		backMenuLateral.setLayout(null);
		
		panelUsers = new PanelUsers();
		backMain.add(panelUsers);
		
		panelAdd = new PanelAdd(1);
		panelAdd.setVisible(false);
		backMain.add(panelAdd);

		// Adicionando Menus Laterais
		final ArrayList<btnMenuLeft> btnsMenuLeft = new ArrayList<>();
		String strMenuLeft[] = {"Principal","Usuarios","Histórico de Trocas", "Itens", "Adicionar Troca", "Adicionar Usuário", "Adicionar Item"};
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
							panelUsers.setVisible(true);
						}else {
							panelUsers.setVisible(false);
						}
						if(indexj >3 && indexj <7) {
							int ID = indexj - 3;
							panelAdd.AddMode(ID);
							panelAdd.setVisible(true);
						}else {
							panelAdd.setVisible(false);
						}
						
						
						
						if(indexj != j && btnsMenuLeft.get(j).getID() == 1) {
							btnsMenuLeft.get(j).setID(0);
							btnsMenuLeft.get(j).setColorBtnNoHover();
						}
					}
				}
			
		});
		}
	
	}
}