package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Grafo {
	private ArrayList<Set<Integer>> vecinos;

	private ArrayList<Arista> aristas;

	private int cantVertices;

	public Grafo(int vertices) {

		this.cantVertices = vertices;
		this.aristas = new ArrayList<Arista>();
		this.vecinos = new ArrayList<Set<Integer>>(vertices);

		for (int i = 0; i < vertices; i++) {
			this.vecinos.add(new HashSet<Integer>()); 
		}
	}

	public void agregarArista(int i, int j) {

		verificarArista(i, j, "agregar");

		if (existeArista(i, j)) 
			throw new IllegalArgumentException(
					"Se intento agregar una arista ya existente: " + i + "|" + j );
			
		Arista aristaNueva = new Arista(i, j);
		aristas.add(aristaNueva); 
		
		agregarVecinos(i, j);

	}
	
	private void agregarVecinos(int i, int j) {
		vecinos.get(i).add(j);
		vecinos.get(j).add(i);
	}

	public void eliminarArista(int i, int j) {

		verificarArista(i, j, "eliminar");

		if (!existeArista(i, j)) 
			throw new IllegalArgumentException(
					"Se intento eliminar una arista no existente" + i + "|" + j + "|" );
		
		aristas.remove(new Arista(i, j));
		
		borrarVecino(i, j);
	}
	
	private void borrarVecino(int i, int j) {
		vecinos.get(i).remove(j);
		vecinos.get(j).remove(i);
	}

	private void verificarArista(int i, int j, String tipo) {
		if (i == j)
			throw new IllegalArgumentException("Se intento " + tipo + " una arista con i = j : " + i + "|" + j);

		verificarVertice(i, tipo);
		verificarVertice(j, tipo);

	}

	public boolean existeArista(int i, int j) {
		return (vecinos.get(i).contains(j) && vecinos.get(j).contains(i));
	}


	// -----------METODOS DE VERTICES-----------------//

	public Set<Integer> vecinosVertice(int i) {

		verificarVertice(i, " un vertice ");

		return vecinos.get(i);
	}

	private void verificarVertice(int i, String tipo) {

		if (i < 0 || i >= cantVertices)
			throw new IllegalArgumentException("Se intento usar " + tipo + " con valores, fuera de rango: " + i);
	}

	// -----------GETTERS Y EQUALS-----------------//

	public ArrayList<Integer> getVertices(){
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		for(int i = 0; i <cantVertices; i++) {
			vertices.add(i);
		}
		return vertices;
	}
	
	public int getCantVecinosVertice(int i) {
		return vecinos.get(i).size();
	}
	
	public int getCantVertices() {
		return cantVertices;
	}

	public ArrayList<Arista> getAristas() {
		return aristas;
	}
	
	public Arista getArista(int i, int j) {
		if (!existeArista(i, j)) {
			return null;
		}
		Arista arista = null;
		for (Arista a : aristas) {
			if ((a.getOrigen() == i && a.getDestino() == j)
					|| (a.getOrigen() == j && a.getDestino() == i)) {
				arista = a;
			}
		}
		return arista;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grafo other = (Grafo) obj;
		return Objects.equals(aristas, other.aristas) && cantVertices == other.cantVertices
				&& Objects.equals(vecinos, other.vecinos);
	}

}
