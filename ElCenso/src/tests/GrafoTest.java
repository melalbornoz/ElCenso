package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grafo.Arista;
import grafo.Grafo;


public class GrafoTest {
	Grafo g;

	@Before
	public void setUp() throws Exception {
		g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(2, 3);
		g.agregarArista(3, 0);
	}

	@Test
	public void agregarAristaTest() {
		Grafo g = new Grafo(5);
		
		g.agregarArista(0, 2);
		assertTrue(g.existeArista(0, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaVerticeIgual() {
		Grafo g = new Grafo(5);
		
		g.agregarArista(0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaExistenteTest() {
		Grafo grafo = new Grafo(4);
		
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaExistenteOpuestaTest() {
		Grafo grafo = new Grafo(4);
		
		grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 1);
	}

	@Test
	public void agregarAristaPesoNegativo() {
		Grafo grafo = new Grafo(4);
		
		grafo.agregarArista(1, 2);
		assertTrue(grafo.existeArista(1, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoSuperior() {
		Grafo g = new Grafo(5);
		
		g.agregarArista(5, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaFueraDeRangoInferior() {
		Grafo g = new Grafo(5);
		
		g.agregarArista(-1, 0);
	}

	@Test
	public void eliminarAristaTest() {
		Grafo g = new Grafo(5);
		
		g.agregarArista(2, 1);
		g.eliminarArista(2, 1);
		assertFalse(g.existeArista(2, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarAristaInexistenteTest() {
		Grafo grafo = new Grafo(4);
		
		grafo.eliminarArista(2, 1);
		;
	}

	@Test
	public void devolverAristaMinima() {
		Arista aristaMinima = new Arista(0, 1);
		Arista primeraArista = g.getAristas().get(0);

		assertEquals(aristaMinima, primeraArista);
	}

	@Test
	public void cantVerticesTest() {
		assertEquals(5, g.getCantVertices());
	}

	@Test
	public void cantVecinosDeTest() {
		assertEquals(2, g.vecinosVertice(3).size());
		assertEquals(0, g.vecinosVertice(4).size());
	}

	@Test
	public void agregarVecinoSimetriaTest() {
		Grafo g = new Grafo(5);
		
		g.agregarArista(2, 1);
		assertTrue(g.existeArista(1, 2));
		assertTrue(g.existeArista(2, 1));
	}

}
