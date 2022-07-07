package persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import instancias.Censista;
import instancias.Manzana;

public class CensoJSON {

	private CiudadJSON laCiudad;
	private DatosCensistasJSON losDatosCensistas;

	public CensoJSON() {
	}

	public void leerCiudadJSON(String archivoManzanas) throws IOException {
		laCiudad = new CiudadJSON(archivoManzanas);
	}

	public void leerDatosCensitasJSON(String archivoDatosCensistas) throws IOException {
		losDatosCensistas = new DatosCensistasJSON(archivoDatosCensistas);
	}

	public ArrayList<Manzana> cargarManzanas() {
		return laCiudad.getMazanas();
	}

	public int cantidadManzanas() {
		return laCiudad.tamanio();
	}

	public List<Censista> cargarDatosCensistas() {
		return losDatosCensistas.getDatosCensistas();
	}

}
