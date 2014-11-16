package pucrs.alpro2.br.listTAD;

import java.io.ObjectInputStream.GetField;

public class Testes {

	public static void main(String[] args) {

		ListTAD<Integer> lista = new LinkedList<>();
		System.out.println(lista);
		
		lista.add(200);
		System.out.println(lista);
		lista.add(300);
		System.out.println(lista);
		lista.add(500);
		System.out.println(lista);
		lista.remove(2);
		System.out.println(lista);
		
	}

}
