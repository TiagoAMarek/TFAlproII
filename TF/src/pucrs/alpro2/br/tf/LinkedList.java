package pucrs.alpro2.br.tf;

import java.util.Iterator;

@SuppressWarnings("hiding")
public class LinkedList<E> implements ListTAD<E>, Iterable<E> {

	private Node<E> headDate;
	private Node<E> headStreet;
	private Node<E> tailDate;
	private Node<E> tailStreet;

	private int count;

	private class Node<E> {
		public E element;
		public Node<E> prevDate;
		public Node<E> prevStreet;
		public Node<E> nextDate;
		public Node<E> nextStreet;

		public Node(E e) {
			element = e;
			prevDate = null;
			prevStreet = null;
			nextDate = null;
			nextStreet = null;
		}
	}
	
    public class LinkedListIterator implements Iterator<E> {
    	 
        private Node<E> correnteDate = headDate.nextDate;
        private Node<E> correnteStreet = headStreet.nextStreet;

 
        @Override
        public boolean hasNext() {
            return correnteDate != tailDate;
        }
        
        public boolean hasNextStreet() {
            return correnteStreet != tailStreet;
        }
        
        @Override
        public E next() {
        	E valor = null;
        	valor = correnteDate.element;
            correnteDate = correnteDate.nextDate;
            return valor;
        }
        
        public E nextStreet() {
        	E valor = null;
        	valor = correnteStreet.element;
        	correnteStreet = correnteStreet.nextStreet;
            return valor;
        }
 
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
 
    }

	// Construtor
	public LinkedList(){
		clear();
	}

  @Override
	public void add(E e){
		Node<E> newNode = new Node<>(e);

		if(count == 0){
			// lista vazia
			headDate = newNode;
		}else{
			// lista com ao menos um elemento
			newNode.nextDate = headDate;
			headDate.prevDate = newNode;
			tailDate.nextDate = newNode;
			newNode.prevDate = tailDate;
		}
		
		tailDate = newNode;
		
		if(count == 0){
			// lista vazia
			headStreet = newNode;
		}else{
			// lista com ao menos um elemento
			newNode.nextStreet = headStreet;
			headStreet.prevStreet = newNode;
			tailStreet.nextStreet = newNode;
			newNode.prevStreet = tailStreet;
		}
		tailStreet = newNode;
		
		count++;
	}

	@Override
	public void add(int indexDate, int indexStreet, E e) {
		if (indexDate < 0 || indexDate > count)
			throw new IndexOutOfBoundsException("Indice invalido");
		if (indexStreet < 0 || indexStreet > count)
			throw new IndexOutOfBoundsException("Indice invalido");
		
		Node<E> newNode = new Node<>(e);
		// LISTA VAZIA
		if (indexDate == 0 && indexStreet == 0) {
			headStreet = newNode;
			headDate   = newNode;
			tailStreet = newNode;
			tailDate   = newNode;
		} else {
		// LISTA COM MAIS DE UM ELEMENTO
			Node<E> auxDate = headDate;
			Node<E> auxStreet = headStreet;
			int i = 1,
				j = 1;
			while (i <= indexDate){
				if(i == indexDate){
					auxDate.prevDate.nextDate = newNode;
					auxDate.prevDate = newNode;
					newNode.nextDate = auxDate;
					newNode.prevDate = auxDate.prevDate;
				}
				auxDate = auxDate.nextDate;
				i++;
			}
			while (j <= indexStreet){
				if(j == indexStreet){
					auxStreet.prevStreet.nextStreet = newNode;
					auxDate.prevStreet = newNode;
					newNode.nextStreet = auxStreet;
					newNode.prevStreet = auxStreet.prevStreet;
				}
				auxStreet = auxStreet.nextStreet;
				j++;
			}
		}
		count++;
	}
	

	@Override
	public E get(int index) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > count || index < 0)
			throw new IndexOutOfBoundsException("Indice inexistente");
		
		Node<E> aux = headDate;
		int i = 0;
		// PERCORRE A LISTA E RETORNA O ELEMENTO NO INDICE PASSADO
		while(aux != null){
			if(i == index){
				return aux.element;
			}
			aux = aux.nextDate;
			i++;
		}
		return null;
	}

	@Override
	public int indexOf(E e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(int index, E element) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > count || index < 0)
			throw new IndexOutOfBoundsException("Indice inexistente");
		
		Node<E> aux = headDate;
		int i = 0;
		// PERCORRE A LISTA E SETA O ELEMENTO NO INDICE PASSADO
		while(aux != null){
			if(i == index){
				aux.element = element;
			}
			aux = aux.nextDate;
			i++;
		}
	}

	@Override
	public boolean remove(E e) {
		if(count == 0)
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(!contains(e))
			return false;
		if(count == 1){
			headDate = null;
			count--;
			return true;
		}
		
		Node<E> aux = headDate;
				
		while(aux.nextDate != null){
			if(aux.nextDate.element.equals(e)){
				if(aux.nextDate.nextDate == null){
					aux.nextDate = null;
				} else {
					aux.nextDate = aux.nextDate.nextDate;
				}
				count--;
			} else {
				aux = aux.nextDate;
			}
		}
		return true;
	}

	@Override
	public E remove(int index) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > count || index < 0)
			throw new IndexOutOfBoundsException("Indice inexistente");
		Node<E> aux = headDate;
		
		// REMOVE O APONTAMENTO DO NODO ANTERIOR E DO PROXIMO PARA O NODO AUXILIAR NO INDICE INDICADO
		int i = 1;
		while(aux != null){
			if(i == index){
				if(aux == tailDate){
					tailDate = aux.prevDate;
				}
				aux.prevDate.nextDate = aux.nextDate;
				aux.nextDate.prevDate = aux.prevDate;
				count--;
				return aux.element;
			}
			i++;
			aux = aux.nextDate;
		}
		
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		if(count == 0)
			return true;
		return false;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean contains(E e) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
				
		Node<E> aux = headDate;
		// VARRE A LISTA EM BUSCA DO ELEMENTO PASSADO RETORNA TRUE SE ENCONTRADO SENÃO FALSE
		while(aux != null){
			if(aux.element.equals(e)){
				return true;
			}
			aux = aux.nextDate;
		}
		return false;
	}

	@Override
	public void clear() {
		headDate = null;
		tailDate = null;
		count = 0;
	}

	@Override
	public String toString() {
		if (count == 0)
			return "[]";

		Node<E> n = headDate;

		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for (int i = 0; i < count - 1; i++) {
			sb.append(n.element.toString() + ",");
			n = n.nextDate;
		}
		sb.append(n.element.toString());
		sb.append("]");

		return sb.toString();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}

}