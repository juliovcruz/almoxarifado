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
	
	public Trade(User user, Item item, String descr, int day, int month, int year) {
		this.user = user;
		this.item = item;
		this.descr = descr;
		this.day = day;
		this.month = month;
		this.year = year;
		this.date = day + "/" + month + "/" + year;
	}
	
	public Trade() {
		
	}

	public Trade(Item item, String descr, int day, int month, int year) {
		this.item = item;
		this.descr = descr;
		this.day = day;
		this.month = month;
		this.year = year;
		this.date = day + "/" + month + "/" + year;
	}

}
