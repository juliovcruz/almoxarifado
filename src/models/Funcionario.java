package models;

public class Funcionario {

	private int id;
	private int matricula;
	private String nome;
	
	public String toString() {
		return id + " - " + matricula + " - " + nome;
	}
	
	public int getID(){
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public Funcionario(int matricula, String nome){
		this.matricula = matricula;
		this.nome = nome;
	}
	
	public Funcionario() {
	}

	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
