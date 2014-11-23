package pucrs.alpro2.br.tf;

import java.text.ParseException;
import java.util.Iterator;

// CLASSE PRINCIPAL DA APP
public class acidentesApp {

	public static void main(String[] args) {
		// INICIALIZA��O DE OBJETOS, LISTAS E VARI�VEIS NECESS�RIAS
		LinkedList<Acidente> acidentes = new LinkedList<Acidente>();
		ListaAcidentes listAcd = new ListaAcidentes();

		// TENTA LER O ARQUIVO E RETORNA LISTA COM REGITROS
		try {
			acidentes = listAcd.createList();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// IMPRIMI A LISTA ORDENADA POR DATA
		Iterator<Acidente> it = acidentes.iterator();
		while(it.hasNext()) {
			Acidente acidente = it.next();
			System.out.println(acidente.getDataHora());
		}
		
		// IMPRIMI A LISTA ORDENADA POR RUA
		/*Iterator<Acidente> it = acidentes.iteratorStreet();
		while(it.hasNext()) {
			Acidente acidente = it.next();
			System.out.println(acidente.getLocal());
		}*/
	}
}
