package unidad4;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Recorrido<T> {

	private GrafoDirigido<T> grafo;
	private int tiempo;
	private List<Integer> ordenVisita;

	public Recorrido(GrafoDirigido<T> grafo) {
		this.grafo = grafo;
	}

	public List<Integer> dfs() {

		this.tiempo = 0;
		this.ordenVisita = new ArrayList<>();

		Iterator<Integer> vertices = grafo.obtenerVertices();

		while (vertices.hasNext()) {
			Vertice<T> vertice = grafo.getVerticeById(vertices.next());
			vertice.setColor("blanco");
		}

		vertices = grafo.obtenerVertices();

		while (vertices.hasNext()) {
			Integer verticeId = vertices.next();
			Vertice<T> vertice = grafo.getVerticeById(verticeId);
			if (vertice.getColor().equals("blanco")) {
				dfs_visit(verticeId);
			}
		}
		System.out.println("Orden de visita DFS: " + ordenVisita);
		return ordenVisita;
	}

	private void dfs_visit(Integer verticeId) {
		Vertice<T> vertice = grafo.getVerticeById(verticeId);
		vertice.setColor("amarillo");
		tiempo++;
		vertice.setTiempoDescubrimiento(tiempo);

		ordenVisita.add(verticeId);

		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(verticeId);
		while (adyacentes.hasNext()) {
			Integer adyacenteId = adyacentes.next();
			Vertice<T> adyacente = grafo.getVerticeById(adyacenteId);

			if (adyacente.getColor().equals("blanco")) {
				dfs_visit(adyacenteId);
			} else if (adyacente.getColor().equals("amarillo")) {
				System.out.println("Hay un ciclo entre " + verticeId + " y " + adyacenteId);
			}
		}

		vertice.setColor("negro");
		tiempo++;
		vertice.setTiempoFinalizacion(tiempo);
	}

	public List<Integer> bfs() {

		this.tiempo = 0;
		this.ordenVisita = new ArrayList<>();

		Iterator<Integer> vertices = grafo.obtenerVertices();

		while (vertices.hasNext()) {
			Vertice<T> vertice = grafo.getVerticeById(vertices.next());
			vertice.setColor("blanco");
		}

		vertices = grafo.obtenerVertices();

		while (vertices.hasNext()) {
			Integer verticeId = vertices.next();
			Vertice<T> vertice = grafo.getVerticeById(verticeId);
			if (vertice.getColor().equals("blanco")) {
				bfs_visit(verticeId);
			}
		}
		System.out.println("Orden de visita BFS: " + ordenVisita);
		return ordenVisita;
	}

	
	
	private void bfs_visit(Integer verticeId) {
		Queue<Integer> queue = new LinkedList<>();
		Vertice<T> vertice = grafo.getVerticeById(verticeId);

		vertice.setColor("amarillo");
		tiempo++;
		vertice.setTiempoDescubrimiento(tiempo);
		ordenVisita.add(verticeId);
		queue.add(verticeId);

		while (!queue.isEmpty()) {
			Integer actualId = queue.poll();
			Vertice<T> actual = grafo.getVerticeById(actualId);

			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actualId);
			while (adyacentes.hasNext()) {
				Integer adyacenteId = adyacentes.next();
				Vertice<T> adyacente = grafo.getVerticeById(adyacenteId);
				if (adyacente.getColor().equals("blanco")) {
					adyacente.setColor("amarillo");
					tiempo++;
					adyacente.setTiempoDescubrimiento(tiempo);
					ordenVisita.add(adyacenteId);
					queue.add(adyacenteId);
				}
			}

			actual.setColor("negro");
			tiempo++;
			actual.setTiempoFinalizacion(tiempo);
		}
	}

}
