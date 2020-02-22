package view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.Home;

public class btnMenuLeft extends JPanel {
	
	private String txt;
	private JLabel lblIcon;
	private ImageIcon img;
	
	public String getTxt() {
		return txt;
	}
	
	private int id; // 0 NORMAL 1 = CLICKADO
	
	public void setID(int id) {
		this.id = id;
	}
	public int getID() {
		return id;
	}
	
	public void setColorBtnNoHover() {
		setBackground(new Color(0,0,52));
	}
	public void setColorBtnHover() {
		setBackground(new Color(0,0,62));
	}
	public void setColorBtnClicked() {
		setBackground(new Color(0,0,72));
	}
	
	public btnMenuLeft(int altura,String texto) {
			this.txt = texto;
			addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(id == 0)
				setColorBtnHover();
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(id == 0)
				setColorBtnNoHover();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setColorBtnClicked();
				id = 1;
			}
		});
		setBackground(new Color(0,0,52));
		setBounds(0, altura, 230, 40);
		setLayout(null);
		
		JLabel btnFirstLabel = new JLabel(texto);
		btnFirstLabel.setBounds(0, 0, 230, 40);
		btnFirstLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnFirstLabel.setForeground(Color.WHITE);
		btnFirstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnFirstLabel);
		
		
		
		if(btnFirstLabel.getText().equals("Principal")) img = new ImageIcon(Home.class.getResource("/home.png"));
		else if(btnFirstLabel.getText().equals("Usuarios")) img = new ImageIcon(Home.class.getResource("/users.png"));
		else if(btnFirstLabel.getText().equals("Trocas")) img = new ImageIcon(Home.class.getResource("/trades.png"));
		else if(btnFirstLabel.getText().equals("Itens")) img = new ImageIcon(Home.class.getResource("/items.png"));
		
		lblIcon = new JLabel(img);
		lblIcon.setBounds(20, 14, 46, 14);
		add(lblIcon);
	}
}