package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class btnMenuLeft extends JPanel {
	
	private String txt;
	
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
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel btnFirstLabel = new JLabel(texto);
		btnFirstLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnFirstLabel.setForeground(Color.WHITE);
		btnFirstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnFirstLabel);
	}

}