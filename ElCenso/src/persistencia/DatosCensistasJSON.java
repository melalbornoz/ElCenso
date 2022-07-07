package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import instancias.Censista;
import instancias.Manzana;

public class DatosCensistasJSON {
	private Gson gson;
	private String nombreArchivo;
	private List<Censista> datosCensistas;

	public DatosCensistasJSON(String archivo) throws IOException {
		nombreArchivo = archivo;
		gson = new Gson();
		datosCensistas = this.leerJSON(this.nombreArchivo);
	}

	private List<Censista> leerJSON(String nombreArchivo) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		Type type = new TypeToken<List<Censista>>() {
		}.getType();
		ArrayList<Censista> datosCensistas = gson.fromJson(br, type);
		if (datosCensistas == null) {
			datosCensistas = new ArrayList<Censista>();
		}
		for (Censista censista : datosCensistas) {
			censista.setListaManzanas(new ArrayList<Manzana>());
		}
		return datosCensistas;
	}

	public List<Censista> getDatosCensistas() {
		return datosCensistas;
	}

}