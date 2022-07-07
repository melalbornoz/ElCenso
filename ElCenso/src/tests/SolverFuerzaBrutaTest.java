package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import algoritmo.Solucion;
import algoritmo.SolverFuerzaBruta;
import instancias.Censista;
import instancias.Censo;
import instancias.Manzana;

public class SolverFuerzaBrutaTest {

	@Test
	public void cantCensistas() throws CloneNotSupportedException {
		SolverFuerzaBruta algoritmo = new SolverFuerzaBruta();
		Solucion solucion = algoritmo.resolver(Auxiliares.instanciaCensoValida());
		assertEquals(3, solucion.censistasAsignadosAradio().size());
	}

	@Test
	public void censistasAsignadosEsperados() throws CloneNotSupportedException {
		SolverFuerzaBruta algoritmo = new SolverFuerzaBruta();
		Solucion solucion = algoritmo.resolver(Auxiliares.instanciaCensoValida());
		LinkedList<Censista> censistasEsperados = new LinkedList<Censista>();
		censistasEsperados.add(new Censista(444444440, "A"));
		censistasEsperados.add(new Censista(444444441, "B"));
		censistasEsperados.add(new Censista(444444442, "C"));
		assertEquals(censistasEsperados, solucion.censistasAsignadosAradio());
	}

	@Test(expected = IllegalArgumentException.class)
	public void censistasInsuficientes() throws CloneNotSupportedException {
		SolverFuerzaBruta algoritmo = new SolverFuerzaBruta();
		algoritmo.resolver(Auxiliares.instanciaCensoNoValida());
	}

	@Test(expected = IllegalArgumentException.class)
	public void radioCensalVacio() throws CloneNotSupportedException {
		SolverFuerzaBruta algoritmo = new SolverFuerzaBruta();
		algoritmo.resolver(new Censo(new ArrayList<Censista>(), new ArrayList<Manzana>()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void instanciaNula() throws CloneNotSupportedException {
		SolverFuerzaBruta algoritmo = new SolverFuerzaBruta();
		algoritmo.resolver(null);
	}
}
