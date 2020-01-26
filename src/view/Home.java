package view;

import java.awt.BorderLayout;
import view.models.FuncionarioTableModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Color;
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
import java.awt.FlowLayout;
import javax.swing.JTable;
import models.Funcionario;
import modelsBd.FuncionarioBD;
import view.components.btnMenuLeft;

public class Home extends JFrame {

	private JPanel backPrincipal;
	private JTable JTableFuncs;
	//private MyJTableScrollPane TableFuncs;
	private JScrollPane TableFuncs;

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
		backPrincipal = new JPanel();
		backPrincipal.setBackground(MyUtil.BG);
		backPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(backPrincipal);
		backPrincipal.setLayout(null);
		
		JPanel JHeader = new JPanel();
		JHeader.setBackground(new Color(0,0,52));
		JHeader.setBounds(230, 0, 770, 125);
		backPrincipal.add(JHeader);
		JHeader.setLayout(null);
		
		final JLabel lblExit = new JLabel("X");	
		lblExit.setBounds(750, 0, 17, 14);
		lblExit.setForeground(Color.RED);
		lblExit.setFont(new Font("Segoe UI", Font.BOLD, 15));
		JHeader.add(lblExit);
		
		JLabel lblHeader = new JLabel("Menu");
		lblHeader.setBounds(48, 27, 550, 60);
		lblHeader.setForeground(Color.white);
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 45));
		JHeader.add(lblHeader);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(new Color(150,0,0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.RED);
			}
		});
		
		JPanel backMenuLateral = new JPanel();
		backMenuLateral.setBackground(new Color(0,0,52));
		backMenuLateral.setBounds(0, 0, 230, 600);
		backPrincipal.add(backMenuLateral);
		backMenuLateral.setLayout(null);
		
		final JPanel panelFuncs = new JPanel();
		panelFuncs.setBackground(Color.BLACK);
		panelFuncs.setBounds(230, 126, 770, 475);
		panelFuncs.setLayout(null);
		panelFuncs.setVisible(false);
		backPrincipal.add(panelFuncs);
		
		
		ArrayList<Funcionario> funcs = new ArrayList<>();
		
		FuncionarioTableModel modelFuncs = new FuncionarioTableModel();
		JTableFuncs = new JTable(modelFuncs);
		TableFuncs = new JScrollPane(JTableFuncs);
		MyUtil.TableFuncionario(JTableFuncs,TableFuncs);
		panelFuncs.add(TableFuncs);
		
		MyUtil.addRow(modelFuncs);
		
		
		// Adicionando Menus Laterais
		final ArrayList<btnMenuLeft> btnsMenuLeft = new ArrayList<>();
		for(int j=125,i=0;j<=245;j+=40,i++) {
			String strs[] = {"Principal","Usuarios","Histórico de Trocas", "Configurações"};
			btnsMenuLeft.add(new btnMenuLeft(j,strs[i]));
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
							panelFuncs.setVisible(true);
						}else {
							panelFuncs.setVisible(false);
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