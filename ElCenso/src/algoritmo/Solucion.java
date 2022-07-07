package algoritmo;

import java.util.List;

import instancias.Censista;
import instancias.Censo;

public class Solucion {

	private Censo censo;
	
	public Solucion(Censo censo) {
		this.censo = censo;
	}
	
	public int cantidadCensistasAsignados() {
		return censo.cantCensistasAsignadosARadio();
	}
	
    public Censo getCenso() {
		return censo;
	}
    
    public List<Censista> censistasAsignadosAradio() {
    	return censo.getCensistasRadioCensal();
    }
}
