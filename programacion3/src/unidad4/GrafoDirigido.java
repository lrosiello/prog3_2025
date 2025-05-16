package unidad4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {

	// un map que tiene vertices cuya clave es Integer y tienen una lista de
	// adyacentes
	private Map<Integer, Vertice<T>> vertices;

	// sirve para llevar un control de cantidad de arcos del grafo
	private int cantArcos;

	public GrafoDirigido() {
		super();
		this.vertices = new HashMap<>();
		this.cantArcos = 0;
	}

	@Override
	public void agregarVertice(int verticeId) {
		if (!contieneVertice(verticeId)) {
			vertices.put(verticeId, new Vertice<T>(verticeId));
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		// Si el vértice existe
		if (contieneVertice(verticeId)) {
			// Primero, eliminamos todos los arcos que van hacia el vértice
			for (Vertice<T> vertice : vertices.values()) {
				if (vertice.existeArco(verticeId)) {
					vertice.removeArco(verticeId);
					cantArcos--;
				}
			}
			// Finalmente, eliminamos el vértice
			vertices.remove(verticeId);
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		// Verificamos que ambos vértices existan
		if (existenVertices(verticeId1, verticeId2)) {
			// Verificamos que no exista ya un arco entre ellos
			if (!existeArco(verticeId1, verticeId2)) {
				// Lo agregamos a la lista de adyacencia del vértice origen
				vertices.get(verticeId1).addArco(verticeId2, etiqueta);
				;
				cantArcos++;
			}
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		// Verificamos que ambos vértices existan
		if (existenVertices(verticeId1, verticeId2)) {
			// Si existe el arco, lo removemos
			Vertice<T> origen = vertices.get(verticeId1);
			if (origen.existeArco(verticeId2)) {
				origen.removeArco(verticeId2);
				cantArcos--;
			}
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		// Verificamos que ambos vértices existan
		if (existenVertices(verticeId1, verticeId2)) {
			// Iteramos sobre los arcos del vértice origen
			for (Arco<T> arco : vertices.get(verticeId1).obtenerArcos()) {
				if (arco.getVerticeDestino() == verticeId2) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// Verificamos que ambos vértices existan
		if (existenVertices(verticeId1, verticeId2)) {
			// Iteramos sobre los arcos del vértice origen
			for (Arco<T> arco : vertices.get(verticeId1).obtenerArcos()) {
				if (arco.getVerticeDestino() == verticeId2) {
					return arco;
				}
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return vertices.size();
	}

	@Override
	public int cantidadArcos() {
		return cantArcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// Verificamos que el vértice exista
		if (vertices.containsKey(verticeId)) {
			List<Integer> adyacentes = new ArrayList<>();
			// Recorremos todos los arcos del vértice y agregamos los destinos
			for (Arco<T> arco : vertices.get(verticeId).obtenerArcos()) {
				adyacentes.add(arco.getVerticeDestino());
			}
			return adyacentes.iterator();
		}
		return new ArrayList<Integer>().iterator(); // Devolvemos un iterador vacío si el vértice no existe
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		List<Arco<T>> todosLosArcos = new ArrayList<>();
		// Recorremos todos los vértices y agregamos todos sus arcos
		for (Vertice<T> vertice : vertices.values()) {
			for (Arco<T> arco : vertice.obtenerArcos()) {
				todosLosArcos.add(arco);
			}
		}
		return todosLosArcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// Verificamos que el vértice exista
		if (vertices.containsKey(verticeId)) {
			return vertices.get(verticeId).obtenerArcos().iterator();
		}
		return new ArrayList<Arco<T>>().iterator(); // Devolvemos un iterador vacío si el vértice no existe
	}

	// Metodo auxiliar de Validacion que verifica existencia de ambos vertices
	public Boolean existenVertices(int verticeId1, int verticeId2) {
		return vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2);
	}

	public Vertice<T> getVerticeById(int verticeId) {
		return vertices.get(verticeId);
	}

}