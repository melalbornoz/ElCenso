package tests;

// Se utiliza para testear
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import instancias.Censista;
import instancias.Cuadra;
import instancias.Manzana;
import instancias.Ubicacion;

public class DatosCenso {

	/*----------------------/ MANZANAS CENSO \----------------------
	*
	*          ("M0-N")          ("M1-N")           ("M2-N")
	*
	*           ------             ------             ------
	* ("M0-O") |  M0  | ("M0-M1") |  M1  | ("M1-M2") |  M2  | ("M2-E")
	*           ------             ------             ------
	*
	*         ("M0-M3")          ("M1-M4")          ("M2-M5")
	*
	*           ------             ------             ------
	* ("M3-O") |  M3  | ("M3-M4") |  M4  | ("M4-M5") |  M5  | ("M5-E")
	*           ------             ------             ------
	*
	*         ("M3-M6")          ("M4-M7")          ("M5-M8")
	*
	*           ------             ------             ------
	* ("M6-O") |  M6  | ("M6-M7") |  M7  | ("M7-M8") |  M8  | ("M8-E")
	*           ------             ------             ------
	*
	*          ("M6-S")           ("M7-S")           ("M8-S")
	*/

	public DatosCenso() {
	}

	public static ArrayList<Censista> getListaCensistas() {
		ArrayList<Censista> listaCensistas = new ArrayList<Censista>();
		listaCensistas.add(new Censista(444444440, "A"));
		listaCensistas.add(new Censista(444444441, "B"));
		listaCensistas.add(new Censista(444444442, "C"));
		listaCensistas.add(new Censista(444444443, "D"));
		listaCensistas.add(new Censista(444444444, "E"));
		listaCensistas.add(new Censista(444444445, "F"));
		listaCensistas.add(new Censista(444444446, "G"));
		listaCensistas.add(new Censista(444444447, "H"));
		listaCensistas.add(new Censista(444444448, "I"));

		return listaCensistas;
	}

	public static ArrayList<Manzana> getListaManzanas() {
		ArrayList<Manzana> listaManzanas = new ArrayList<Manzana>();

		for (int i = 0; i < 9; i++) {
			listaManzanas.add(new Manzana(getUbicacionesManzanas().get(i), getlistaCuadrasCompartidas().get(i), i));
		}

		return listaManzanas;
	}

	private static ArrayList<Ubicacion> getUbicacionesManzanas() {
		ArrayList<Ubicacion> ubicacionesManzanas = new ArrayList<Ubicacion>();
		for (int i = 0; i < 10; i++) {
			ubicacionesManzanas.add(new Ubicacion(i, i));
		}

		return ubicacionesManzanas;
	}

	private static void agregarCuadra(Map<Integer, ArrayList<Cuadra>> mapa, Cuadra a, Cuadra b, Cuadra c, Cuadra d,
			int m) {
		mapa.put(m, new ArrayList<Cuadra>());

		mapa.get(m).add(a);
		mapa.get(m).add(b);
		mapa.get(m).add(c);
		mapa.get(m).add(d);
	}

	private static Map<Integer, ArrayList<Cuadra>> getlistaCuadrasCompartidas() {
		Map<Integer, ArrayList<Cuadra>> cuadrasDeManzanas = new HashMap<Integer, ArrayList<Cuadra>>();

		int m = 0;
		agregarCuadra(cuadrasDeManzanas, new Cuadra("M0-O"), new Cuadra("M0-N"), new Cuadra("M0-M1"),
				new Cuadra("M0-M3"), m);
		m++;

		agregarCuadra(cuadrasDeManzanas, new Cuadra("M0-M1"), new Cuadra("M1-N"), new Cuadra("M1-M2"),
				new Cuadra("M1-M4"), m);
		m++;

		agregarCuadra(cuadrasDeManzanas, new Cuadra("M1-M2"), new Cuadra("M2-N"), new Cuadra("M2-E"),
				new Cuadra("M2-M5"), m);
		m++;
		agregarCuadra(cuadrasDeManzanas, new Cuadra("M3-O"), new Cuadra("M0-M3"), new Cuadra("M3-M4"),
				new Cuadra("M3-M6"), m);
		m++;
		agregarCuadra(cuadrasDeManzanas, new Cuadra("M3-M4"), new Cuadra("M1-M4"), new Cuadra("M4-M5"),
				new Cuadra("M4-M7"), m);
		m++;
		agregarCuadra(cuadrasDeManzanas, new Cuadra("M4-M5"), new Cuadra("M2-M5"), new Cuadra("M5-E"),
				new Cuadra("M5-M8"), m);
		m++;
		agregarCuadra(cuadrasDeManzanas, new Cuadra("M6-O"), new Cuadra("M3-M6"), new Cuadra("M6-M7"),
				new Cuadra("M6-S"), m);
		m++;
		agregarCuadra(cuadrasDeManzanas, new Cuadra("M6-M7"), new Cuadra("M4-M7"), new Cuadra("M7-M8"),
				new Cuadra("M7-S"), m);
		m++;
		agregarCuadra(cuadrasDeManzanas, new Cuadra("M7-M8"), new Cuadra("M5-M8"), new Cuadra("M8-E"),
				new Cuadra("M8-S"), m);

		return cuadrasDeManzanas;
	}
}
