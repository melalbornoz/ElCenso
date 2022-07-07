package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.Test;

import algoritmo.ComparadorManzanas;
import algoritmo.Solucion;
import algoritmo.SolverFuerzaBruta;
import algoritmo.SolverGoloso;
import instancias.Censista;
import instancias.Censo;
import instancias.Cuadra;
import instancias.Manzana;
import instancias.Ubicacion;

public class SolverGolosoTest {

	@Test
	public void manzanasConMenosManzanasCompartidas() throws CloneNotSupportedException {
		Solucion solucion = Auxiliares.solMenosManzanasCompartidas(Auxiliares.instanciaCensoValida());
		assertEquals(4, solucion.cantidadCensistasAsignados());
	}

	@Test
	public void manzanasConMasManzanasCompartidas() throws CloneNotSupportedException {
		Solucion solucion = Auxiliares.solMasManzanasCompartidas(Auxiliares.instanciaCensoValida());
		assertEquals(7, solucion.cantidadCensistasAsignados());
	}

	@Test
	public void censistasAsignadosEsperadosMenosManzanas() throws CloneNotSupportedException {
		LinkedList<Censista> censistasEsperados = new LinkedList<Censista>();
		censistasEsperados.add(new Censista(444444440, "A"));
		censistasEsperados.add(new Censista(444444441, "B"));
		censistasEsperados.add(new Censista(444444442, "C"));
		censistasEsperados.add(new Censista(444444443, "D"));

		Solucion solucion = Auxiliares.solMenosManzanasCompartidas(Auxiliares.instanciaCensoValida());

		boolean todosIguales = true;
		for (Censista censista : solucion.censistasAsignadosAradio()) {
			todosIguales = todosIguales && censistasEsperados.contains(censista);
		}

		assertTrue(todosIguales);
	}

	@Test
	public void censistasAsignadosEsperadosMasManzanas() throws CloneNotSupportedException {
		LinkedList<Censista> censistasEsperados = new LinkedList<Censista>();
		censistasEsperados.add(new Censista(444444440, "A"));
		censistasEsperados.add(new Censista(444444441, "B"));
		censistasEsperados.add(new Censista(444444442, "C"));
		censistasEsperados.add(new Censista(444444443, "D"));
		censistasEsperados.add(new Censista(444444444, "E"));
		censistasEsperados.add(new Censista(444444445, "F"));
		censistasEsperados.add(new Censista(444444446, "G"));

		Solucion solucion = Auxiliares.solMasManzanasCompartidas(Auxiliares.instanciaCensoValida());

		assertEquals(censistasEsperados.size(), solucion.cantidadCensistasAsignados());

		boolean todosIguales = true;
		for (Censista censista : solucion.censistasAsignadosAradio()) {
			todosIguales = todosIguales && censistasEsperados.contains(censista);
		}
		assertTrue(todosIguales);
	}

	@Test(expected = IllegalArgumentException.class)
	public void censistasInsuficientes() throws CloneNotSupportedException {
		ComparadorManzanas orden = new ComparadorManzanas(Auxiliares.instanciaCensoNoValida().getRadioCensal());
		SolverGoloso solver = new SolverGoloso(Auxiliares.instanciaCensoNoValida(),
				orden.comparadorManzanasConMenosVecinos());
		@SuppressWarnings("unused")
		Solucion solucion = solver.resolver();
	}

	@Test(expected = IllegalArgumentException.class)
	public void radioCensalVacio() throws CloneNotSupportedException {
		ComparadorManzanas orden = new ComparadorManzanas(Auxiliares.instanciaCensoValida().getRadioCensal());
		@SuppressWarnings("unused")
		SolverGoloso algoritmo = new SolverGoloso(new Censo(new ArrayList<Censista>(), new ArrayList<Manzana>()),
				orden.comparadorManzanasConMasVecinos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void instanciaNula() throws CloneNotSupportedException {
		ComparadorManzanas orden = new ComparadorManzanas(Auxiliares.instanciaCensoValida().getRadioCensal());
		@SuppressWarnings("unused")
		SolverGoloso algoritmo = new SolverGoloso(null, orden.comparadorManzanasConMasVecinos());
	}

	@Test
	public void asignarManzanasContiguas() throws CloneNotSupportedException {
		Solucion solucion = Auxiliares.solMenosManzanasCompartidas(Auxiliares.instanciaCensoValida());

		boolean censistasManzanasContiguas = true;
		boolean algunaManzana = false;
		boolean todasVecinas = true;

		for (Censista censista : solucion.censistasAsignadosAradio()) {
			algunaManzana = false;
			for (Manzana manzana : censista.getManzanasAsignadas()) {
				todasVecinas = true;
				for (Manzana manzana2 : censista.getManzanasAsignadas()) {
					if (!manzana2.equals(manzana)) {
						todasVecinas = todasVecinas && solucion.getCenso()
								.manzanasDelRadioContiguas(manzana.getIdentificador(), manzana2.getIdentificador());
					}
				}
				algunaManzana = algunaManzana || todasVecinas;
			}
			censistasManzanasContiguas = censistasManzanasContiguas && algunaManzana;
		}
		assertTrue(censistasManzanasContiguas);
	}
}
