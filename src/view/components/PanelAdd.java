package view.components;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.*;
import view.Home;
import view.MyUtil;
import javax.swing.JRadioButton;

public class PanelAdd extends JPanel{
	
	
	private JLabel lblYear;
	private JLabel lblDate;
	private JLabel lblMonth;
	private JLabel lblDay;
	private JLabel lblUser;
	private JLabel lblItem;
	private JCheckBox chckBoxYesDate;
	private JLabel lblDescr;
	private JScrollPane scrollTxtDescr;
	private JTextArea Txtdescr;
	private JComboBox comboYear;
	private JComboBox comboMonth;
	private JComboBox comboDay;
	private JScrollPane listaItem;
	private JScrollPane listaUser;
	private JLabel lblAmount;
	private JRadioButton rdbtnRemove;
	private JTextField txtAmount;
	private JRadioButton rdbtnAdd;
	// 2 & 3
	private JLabel lblName;
	private JLabel lblRegOrAmount;
	private JTextField txtName;
	private JTextField txtRegOrAmount;
	
	private JList<User> ListUser;
	private JList<Item> ListItem;
	private DefaultListModel<Item> Itemlist;
	private DefaultListModel<User> Userlist;
	private JLabel IconCorrect;
	
	public int IDEDIT;
	
	
	public void readLists() {
		MyUtil.LoadListItem(Itemlist);	
		MyUtil.LoadListUser(Userlist);
	}
	
	public PanelAdd(int ID) {
		setBounds(230, 125, 770, 475);
		setLayout(null);
		
		Userlist = new DefaultListModel();
		ListUser = new JList<User>(Userlist);
		listaUser = new JScrollPane(ListUser);
		MyUtil.LoadListUser(Userlist);
		listaUser.setBounds(180, 181, 110, 230);
		add(listaUser);
		listaUser.setVisible(false);
		
		Itemlist = new DefaultListModel();
		ListItem = new JList<Item>(Itemlist);
		listaItem = new JScrollPane(ListItem);
		MyUtil.LoadListItem(Itemlist);		
		listaItem.setBounds(10, 181, 110, 230);
		add(listaItem);
		listaItem.setVisible(false);

		String days[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		comboDay = new JComboBox(days);
		comboDay.setBounds(172, 49, 50, 20);
		add(comboDay);
		comboDay.setVisible(false);

		String months[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		comboMonth = new JComboBox(months);
		comboMonth.setBounds(172, 83, 50, 20);
		add(comboMonth);
		comboMonth.setVisible(false);

		String years[] = {"2019","2020","2021"};
		comboYear = new JComboBox(years);
		comboYear.setBounds(157, 114, 65, 20);
		add(comboYear);
		comboYear.setVisible(false);

		lblDay = new JLabel("Dia:");
		lblDay.setBounds(116, 52, 46, 14);
		add(lblDay);
		lblDay.setVisible(false);

		lblMonth = new JLabel("Mes:");
		lblMonth.setBounds(116, 86, 46, 14);
		add(lblMonth);
		lblMonth.setVisible(false);

		lblYear = new JLabel("Ano:");
		lblYear.setBounds(116, 117, 46, 14);
		add(lblYear);
		lblYear.setVisible(false);

		lblDate = new JLabel("Utilizar a data de Hoje ?");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDate.setBounds(22, 18, 171, 14);
		add(lblDate);
		lblDate.setVisible(false);

		chckBoxYesDate = new JCheckBox("");
		chckBoxYesDate.setBounds(203, 14, 26, 23);
		chckBoxYesDate.setSelected(true);
		add(chckBoxYesDate);
		chckBoxYesDate.setVisible(false);

		lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblItem.setBounds(41, 160, 46, 15);
		add(lblItem);
		lblItem.setVisible(false);

		lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblUser.setBounds(206, 160, 84, 15);
		add(lblUser);
		lblUser.setVisible(false);
		
		Txtdescr = new JTextArea();
		scrollTxtDescr = new JScrollPane(Txtdescr);
		Txtdescr.setFont(new Font("Arial", Font.PLAIN, 13));
		Txtdescr.setText("");
		scrollTxtDescr.setBounds(377, 36, 269, 111);
		Txtdescr.setLineWrap(true);
		add(scrollTxtDescr);
		scrollTxtDescr.setVisible(false);

		
		lblDescr = new JLabel("Descri\u00E7\u00E3o:");
		lblDescr.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblDescr.setBounds(377, 18, 96, 15);
		add(lblDescr);
		lblDescr.setVisible(false);

		
		ImageIcon imgCorrect= new ImageIcon("src/imgs/correct32.png");
		IconCorrect = new JLabel(imgCorrect);
		IconCorrect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e){
				
				if(Home.IDADD == 1) {
					
					if(ListUser.getSelectedIndex() < 0 ||  ListItem.getSelectedIndex() < 0) {
						JOptionPane.showMessageDialog(null, "Nao foi selecionado um item ou usuario");
					} else if(txtAmount.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Sem quantidade!");
					} else {
					
						int day=0,month=0,year=0,amount = 0;
						
							if(chckBoxYesDate.isSelected()) {
								day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
								year = Calendar.getInstance().get(Calendar.YEAR);
								month = Calendar.getInstance().get(Calendar.MONTH);
								month++;
							}else {
								day = Integer.parseInt(comboDay.getSelectedItem().toString());
								month = Integer.parseInt(comboMonth.getSelectedItem().toString());
								year = Integer.parseInt(comboYear.getSelectedItem().toString());
							}
							
							if(rdbtnRemove.isSelected()) {
								amount = Integer.parseInt(txtAmount.getText());
								amount *= -1;
							}else {
								amount = Integer.parseInt(txtAmount.getText());
							}
						
						
						String descr = Txtdescr.getText();
						if(descr == null) descr = "Sem descricao";
						
						try {
						MyUtil.addTrade(ListUser.getSelectedValue(), ListItem.getSelectedValue(), descr,day, month, year,amount);
						}	catch (Exception ex) {
				            throw new RuntimeException("Erro em Adicionar Troca :",ex);
						}
						
						txtAmount.setText("");
						Txtdescr.setText("");
						Home.eventAdd(Home.IDADD);
					
					}
					
				}
				
				
				else if(Home.IDADD == 2 || Home.IDADD == 3){
					if(txtName.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Escreva o nome para prosseguir");
					}
					else if(txtRegOrAmount.getText().equals("")) {
						if(Home.IDADD == 2)
						JOptionPane.showMessageDialog(null, "Escreva a matricula para prosseguir");
						else JOptionPane.showMessageDialog(null, "Escreva a quantidade para prosseguir");
					}	else {
						String name = txtName.getText();
						
						try {
							if(Home.IDADD == 2) MyUtil.addUser(name,txtRegOrAmount.getText());
								else MyUtil.addItem(name, Integer.parseInt(txtRegOrAmount.getText()));
						} catch (Exception ex) {
							throw new RuntimeException("Erro em Adicionar Troca :",ex);
						}
						
						txtName.setText("");
						txtRegOrAmount.setText("");
						Home.eventAdd(Home.IDADD);
						
					}
				}
				
				else if(Home.IDADD == 4) {
					if(ListUser.getSelectedIndex() < 0 ||  ListItem.getSelectedIndex() < 0) {
						JOptionPane.showMessageDialog(null, "Nao foi selecionado um item ou usuario");
					} else if(txtAmount.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Sem quantidade!");
					} else {
					
						int day=0,month=0,year=0,amount = 0;
						
							if(chckBoxYesDate.isSelected()) {
								day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
								year = Calendar.getInstance().get(Calendar.YEAR);
								month = Calendar.getInstance().get(Calendar.MONTH);
								month++;
							}else {
								day = Integer.parseInt(comboDay.getSelectedItem().toString());
								month = Integer.parseInt(comboMonth.getSelectedItem().toString());
								year = Integer.parseInt(comboYear.getSelectedItem().toString());
							}
							
							if(rdbtnRemove.isSelected()) {
								amount = Integer.parseInt(txtAmount.getText());
								amount *= -1;
							}else {
								amount = Integer.parseInt(txtAmount.getText());
							}
						
						
						String descr = Txtdescr.getText();
						if(descr == null) descr = "Sem descricao";
						
						try {
						MyUtil.editTrade(ListUser.getSelectedValue(), ListItem.getSelectedValue(), descr,day, month, year,amount,IDEDIT);
						}	catch (Exception ex) {
				            throw new RuntimeException("Erro em Editar Troca :",ex);
						}
						
						txtAmount.setText("");
						Txtdescr.setText("");
						Home.eventAdd(Home.IDADD);
					
					}
				}
				else if(Home.IDADD == 5 || Home.IDADD == 6) {
					
					if(txtName.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Escreva o nome para prosseguir");
					}
					else if(txtRegOrAmount.getText().equals("")) {
						if(Home.IDADD == 2)
						JOptionPane.showMessageDialog(null, "Escreva a matricula para prosseguir");
						else JOptionPane.showMessageDialog(null, "Escreva a quantidade para prosseguir");
					}	else {
						String name = txtName.getText();
						
						try {
							if(Home.IDADD == 5) MyUtil.editUser(name,txtRegOrAmount.getText(),IDEDIT);
								else MyUtil.editItem(name, Integer.parseInt(txtRegOrAmount.getText()),IDEDIT);
						} catch (Exception ex) {
							throw new RuntimeException("Erro em Adicionar Troca :",ex);
						}
						
						txtName.setText("");
						txtRegOrAmount.setText("");
						Home.eventAdd(Home.IDADD);
						
						System.out.println(IDEDIT);
						
					}
					
				}

			}
		});
		IconCorrect.setBounds(730, 435, 32, 32);
		add(IconCorrect);
		
		ImageIcon imgRefresh= new ImageIcon("src/imgs/refresh32.png");
		JLabel IconRefresh= new JLabel(imgRefresh);
		IconRefresh.addMouseListener(new MouseAdapter() {
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
		IconRefresh.setBounds(690, 435, 32, 32);
		add(IconRefresh);
		
		txtName = new JTextField();
		txtName.setBounds(150, 25, 140, 20);
		add(txtName);
		txtName.setColumns(10);
		txtName.setVisible(false);
		
		txtRegOrAmount = new JTextField();
		txtRegOrAmount.setColumns(10);
		txtRegOrAmount.setBounds(150, 55, 140, 20);
		add(txtRegOrAmount);
		txtRegOrAmount.setVisible(false);
		
		String strRegOrAmount = (Home.IDADD == 2) ? "Matricula:" : "Quantidade:";
		lblRegOrAmount = new JLabel(strRegOrAmount);
		lblRegOrAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegOrAmount.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblRegOrAmount.setBounds(53, 58, 87, 14);
		add(lblRegOrAmount);
		lblRegOrAmount.setVisible(false);
		
		lblName = new JLabel("Nome:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblName.setBounds(53, 28, 87, 14);
		add(lblName);
		
		ButtonGroup addOrRemove = new ButtonGroup();
		rdbtnAdd = new JRadioButton("Adicionando");
		rdbtnAdd.setSelected(true);
		rdbtnAdd.setBounds(418, 160, 96, 23);
		add(rdbtnAdd);
		rdbtnRemove = new JRadioButton("Removendo");
		rdbtnRemove.setBounds(516, 160, 96, 23);
		add(rdbtnRemove);
		addOrRemove.add(rdbtnAdd);
		addOrRemove.add(rdbtnRemove);
		
		txtAmount = new JTextField();
		txtAmount.setText("");
		txtAmount.setBounds(516, 193, 96, 20);
		add(txtAmount);
		txtAmount.setColumns(10);
		
		lblAmount = new JLabel("Quantidade:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(409, 196, 105, 14);
		add(lblAmount);
		lblAmount.setVisible(false);
		
		AddMode(ID);
		
	}
	
	public void AddMode(int ID) {
		if(ID == 1) {
			//readLists();
			lblYear.setVisible(true);
			lblDate.setVisible(true);
			lblMonth.setVisible(true);
			lblDay.setVisible(true);
			lblUser.setVisible(true);
			lblItem.setVisible(true);
			chckBoxYesDate.setVisible(true);
			lblDescr.setVisible(true);
			scrollTxtDescr.setVisible(true);
			Txtdescr.setVisible(true);
			comboYear.setVisible(true);
			comboMonth.setVisible(true);
			comboDay.setVisible(true);
			listaItem.setVisible(true);
			listaUser.setVisible(true);
			rdbtnRemove.setVisible(true);
			txtAmount.setVisible(true);
			rdbtnAdd.setVisible(true);
			lblAmount.setVisible(true);
			// 2 & 3
			lblName.setVisible(false);
			lblRegOrAmount.setVisible(false);
			txtName.setVisible(false);
			txtRegOrAmount.setVisible(false);
			
		}else{
			lblYear.setVisible(false);
			lblDate.setVisible(false);
			lblMonth.setVisible(false);
			lblDay.setVisible(false);
			lblUser.setVisible(false);
			lblItem.setVisible(false);
			chckBoxYesDate.setVisible(false);
			lblDescr.setVisible(false);
			scrollTxtDescr.setVisible(false);
			Txtdescr.setVisible(false);
			comboYear.setVisible(false);
			comboMonth.setVisible(false);
			comboDay.setVisible(false);
			listaItem.setVisible(false);
			listaUser.setVisible(false);
			rdbtnRemove.setVisible(false);
			txtAmount.setVisible(false);
			rdbtnAdd.setVisible(false);
			lblAmount.setVisible(false);
			// 2 & 3
			lblName.setVisible(true);
			lblRegOrAmount.setVisible(true);
			txtName.setVisible(true);
			txtRegOrAmount.setVisible(true);
			if(ID == 2) lblRegOrAmount.setText("Matricula:");
			else lblRegOrAmount.setText("Quantidade:");
			
		}

	}
	
	public void eventEdit(Trade trd) {
		chckBoxYesDate.setSelected(false);
		String amount[] = trd.getAmount().split(" ");
			if(amount[1].equals("+")) rdbtnAdd.setSelected(true);
			else if(amount[1].equals("-")) rdbtnRemove.setSelected(true);
		txtAmount.setText(amount[2]);
		int index = 0;
		for(int i =0;i<Itemlist.getSize();i++) {
			if(Itemlist.get(i).getId() == trd.getId_item()) {
				index = i;
				break;
			}
		}
		ListItem.setSelectedIndex(index);
		index = 0;
		for(int i =0;i<Userlist.getSize();i++) {
			if(Userlist.get(i).getId() == trd.getId_user()) {
				index = i;
				break;
			}
		}
		ListUser.setSelectedIndex(index);
			
		Txtdescr.setText(trd.getDescr());
		comboDay.setSelectedIndex(trd.getDay()-1);
		comboMonth.setSelectedIndex(trd.getMonth()-1);
		comboYear.setSelectedIndex(trd.getYear()-2019);
		
	}
	
	public void eventEdit(Item item) {
		String amount = String.valueOf(item.getAmount());
		txtName.setText(item.getName());
		txtRegOrAmount.setText(amount);
	}
	
	public void eventEdit(User user) {
		txtName.setText(user.getName());
		txtRegOrAmount.setText(user.getReg());
	}
	
}
