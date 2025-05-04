package unidad1;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		
		MySimpleLinkedList<Integer> lista1 = new MySimpleLinkedList<Integer>();
		MySimpleLinkedList<Integer> lista2 = new MySimpleLinkedList<Integer>();
		MySimpleLinkedList<Integer> listaOrdenada = new MySimpleLinkedList<Integer>();
		
		
		lista1.insertarOrdenado(3);
		lista1.insertarOrdenado(1);
		lista1.insertarOrdenado(4);
		lista1.insertarOrdenado(10);
		lista1.insertarOrdenado(2);
		lista1.insertarOrdenado(3);
		
		lista2.insertarOrdenado(3);
		lista2.insertarOrdenado(5);
		lista2.insertarOrdenado(4);
		lista2.insertarOrdenado(6);
		lista2.insertarOrdenado(2);
		lista2.insertarOrdenado(3);
		
		System.out.println("LISTA 1");
		Iterator<Integer> iterador1 = lista1.iterator();
		while(iterador1.hasNext()) {
			Integer valor = iterador1.next();
			System.out.println(valor);
		}
		System.out.println("LISTA 2");
		Iterator<Integer> iterador2 = lista2.iterator();
		while(iterador2.hasNext()) {
			Integer valor = iterador2.next();
			System.out.println(valor);
		}
		
		listaOrdenada = listaOrdenada.encontrarNodosSoloEnPrimerLista(lista1, lista2);
		
		System.out.println("NODOS EN PRIMER LISTA");
		Iterator<Integer> iteradorPrimerLista = listaOrdenada.iterator();
		while(iteradorPrimerLista.hasNext()) {
			Integer valor = iteradorPrimerLista.next();
			System.out.println(valor);
		}

	}

}
