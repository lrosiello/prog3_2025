package unidad1;

import java.util.Iterator;

public class MySimpleLinkedList<T extends Comparable<T>> implements Iterable<T> {
	
	private Node<T> first;
	private int size;
	
	public MySimpleLinkedList() {
		this.first = null;
	}
	
	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info,null);
		if(!this.isEmpty()) {
			tmp.setNext(this.first);
			this.first = tmp;
			size++;
		}else {
			this.first = tmp;
			size++;
		}
	}
	
	public T extractFront() {
		Node<T> tmp = new Node<T>();
		if(this.isEmpty()) {
			return null;
		} else if(this.first.hasNext()) {
			tmp = first;
			this.first = first.getNext();
			size--;
			return tmp.getInfo();
		} else {
			tmp = this.first;
			this.first = null;
			size--;
			return tmp.getInfo();
		}
	}

	public boolean isEmpty() {
		return this.first == null;
	}
	
	public T get(T index) {
		T cursor = this.first.getInfo();
		
		Iterator<T> recorrido = this.iterator();
		while(recorrido.hasNext()) {
			cursor = recorrido.next();
			if (cursor == index) {
				return cursor;
			}
		}
		return null;
	}
	
	public T extractByIndex(T index) {
		Node<T> anterior = null;
		Node<T> nodoActual = this.first;
		
		while(nodoActual != null) {
			if(nodoActual.getInfo() == index) {
				if(anterior == null) { //pregunto si es el primer NODO
					this.first = nodoActual.getNext();
					size--;
				}else {
					anterior.setNext(nodoActual.getNext());
					size--;
				}
			}
			anterior = nodoActual;
			nodoActual = nodoActual.getNext();
		}
		return null;
	}
	
	
	
	public void insertarOrdenado(T info) {
	    Node<T> nuevo = new Node<T>(info, null);
	    Node<T> nodoActual = this.first;
	    Node<T> nodoAnterior = null;

	    // Caso especial: lista vacía o insertar al inicio
	    if (nodoActual == null || info.compareTo(nodoActual.getInfo()) <= 0) {
	        nuevo.setNext(first);
	        first = nuevo;
	        size++;
	        return;
	    }

	    // Recorremos hasta encontrar la posición correcta
	    while (nodoActual != null && info.compareTo(nodoActual.getInfo()) > 0) {
	        nodoAnterior = nodoActual;
	        nodoActual = nodoActual.getNext();
	    }

	    // Insertamos en la posición correcta
	    nuevo.setNext(nodoActual);
	    nodoAnterior.setNext(nuevo);
	    size++;
	}
	
	public MySimpleLinkedList<T> encontrarNodosSoloEnPrimerLista (MySimpleLinkedList<T> lista1, MySimpleLinkedList<T> lista2) {
		
		MyIterator<T>  it1 = lista1.iterator();
		MyIterator<T>  it2 = lista2.iterator();
		MySimpleLinkedList<T> salida = new MySimpleLinkedList<T>();
		
		while(it1.hasNext() && it2.hasNext()) {
			T val1 = it1.get();
			T val2 = it2.get();
			
			if(val1.compareTo(val2) < 0) {
				it1.next();
				salida.insertFront(val1);
			}else if (val1.compareTo(val2) == 0) {
				it1.next();
				it2.next();
			}else {
				it2.next();
			}
		}
		
		while(it1.hasNext()) {
			salida.insertFront(it1.get());
			it1.next();
		}
		
		return salida;
		
	}

	
	public int getSize() {
		return this.size;
	}
	
	
	@Override
	public String toString() {
		return "MySimpleLinkedList [first=" + first + ", size=" + size + "]";
	}

	@Override
	public MyIterator<T> iterator() {
	    return new MyIterator<>(this.first);
	}
	
}