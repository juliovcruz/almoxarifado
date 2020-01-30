package models;

import lombok.*;

@Getter
@Setter

public class Trade {

	private int id;
	private User user;
	private String descr;
	private int day;
	private int month;
	private int year;
	private String date;
	private Item item;
	private String userName;
	private String itemName;
	private String userReg;
	private String amount;
	private int id_user;
	private int id_item;
	
	public Trade(User user, Item item, String descr, int day, int month, int year,String amount) {
		this.user = user;
		this.item = item;
		this.descr = descr;
		this.day = day;
		this.month = month;
		this.year = year;
		this.date = day + "/" + month + "/" + year;
		this.amount = amount;
	}
	
	public Trade() {
		
	}

	public Trade(Item item, String descr, int day, int month, int year,String amount) {
		this.item = item;
		this.descr = descr;
		this.day = day;
		this.month = month;
		this.year = year;
		this.date = day + "/" + month + "/" + year;
		this.amount = amount;
	}

}
