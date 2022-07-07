package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import algoritmo.ComparadorManzanas;
import algoritmo.Solucion;
import algoritmo.SolverFuerzaBruta;
import algoritmo.SolverGoloso;
import grafo.Arista;
import instancias.Censista;
import instancias.Censo;
import instancias.Manzana;
import instancias.RadioCensal;
import persistencia.CensoJSON;

public class Controlador {
	private static CensoJSON datosCenso;
	private static Censo censo;

	public static void leerLosJSON() {
		try {
			datosCenso = new CensoJSON();
			datosCenso.leerCiudadJSON("src/persistencia/ManzanasC1Pretty.JSON");
			datosCenso.leerDatosCensitasJSON("src/persistencia/DatosCensistasPretty.JSON");
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public static List<Censista> datosCensistasDisponibles() {
		return datosCenso.cargarDatosCensistas();
	}

	public static ArrayList<Manzana> manzanasDisponibles() {
		return datosCenso.cargarManzanas();
	}

	public static ArrayList<Manzana> manzanasDisponiblesPorCantidad(int cantidad) {
		ArrayList<Manzana> manzanasRet = new ArrayList<Manzana>();
		int i = 0;
		for (Manzana manzana : datosCenso.cargarManzanas()) {
			if (i < cantidad)
				manzanasRet.add(manzana);
			i++;
		}
		return manzanasRet;
	}

	public static int getCantManzanasDisponibles() {
		return datosCenso.cantidadManzanas();
	}

	public static boolean censoCreado() {
		if (censo == null)
			return false;
		else
			return true;
	}

	public static void crearCenso(ArrayList<Censista> listaCensistas, ArrayList<Manzana> listaManzanas) {
		censo = new Censo(listaCensistas, listaManzanas);
	}

	public static List<Manzana> getManzanasRadioCensal() throws CloneNotSupportedException {
		return censo.getRadioCensal().getManzanas();
	}

	public static List<Arista> getCuadras() {
		return censo.getRadioCensal().getAristas();
	}

	public static List<Censista> getCensistasCenso() {
		return censo.getRadioCensal().getCensistas();
	}

	public static Censista getCensista(Integer posicion) {
		return datosCensistasDisponibles().get(posicion);
	}

	public static Integer cantCensistas() {
		return datosCensistasDisponibles().size();
	}

	public static Solucion asignarConCriterioMasManzanas() throws CloneNotSupportedException {

		Censo instancia = new Censo(censo.getcensistasCenso(), censo.getManzanasRadio());
		ComparadorManzanas comparador = new ComparadorManzanas(instancia.getRadioCensal());

		SolverGoloso solver = new SolverGoloso(instancia, comparador.comparadorManzanasConMasVecinos());
		Solucion solucion = solver.resolver();

		return solucion;
	}

	public static Solucion asignarConCriterioMenosManzanas() throws CloneNotSupportedException {
		Censo instancia = new Censo(censo.getcensistasCenso(), censo.getManzanasRadio());
		ComparadorManzanas comparador = new ComparadorManzanas(censo.getRadioCensal());

		SolverGoloso solver = new SolverGoloso(instancia, comparador.comparadorManzanasConMenosVecinos());
		Solucion solucion = solver.resolver();
		return solucion;
	}

	public static Solucion asignarCensistasManzanasFB() throws CloneNotSupportedException {
		Censo instancia = new Censo(censo.getcensistasCenso(), censo.getManzanasRadio());
		SolverFuerzaBruta bruta = new SolverFuerzaBruta();

		Solucion solucion = bruta.resolver(instancia);
		return solucion;

	}

	public static String consultarCensistasAsignado(Solucion solucion) {
		StringBuilder censistasAsignados = new StringBuilder();
		for (Censista censista : solucion.getCenso().getCensistasRadioCensal()) {
			censistasAsignados.append(" " + censista.toString() + "\n");
		}
		return censistasAsignados.toString();
	}

	public static String consultarCensistasNoAsignados(Solucion solucion) {
		StringBuilder censistasNoAsignados = new StringBuilder();
		for (Censista censista : solucion.getCenso().censistasNoAsignadosAlRadio()) {
			censistasNoAsignados.append(" " + censista.toString() + "\n");
		}
		return censistasNoAsignados.toString();
	}

}
