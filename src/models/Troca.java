package models;

import lombok.*;

@Getter
@Setter

public class Troca {

	private int id;
	private String desc;
	private int dia;
	private int mes;
	private int ano;
	private String data;
	private Item item;
	
	
	
	public Troca(Item item, String desc, int dia, int mes, int ano) {
		this.item = item;
		this.desc = desc;
		this.dia = dia;
		this.mes = mes;
		this.data = dia + "/" + mes + "/" + ano;
	}
	
}
