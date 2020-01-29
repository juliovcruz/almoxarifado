package models;
import java.util.ArrayList;

import lombok.*;
@Getter
@Setter

public class Funcionario {
	
	private ArrayList<Troca> trocas;
	private int id;
	private String matricula;
	private String nome;
	
	public void addTroca(Troca trc) {
		trocas.add(trc);
	}
	
	public Funcionario(String matricula, String nome){
		this.matricula = matricula;
		this.nome = nome;
	}
	
	public Funcionario() {
	}
	
	public String toString() {
		return id + " - " + matricula + " - " + nome;
	}
	
}
