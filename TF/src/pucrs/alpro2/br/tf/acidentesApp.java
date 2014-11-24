package pucrs.alpro2.br.tf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

// CLASSE PRINCIPAL DA APP
public class acidentesApp {

	public static void main(String[] args) {
		// INICIALIZAÇÃO DE OBJETOS, LISTAS E VARIÁVEIS NECESSÁRIAS
		LinkedList<Acidente> acidentes = new LinkedList<Acidente>();
		ArrayList<Acidente> listaSem = new ArrayList<Acidente>();
		ListaAcidentes listAcd = new ListaAcidentes();

		// TENTA LER O ARQUIVO E RETORNA LISTA COM REGITROS
		try {
			acidentes = listAcd.createList();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(listAcd.tempoComMaisAcidentes());
		
		System.out.println(listAcd.momentoComMaisAcidentes());

		System.out.println(listAcd.dataMaisAcidentes());

		listaSem = listAcd.dataAcidente("AV WENCESLAU ESCOBAR", "SABADO");
		
		for(Acidente l : listaSem){
			System.out.println(l.toString());
		}

		System.out.println(listAcd.horaMaisAcidentes());

		System.out.println(listAcd.diaSemComMaisAcidentes());


		
		// IMPRIMI A LISTA ORDENADA POR DATA
		/*Iterator<Acidente> it = acidentes.iterator();
		while(it.hasNext()) {
			Acidente acidente = it.next();
			System.out.println(acidente.getDataHora());
		}*/
		
		// IMPRIMI A LISTA ORDENADA POR RUA
		/*Iterator<Acidente> it = acidentes.iteratorStreet();
		while(it.hasNext()) {
			Acidente acidente = it.next();
			System.out.println(acidente.getLocal());
		}*/
	}
}
