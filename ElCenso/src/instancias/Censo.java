package instancias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import grafo.Arista;

public class Censo {

	private RadioCensal radioCensal;
	private ArrayList<Censista> listaCensistas;

	public Censo(ArrayList<Censista> listaCensistas, ArrayList<Manzana> listaManzanas) {
		verificarCensistasRepetidos(listaCensistas);
		verificarManzanasRepetidas(listaManzanas);
		if (listaCensistas.isEmpty() || listaManzanas.isEmpty())
			throw new IllegalArgumentException("Los datos ingresados no son validos");

		this.radioCensal = new RadioCensal(listaManzanas);
		this.listaCensistas = listaCensistas;
	}

	private void verificarCensistasRepetidos(ArrayList<Censista> listaCensistas) {
		for (int c = 0; c < listaCensistas.size(); c++) {
			if (c != listaCensistas.size()) {
				for (int c2 = c + 1; c2 < listaCensistas.size(); c2++) {
					if (listaCensistas.get(c).getDNI() == listaCensistas.get(c2).getDNI())
						throw new IllegalArgumentException("Existen censistas repetidos en la lista");
				}
			}
		}
	}

	private void verificarManzanasRepetidas(ArrayList<Manzana> listaManzanas) {
		for (int c = 0; c < listaManzanas.size(); c++) {
			if (c != listaManzanas.size()) {
				for (int c2 = c + 1; c2 < listaManzanas.size(); c2++) {
					if (listaManzanas.get(c).getIdentificador() == listaManzanas.get(c2).getIdentificador())
						throw new IllegalArgumentException("Existen manzanas repetidos en la lista");
				}
			}
		}
	}

	public Censista getCensistaIndice(int indiceCensista) {
		if (listaCensistas.size() <= indiceCensista || 0 > indiceCensista)
			throw new IllegalArgumentException("El indice del censista no es valido");

		return listaCensistas.get(indiceCensista);
	}

	public void asignarCensistaAlRadio(Integer idManzana, Censista censista) {

		censistaNoPerteneceAlCenso(censista);

		radioCensal.asignarCensista(idManzana, censista);
	}

	public List<Censista> censistasNoAsignadosAlRadio() {
		List<Censista> noAsignados = new ArrayList<Censista>();
		for (Censista censista : listaCensistas) {
			if (!radioCensal.getCensistas().contains(censista)) {
				noAsignados.add(censista);
			}
		}
		return noAsignados;
	}

	public int cantCensistasAsignadosARadio() {
		return radioCensal.cantCensistasAsignadosAManzanas();
	}

	public Censista getCensistaAsignadoAManzana(Manzana manzana) {
		return radioCensal.censistaAsignadoaManzana(manzana);
	}

	public boolean todasManzanasDelRadioAsignadas() {
		return radioCensal.todasManzanasAsignadas();
	}

	public String infoManzanaRadioCensal(Manzana manzana) {
		return radioCensal.infoManzana(manzana);
	}

	public boolean manzanasDelRadioContiguas(Integer idManzana1, Integer idManzana2) {
		return radioCensal.sonManzanasVecinas(idManzana1, idManzana2);
	}

	public List<Integer> idsManzanasCenso() {
		return radioCensal.idsManzanas();
	}

	public Set<Integer> getManzanasContiguasDeManzana(Integer idManzana) {
		return radioCensal.manzanasVecinas(idManzana);
	}

	public List<Integer> idManzanasCensista(Censista censista) {
		censistaNoPerteneceAlCenso(censista);

		List<Integer> idManzanasCensista = new ArrayList<Integer>();

		for (Manzana manzana : censista.getManzanasAsignadas()) {
			idManzanasCensista.add(manzana.getIdentificador());
		}

		return idManzanasCensista;
	}

	public boolean censistaSinManzanasAsignadas(Censista censista) {
		censistaNoPerteneceAlCenso(censista);
		return censista.getCantManzanasAsignadas() == 0;
	}

	public boolean censistaTieneTresManzanas(Censista censista) {
		censistaNoPerteneceAlCenso(censista);
		if (censista.getCantManzanasAsignadas() < 3)
			return false;

		return true;
	}

	private void censistaNoPerteneceAlCenso(Censista censista) {
		if (!listaCensistas.contains(censista))
			throw new IllegalArgumentException("El censista no es valido");
	}

	public RadioCensal getRadioCensal() {
		return radioCensal;
	}

	public List<Censista> getCensistasRadioCensal() {
		return radioCensal.getCensistas();
	}

	public ArrayList<Censista> getcensistasCenso() throws CloneNotSupportedException {
		ArrayList<Censista> copiaCensistas = new ArrayList<Censista>();

		for (Censista c : listaCensistas) {
			copiaCensistas.add(c.clone());
		}
		return copiaCensistas;
	}

	public ArrayList<Manzana> getManzanasRadio() throws CloneNotSupportedException {
		return radioCensal.getManzanas();
	}

}
