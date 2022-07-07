package tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import algoritmo.ComparadorManzanas;
import algoritmo.Solucion;
import algoritmo.SolverGoloso;
import instancias.Censista;
import instancias.Censo;

public class Auxiliares {

	public static Censo instanciaCensoNoValida() {
		ArrayList<Censista> censistas = new ArrayList<Censista>();
		censistas.add(new Censista(444444440, "A"));
		censistas.add(new Censista(444444442, "B"));
		Censo censoNoValido = new Censo(censistas, DatosCenso.getListaManzanas());
		return censoNoValido;
	}

	public static Censo instanciaCensoValida() {
		return new Censo(DatosCenso.getListaCensistas(), DatosCenso.getListaManzanas());
	}

	public static Solucion solMenosManzanasCompartidas(Censo instancia) throws CloneNotSupportedException {
		ComparadorManzanas orden = new ComparadorManzanas(instancia.getRadioCensal());
		SolverGoloso solver = new SolverGoloso(instancia, orden.comparadorManzanasConMenosVecinos());
		Solucion solucion = solver.resolver();

		return solucion;
	}

	public static Solucion solMasManzanasCompartidas(Censo instancia) throws CloneNotSupportedException {
		ComparadorManzanas orden = new ComparadorManzanas(instancia.getRadioCensal());
		SolverGoloso solver = new SolverGoloso(instancia, orden.comparadorManzanasConMasVecinos());
		Solucion solucion = solver.resolver();

		return solucion;
	}
}
