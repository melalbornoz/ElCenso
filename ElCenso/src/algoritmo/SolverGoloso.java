package algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import instancias.Censista;
import instancias.Censo;

public class SolverGoloso {
	private Comparator<Integer> comparador;
	private Censo instancia;
	private int indiceCensista;

	public SolverGoloso(Censo instancia, Comparator<Integer> comparador) throws CloneNotSupportedException {
		if (instancia == null || instancia.getManzanasRadio().isEmpty()) {
			throw new IllegalArgumentException("No se ingresaron manzanas o la instancia es nula");
		}
		this.instancia = instancia;
		this.comparador = comparador;
		this.indiceCensista = 0;
	}

	public Solucion resolver() throws CloneNotSupportedException {
		List<Integer> mAsignadas = new ArrayList<Integer>();
		Censista censista = instancia.getCensistaIndice(indiceCensista);
		for (Integer manzana : manzanasOrdenadas()) {
			if (!mAsignadas.contains(manzana)) {
			  if(tomarOtroCensista(censista, manzana, mAsignadas)) { 
				indiceCensista++;
				censista = instancia.getCensistaIndice(indiceCensista);
			  }
		    
			asignarManzana(censista, manzana, mAsignadas);
			//asigno a todas las manzanas que no estan asignadas ycomparten cuadras con esa manzana:
			asignarManzanasVecinas(censista, manzana, mAsignadas);
		 }
		}
		
		Solucion solucion = new Solucion(instancia);
		return solucion;
	}
	

	private void asignarManzanasVecinas(Censista censista, Integer manzana, List<Integer> mAsignadas) {
	 for (Integer manzanaVecina : instancia.getManzanasContiguasDeManzana(manzana)) {
		 if (!mAsignadas.contains(manzanaVecina)) {
			// si el censista ya tiene sus tres manzanas completas, tomo otro censista y le
			// asigno la manzana
			if (tomarOtroCensista(censista, manzanaVecina, mAsignadas)) {
				indiceCensista++;
				censista = instancia.getCensistaIndice(indiceCensista);
				asignarManzana(censista, manzanaVecina, mAsignadas);
			} else if (algunaManzanaEsVecinas(instancia.idManzanasCensista(censista), manzanaVecina))
				asignarManzana(censista, manzanaVecina, mAsignadas);
		}
	 }
	}
	
	private void asignarManzana(Censista censista, Integer manzana, List<Integer> mAsignadas) {
		instancia.asignarCensistaAlRadio(manzana, censista);
		mAsignadas.add(manzana);
	}

	private boolean algunaManzanaEsVecinas(List<Integer> manzanasCensista, Integer manzana) {
		boolean algunaManzanaVecina = false;
		for (Integer manzanaCensista : manzanasCensista) {
			algunaManzanaVecina = algunaManzanaVecina || instancia.manzanasDelRadioContiguas(manzanaCensista, manzana);
		}
		return algunaManzanaVecina;
	}

	private boolean tomarOtroCensista(Censista censista, Integer manzana, List<Integer> mAsignadas) {
		if (!mAsignadas.contains(manzana) && (instancia.censistaTieneTresManzanas(censista)
				|| !algunaManzanaEsVecinas(instancia.idManzanasCensista(censista), manzana)
						&& !instancia.censistaSinManzanasAsignadas(censista))) {
			return true;
		}
		return false;
	}

	private List<Integer> manzanasOrdenadas() {
		List<Integer> ret = instancia.idsManzanasCenso();
		Collections.sort(ret, comparador);

		return ret;
	}
}
