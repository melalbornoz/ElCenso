package instancias;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import grafo.Grafo;

public class RadioCensal extends Grafo {
	private List<Censista> listaCensistas;
	private ArrayList<Manzana> listaManzanas;

	public RadioCensal(ArrayList<Manzana> listaManzanas) {
		super(listaManzanas.size());

		this.listaCensistas = new ArrayList<Censista>();
		this.listaManzanas = listaManzanas;

		agregarManzanasContiguas();
	}

	private void agregarManzanasContiguas() {
		for (int manzana = 0; manzana < listaManzanas.size(); manzana++) {
			for (int manzanaV = manzana; manzanaV < listaManzanas.size(); manzanaV++) {
				if (!existeArista(manzana, manzanaV) && manzana != manzanaV && listaManzanas.get(manzana)
						.algunaCuadraEsIgual(listaManzanas.get(manzanaV).getCuadrasManzana()))
					agregarArista(manzana, manzanaV);
			}
		}
	}

	public void asignarCensista(Integer idManzana, Censista censista) {
		if (!listaCensistas.contains(censista)) {
			listaCensistas.add(censista);
		}
		// ahora le asigno la manzana al censista
		censista.asignarManzanas(getManzana(idManzana));
	}

	public int cantCensistasAsignadosAManzanas() {
		return listaCensistas.size();
	}

	public Censista censistaAsignadoaManzana(Manzana manzana) {
		Censista censista = null;
		for (Censista c : listaCensistas) {
			for (Manzana manzanac : c.getManzanasAsignadas()) {
				if (manzanac.getIdentificador() == manzana.getIdentificador()) {
					censista = c;
				}
			}
		}
		censistaNoEsValido(censista);

		return censista;
	}

	private void censistaNoEsValido(Censista censista) {
		if (censista == null) {
			throw new IllegalArgumentException("El censista no es valido");
		}
	}

	private Manzana getManzana(Integer idManzana) {
		Manzana manzana = null;
		for (Manzana m : listaManzanas) {
			if (m.getIdentificador() == idManzana)
				manzana = m;
		}
		if (manzana == null) {
			manzanaNoPerteneceAlRadio();
		}
		return manzana;
	}

	public ArrayList<Integer> idsManzanas() {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> idsManzanas = (ArrayList<Integer>) getVertices().clone();
		return idsManzanas;
	}

	public Set<Integer> manzanasVecinas(int idManzana) {
		if (buscarManzana(idManzana) == null)
			manzanaNoPerteneceAlRadio();

		return vecinosVertice(idManzana);
	}

	public boolean sonManzanasVecinas(Integer idManzana1, Integer idManzana2) {
        
		if (buscarManzana(idManzana1) == null || buscarManzana(idManzana2) == null) {
			manzanaNoPerteneceAlRadio();
		}
		return existeArista(idManzana1, idManzana2);
	}

	private Manzana buscarManzana(Integer idManzana) {
		Manzana manzana = null;
		for(Manzana m: listaManzanas) {
			if(m.getIdentificador() == idManzana)
			manzana = m;
		}
		return manzana;
	}
	
	public String infoManzana(Manzana manzana) {
		if (!listaManzanas.contains(manzana))
			manzanaNoPerteneceAlRadio();
		return manzana.toString();
	}

	private void manzanaNoPerteneceAlRadio() {
		throw new IllegalArgumentException("La manzana no pertenece al radio censal");
	}

	public boolean todasManzanasAsignadas() {
		int cantManzanas = 0;
		for (Censista censista : listaCensistas) {
			cantManzanas = cantManzanas + censista.getCantManzanasAsignadas();
		}
		return cantManzanas == listaManzanas.size();
	}

	public ArrayList<Manzana> getManzanas() throws CloneNotSupportedException {
		ArrayList<Manzana> copiaManzanas = new ArrayList<Manzana>();
		
		for(Manzana m: listaManzanas) {
			copiaManzanas.add(m.clone());
		}
		return copiaManzanas;
	}

	public List<Censista> getCensistas() {
		return listaCensistas;
	}

}
