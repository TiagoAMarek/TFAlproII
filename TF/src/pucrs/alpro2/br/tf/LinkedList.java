package pucrs.alpro2.br.tf;

import java.util.Iterator;

/**
 * 
 * @authors Tiago A. Marek, Joao Garcia
 *
 * @param <E>
 */

public class LinkedList<E> implements ListTAD<E>, Iterable<E> {

	private Node<E> headDate;
	private Node<E> headStreet;
	private Node<E> tailDate;
	private Node<E> tailStreet;

	private int countDate;
	private int countStreet;


	// CLASSE NODO
	@SuppressWarnings("hiding")
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
	
	// CLASSE ITERADORA DE DATA
    public class LinkedListIteratorDate implements Iterator<E> {
    	 
        private Node<E> correnteDate = headDate.nextDate;
 
        @Override
        public boolean hasNext() {
            return correnteDate != tailDate;
        }
                
        @Override
        public E next() {
        	E valor = null;
        	valor = correnteDate.element;
            correnteDate = correnteDate.nextDate;
            return valor;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
 
    }

    // CLASSE ITERADORA DE RUA
    public class LinkedListIteratorStreet implements Iterator<E> {

	    private Node<E> correnteStreet = headStreet.nextStreet;
	
	    public boolean hasNext() {
	        return correnteStreet != tailStreet;
	    }
	
	    public E next() {
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

	// ADICIONA NODO POR DATA
	public void addDate(E e){
		Node<E> newNode = new Node<>(e);
		
		if(countDate == 0){
			// lista vazia
			headDate   = newNode;
			tailDate   = newNode;
			
			headDate.nextDate = newNode;
			tailDate.prevDate = newNode;
		}else{
			// lista com ao menos um elemento
			newNode.nextDate = headDate;
			headDate.prevDate = newNode;
			tailDate.nextDate = newNode;
			newNode.prevDate = tailDate;
		}
		
		tailDate = newNode;
		countDate++;
	}
	
	// ADICIONA NODO POR RUA
	public void addStreet(E e){
		Node<E> newNode = new Node<>(e);
		
		if(countStreet == 0){
			// lista vazia
			headStreet = newNode;
			tailStreet = newNode;
			
			headStreet.nextStreet = newNode;
			tailStreet.prevStreet = newNode;
		}else{
			// lista com ao menos um elemento
			newNode.nextStreet      = headStreet;
			headStreet.prevStreet   = newNode;
			tailStreet.nextStreet   = newNode;
			newNode.prevStreet      = tailStreet;
		}
		
		tailStreet = newNode;
		countStreet++;
	}
	
	@Override
	public E get(int index) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > countDate || index < 0)
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
	
	public E getByStreet(int index) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > countStreet || index < 0)
			throw new IndexOutOfBoundsException("Indice inexistente");
		
		Node<E> aux = headStreet;
		int i = 0;
		// PERCORRE A LISTA E RETORNA O ELEMENTO NO INDICE PASSADO
		while(aux != null){
			if(i == index){
				return aux.element;
			}
			aux = aux.nextStreet;
			i++;
		}
		return null;
	}

	// SETA ELEMENTO NA POSICAO INDICADA POR DATA
	@Override
	public void set(int index, E element) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > countDate || index < 0)
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
	
	// SETA ELEMENTO NA POSICAO INDICADA POR RUA
	public void setStreet(int index, E element) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > countStreet || index < 0)
			throw new IndexOutOfBoundsException("Indice inexistente");
		
		Node<E> aux = headStreet;
		int i = 0;
		// PERCORRE A LISTA E SETA O ELEMENTO NO INDICE PASSADO
		while(aux != null){
			if(i == index){
				aux.element = element;
			}
			aux = aux.nextStreet;
			i++;
		}
	}
	
	// RETORNAR SE LISTA DATA ESTA VAZIA
	@Override
	public boolean isEmpty() {
		if(countDate == 0)
			return true;
		return false;
	}

	// RETORNAR SE LISTA RUA ESTA VAZIA
	public boolean isEmptyStreet() {
		if(countStreet == 0)
			return true;
		return false;
	}

	// RETORNAR TAMANHO LISTA DATA
	@Override
	public int size() {
		return countDate;
	}
	
	// RETORNAR TAMANHO LISTA RUA
	public int sizeStreet() {
		return countStreet;
	}

	// VERIFICA SE ELEMENTO EXISTE LISTA DATA
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
	
	// VERIFICA SE ELEMENTO EXISTE LISTA RUA
		public boolean containsStreet(E e) {
			if(isEmptyStreet())
				throw new IndexOutOfBoundsException("Lista vazia!");
					
			Node<E> aux = headStreet;
			// VARRE A LISTA EM BUSCA DO ELEMENTO PASSADO RETORNA TRUE SE ENCONTRADO SENÃO FALSE
			while(aux != null){
				if(aux.element.equals(e)){
					return true;
				}
				aux = aux.nextStreet;
			}
			return false;
		}

	// ESVAZIA AS DUAS LISTAS
	@Override
	public void clear() {
		headDate = null;
		tailDate = null;
		headStreet = null;
		tailStreet = null;
		countDate = 0;
		countStreet = 0;

	}
	
	// REMOVE POR DATA
	@Override
	public E remove(int index) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > countDate || index < 0)
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
				countDate--;
				return aux.element;
			}
			i++;
			aux = aux.nextDate;
		}
		
		return null;
	}
	
	// REMOVE POR RUA
	public E removeStreet(int index) {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Lista vazia!");
		if(index > countStreet || index < 0)
			throw new IndexOutOfBoundsException("Indice inexistente");
		Node<E> aux = headStreet;
		
		// REMOVE O APONTAMENTO DO NODO ANTERIOR E DO PROXIMO PARA O NODO AUXILIAR NO INDICE INDICADO
		int i = 1;
		while(aux != null){
			if(i == index){
				if(aux == tailStreet){
					tailStreet = aux.prevStreet;
				}
				aux.prevStreet.nextStreet = aux.nextStreet;
				aux.nextStreet.prevStreet = aux.prevStreet;
				countStreet--;
				return aux.element;
			}
			i++;
			aux = aux.nextStreet;
		}
		
		return null;
	}

	// ITERADOR POR DATA
	@Override
	public Iterator<E> iterator() {
		return new LinkedListIteratorDate();
	}
	
	// ITERADOR POR RUA
	public Iterator<E> iteratorStreet() {
		return new LinkedListIteratorStreet();
	}
	
	
	// #### NÃO IMPLEMENTADOS #####
	
	@Override
	public void add(E e){
		// TODO Auto-generated method stub
	}


	@Override
	public void add(int index, E e) {
		// TODO Auto-generated method stub

	}

	@Override
	public int indexOf(E e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub	
		return null;
	}
}