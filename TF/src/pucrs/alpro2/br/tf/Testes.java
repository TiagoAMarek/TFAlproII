package pucrs.alpro2.br.tf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class Testes {

	public static void main(String[] args) {

		LinkedList<Acidente> lista = new LinkedList<Acidente>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:ss");

		Date date = null;
		try {
			date = sdf.parse("05-12-2012 08:15");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Acidente acidente1 = new Acidente("IPIRANGA", "ABALROAMENTO", date,
				"QUARTA-FEIRA", 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, "NUBLADO",
				"DIA", "NORTE", "AV ");
		Acidente acidente2 = new Acidente("BORGES DE MEDEIROS", "ABALROAMENTO",
				date, "QUINTA-FEIRA", 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0,
				"BOM", "DIA", "NORTE", "AV ");
		
		/*lista.add(0, 0, acidente1);
		lista.add(1, 1, acidente2);*/
		
		System.out.println(lista.get(0).getLocal());
		System.out.println(lista.getByStreet(1).getLocal());
		/*Iterator<Acidente> it = lista.iterator();
		while(it.hasNext()) {
			Acidente acidente = it.next();
			System.out.println(acidente.getLocal());
		}*/
	}

}
