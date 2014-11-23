package pucrs.alpro2.br.tf;

import java.text.ParseException;
import java.util.Iterator;

// CLASSE PRINCIPAL DA APP
public class acidentesApp {

	public static void main(String[] args) {
		// INICIALIZAÇÃO DE OBJETOS, LISTAS E VARIÁVEIS NECESSÁRIAS
		LinkedList<Acidente> acidentes = new LinkedList<Acidente>();
		Arquivo arq = new Arquivo();

		// TENTA LER O ARQUIVO E RETORNA LISTA COM REGITROS
		try {
			acidentes = arq.lerArquivo();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// IMPRIMI A LISTA ORDENADA POR DATA
		Iterator<Acidente> it = acidentes.iterator();
		while(it.hasNext()) {
			Acidente acidente = it.next();
			System.out.println(acidente.getDataHora());
		}
	}
}
