package pucrs.alpro2.br.tf;

import pucrs.alpro2.br.listTAD.*;

public class acidentesApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Acidentes> lista;
		Arquivo arq = new Arquivo();
		lista = arq.lerArquivo();
		
		for(Acidentes l : lista){
			System.out.println(l.getDiaSem());
		}
		
		
	}

}
