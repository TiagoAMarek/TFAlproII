package pucrs.alpro2.br.listTAD;

import java.util.Iterator;

public class LinkedList<E> implements ListTAD<E> {

	private Node<E> head;
	private Node<E> tail;
	private int count;

	private class Node<E> {
		public E element;
		public Node<E> prev;
		public Node<E> next;

		public Node(E e) {
			element = e;
			prev = null;
			next = null;
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
			head = newNode;
		}else{
			// lista com ao menos um elemento
			newNode.next = head;
			head.prev = newNode;
			tail.next = newNode;
			newNode.prev = tail;
		}
		tail = newNode;
		count++;
	}

	@Override
	public void add(int index, E e) {
		if (index < 0 || index > count)
			throw new IndexOutOfBoundsException("Indice invalido");

		Node<E> newNode = new Node<>(e);
		// LISTA VAZIA
		if (index == 0) {
			head = newNode;
		} else {
		// LISTA COM MAIS DE UM ELEMENTO
			Node<E> aux = head;
			int i = 1;
			while (i <= index){
				if(i == index){
					aux.prev.next = newNode;
					aux.prev = newNode;
					newNode.next = aux;
					newNode.prev = aux.prev;
				}
				aux = aux.next;
				i++;
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
		
		Node<E> aux = head;
		int i = 0;
		// PERCORRE A LISTA E RETORNA O ELEMENTO NO INDICE PASSADO
		while(aux != null){
			if(i == index){
				return aux.element;
			}
			aux = aux.next;
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
		
		Node<E> aux = head;
		int i = 0;
		// PERCORRE A LISTA E SETA O ELEMENTO NO INDICE PASSADO
		while(aux != null){
			if(i == index){
				aux.element = element;
			}
			aux = aux.next;
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
			head = null;
			count--;
			return true;
		}
		
		Node<E> aux = head;
				
		while(aux.next != null){
			if(aux.next.element.equals(e)){
				if(aux.next.next == null){
					aux.next = null;
				} else {
					aux.next = aux.next.next;
				}
				count--;
			} else {
				aux = aux.next;
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
		Node<E> aux = head;
		
		// REMOVE O APONTAMENTO DO NODO ANTERIOR E DO PROXIMO PARA O NODO AUXILIAR NO INDICE INDICADO
		int i = 1;
		while(aux != null){
			if(i == index){
				if(aux == tail){
					tail = aux.prev;
				}
				aux.prev.next = aux.next;
				aux.next.prev = aux.prev;
				count--;
				return aux.element;
			}
			i++;
			aux = aux.next;
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
				
		Node<E> aux = head;
		// VARRE A LISTA EM BUSCA DO ELEMENTO PASSADO RETORNA TRUE SE ENCONTRADO SEN�O FALSE
		while(aux != null){
			if(aux.element.equals(e)){
				return true;
			}
			aux = aux.next;
		}
		return false;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		count = 0;
	}

	@Override
	public String toString() {
		if (count == 0)
			return "[]";

		Node<E> n = head;

		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for (int i = 0; i < count - 1; i++) {
			sb.append(n.element.toString() + ",");
			n = n.next;
		}
		sb.append(n.element.toString());
		sb.append("]");

		return sb.toString();
	}

}