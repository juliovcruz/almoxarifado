package models;

import javax.swing.JOptionPane;

import lombok.*;

@Getter
@Setter

public class Item {
	
	private int id;
	private String name;
	private int amount;
	
	public String toString() {
		return name;
	}
	
	public void updateAmount(int value) {
		amount += value;		
	}
	
	public Item(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public Item() {
		
	}

}
