package view;
import java.util.ArrayList;

import models.Funcionario;
import modelsBd.FuncionarioBD;

public class Principal {

	public static ArrayList<Funcionario> readFuncs() {
		ArrayList<Funcionario> funcs = new ArrayList<>();
		
		FuncionarioBD fbd = new FuncionarioBD();
		
		for(Funcionario f: fbd.read()) {
			funcs.add(f);	
		}
		
		return funcs;
	}
	
	public static void main (String[] args) {
		ArrayList<Funcionario> funcs = new ArrayList<>();
		
		//Funcionario f = new Funcionario(2003,"Victor");
		//FuncionarioBD fdb = new FuncionarioBD();
		//fdb.create(f);
		
		funcs = readFuncs();
		
		for(int i =0;i<funcs.size();i++) {
			System.out.println(funcs.get(i));
		}
		
	}
}
