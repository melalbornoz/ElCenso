package instancias;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Manzana implements Cloneable {
	private int identificador; // a que vertice representa la manzana.
	private List<Cuadra> cuadras;
	private Ubicacion ubicacion;

	public Manzana(Ubicacion ubicacion, ArrayList<Cuadra> cuadras, int identificador) {
		
		if ( cuadras.isEmpty() || identificador < 0 || cuadrasRepetidas(cuadras)) 
			throw new IllegalArgumentException("Los datos no son validos");
		
		this.identificador = identificador;
		this.cuadras = cuadras;
		this.ubicacion = ubicacion;
	}
	
	private boolean cuadrasRepetidas(ArrayList<Cuadra> cuadras) {
		boolean cuadrasRepetidas = false;

		for(int c = 0; c < cuadras.size(); c++) {
		  if(c != cuadras.size()) {
			for(int c2 = c+1; c2 < cuadras.size(); c2++) 
				cuadrasRepetidas = cuadrasRepetidas || cuadras.get(c).equals(cuadras.get(c2));			
		  }
		}
        return cuadrasRepetidas;
	}

	public List<Cuadra> getCuadrasManzana() {
		return cuadras;
	}

	public int getIdentificador() {
		return identificador;
	}

	public Ubicacion getUbicacionManzana() {
		return ubicacion;
	}

	public boolean algunaCuadraEsIgual(List<Cuadra> cuadrasV) {
		boolean algunaIgual = false;
		for(Cuadra cuadrav: cuadrasV) {
			for(Cuadra cuadra: cuadras) 
			  algunaIgual = algunaIgual || cuadrav.getNombreCuadra().equals(cuadra.getNombreCuadra());
		}
		return algunaIgual;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setCuadras(List<Cuadra> cuadras) {
		this.cuadras = cuadras;
	}
	
	@Override
	public Manzana clone() throws CloneNotSupportedException{
	    Manzana copia = (Manzana)super.clone();
	    
	    @SuppressWarnings("unchecked")
		List<Cuadra> copiaCuadras = (List<Cuadra>) ((ArrayList<Cuadra>) cuadras).clone();
		Ubicacion ubicacion = this.ubicacion.clone();
		
		copia.setCuadras(copiaCuadras);
		copia.setUbicacion(ubicacion);
	    return copia;
	}
	 
	@Override
	public String toString() {
		StringBuilder infoManzana = new StringBuilder();

		infoManzana.append("\n------------------------------------------------------\n").
		            append("Identificacion Manzana: ").
		            append(identificador).
		            append("\nCuadras:").
		            append(cuadras).
				    append("\nUbicacion :").
				    append(ubicacion).
				    append("\n------------------------------------------------------\n");

		return infoManzana.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manzana other = (Manzana) obj;
		if (cuadras == null && other.cuadras != null || !cuadras.equals(other.cuadras))
			return false;
		if (identificador != other.identificador)
			return false;
		if (ubicacion == null && other.ubicacion != null || !ubicacion.equals(other.ubicacion))
			return false;
		return true;
	}

}
