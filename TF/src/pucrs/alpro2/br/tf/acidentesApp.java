package pucrs.alpro2.br.tf;

import java.util.LinkedList;

public class acidentesApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Acidentes> lista = null;
		Arquivo arq = new Arquivo();
		lista = arq.lerArquivo();
		
		for(Acidentes l : lista){
			System.out.println(l.getDiaSem());
		}
		
		
	}

}
