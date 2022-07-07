package algoritmo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import instancias.Censo;

public class SolverFuerzaBruta {

	private ArrayList<ArrayList<Integer>> incumbente;
	private ArrayList<ArrayList<Integer>> actual;
	private Censo instancia;

	public SolverFuerzaBruta() {
		incumbente = new ArrayList<ArrayList<Integer>>();
		actual = new ArrayList<ArrayList<Integer>>();
	}

	public Solucion resolver(Censo instancia) throws CloneNotSupportedException {
		if (instancia == null || instancia.getManzanasRadio().isEmpty()) {
			throw new IllegalArgumentException("No se ingresaron manzanas o la instancia es nula");
		}
		this.instancia = instancia;
		recursion(0);
		return solucion();
	}

	private Solucion solucion() throws CloneNotSupportedException {
		if (instancia.getcensistasCenso().size() < incumbente.size()) {
			throw new IllegalArgumentException("Cantidad de censitas insuficientes");
		}
		int censista = 0;
		for (ArrayList<Integer> subConjunto : incumbente) {
			for (Integer manzana : subConjunto) {
				instancia.asignarCensistaAlRadio(manzana, instancia.getCensistaIndice(censista));
			}
			censista++;
		}
		return new Solucion(instancia);
	}

	private void recursion(int inicial) {
		// Caso Base
		if (manzanasVisitadas() == instancia.idsManzanasCenso().size()) {

			if (incumbente.isEmpty() || incumbente.size() >= actual.size()) {
				incumbente = clonar();
//				imprimirSolucion();
			}
			return;
		}
		if (actual.isEmpty()) {
			ArrayList<Integer> c = new ArrayList<Integer>();
			c.add(inicial);
			actual.add(c);
			recursion(inicial + 1);
		}
		// Caso recursivo
		agregar(inicial);
		recursion(inicial + 1);

	}

	private void agregar(Integer inicial) { // se deveria arrogar un caso recursivo para cada subconjunto que cumpla? --
											// Testear
		if (noSePuedeInsertar(inicial)) {
			ArrayList<Integer> c = new ArrayList<Integer>();
			c.add(inicial);
			actual.add(c);
		} else {
			for (ArrayList<Integer> subConjuntos : actual) {
				if (subConjuntos.size() < 3 && (!subConjuntos.contains(inicial))
						&& esVecinoDeAlguno(subConjuntos, inicial)) {
					subConjuntos.add(inicial);
				}
			}
		}

	}

	private boolean esVecinoDeAlguno(ArrayList<Integer> subConjuntos, Integer inicial) {
		for (Integer i : subConjuntos) {
			if (instancia.getRadioCensal().existeArista(inicial, i)) {
				return true;
			}
		}
		return false;
	}

	private boolean noSePuedeInsertar(Integer inicial) {
		boolean ret = true;
		for (ArrayList<Integer> subConjuntos : actual) {
			if (!(subConjuntos.size() >= 3 || subConjuntos.contains(inicial)
					|| !esVecinoDeAlguno(subConjuntos, inicial))) {
				ret = ret && false;
			}
		}
		return ret;
	}

	private ArrayList<ArrayList<Integer>> clonar() {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> subConjuntos : actual) {
			ret.add(subConjuntos);
		}
		return ret;
	}

	private void imprimirSolucion() {
		for (ArrayList<Integer> subConjuntos : actual) {
			System.out.print("{");
			for (Integer i : subConjuntos) {
				System.out.print(i + ",");
			}
			System.out.print("}");
			System.out.println();
		}
	}

	private int manzanasVisitadas() {
		Set<Integer> manzanas = new HashSet<Integer>();
		for (ArrayList<Integer> subConjuntos : actual) {
			for (Integer i : subConjuntos) {
				manzanas.add(i);
			}
		}
		return manzanas.size();
	}

}
