package models;
import java.util.ArrayList;

import lombok.*;
@Getter
@Setter

public class User {
	
	private ArrayList<Trade> trades;
	private int amountTrades;
	private int id;
	private String reg;
	private String name;
	
	public void addTroca(Trade trd) {
		trades.add(trd);
	}
	
	public User(String reg, String name){
		this.reg = reg;
		this.name = name;
	}
	
	public User() {
	}
	
	public String toString() {
		return name;
	}
	
}
