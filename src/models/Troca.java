package models;

public class Troca {

	private int id;
	private String desc;
	private String item;
	private int dia;
	private int mes;
	private int ano;
	private String data;
	
	public Troca(String item, String desc, int dia, int mes, int ano) {
		this.item = item;
		this.desc = desc;
		this.dia = dia;
		this.mes = mes;
		this.data = dia + "/" + mes + "/" + ano;
	}
	
}
