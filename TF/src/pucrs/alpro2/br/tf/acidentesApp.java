package pucrs.alpro2.br.tf;

import java.text.ParseException;

public class acidentesApp {

	public static void main(String[] args) {

		LinkedList<Acidente> acidentes = new LinkedList<Acidente>();
		Arquivo arq = new Arquivo();
		try {
			acidentes = arq.lerArquivo();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
