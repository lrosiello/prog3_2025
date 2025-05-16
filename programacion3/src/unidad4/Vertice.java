package unidad4;

import java.util.HashMap;
import java.util.Map;

public class Vertice<T> {

	private int verticeId;
	private int cantArcos;
	private String color;
	private int tiempoDescubrimiento;
	private int tiempoFinalizacion;
	private Map<Integer, Arco<T>> arcos;

	public Vertice(int verticeId) {
		this.verticeId = verticeId;
		this.cantArcos = 0;
		this.color = "blanco";
		this.arcos = new HashMap<>();
		this.tiempoDescubrimiento = 0;
		this.tiempoFinalizacion = 0;
	}

	public int getTiempoDescubrimiento() {
		return tiempoDescubrimiento;
	}

	public void setTiempoDescubrimiento(int tiempoDescubrimiento) {
		this.tiempoDescubrimiento = tiempoDescubrimiento;
	}

	public int getTiempoFinalizacion() {
		return tiempoFinalizacion;
	}

	public void setTiempoFinalizacion(int tiempoFinalizacion) {
		this.tiempoFinalizacion = tiempoFinalizacion;
	}

	public int getVerticeId() {
		return verticeId;
	}

	public void setVerticeId(int verticeId) {
		this.verticeId = verticeId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCantArcos() {
		return cantArcos;
	}

	public void addArco(int verticeDestino, T etiqueta) {
		this.arcos.put(verticeDestino, new Arco<>(this.getVerticeId(), verticeDestino, etiqueta));
		cantArcos++;
	}

	public void removeArco(int verticeDestino) {
		this.arcos.remove(verticeDestino);
	}

	public boolean existeArco(int verticeDestino) {
		return arcos.containsKey(verticeDestino);
	}

	public Iterable<Arco<T>> obtenerArcos() {
		return arcos.values();
	}

	public Arco<T> getArco(int destino) {
		return arcos.get(destino);
	}

}
