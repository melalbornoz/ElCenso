package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import instancias.Censista;
import instancias.Manzana;
import instancias.RadioCensal;

public class RadioCensalTest {
	private RadioCensal radioCensal;

	@Before
	public void setUp() {
		radioCensal = new RadioCensal(DatosCenso.getListaManzanas());
	}

	@Test(expected = IllegalArgumentException.class)
	public void manzanaNoPerteneseAlRadio() {
		radioCensal.sonManzanasVecinas(9, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void asignarCensistaManzanaNoValida() {
		radioCensal.asignarCensista(9, censistas().get(0));
	}

	@Test
	public void asignarCensistaDosVeces() {

		radioCensal.asignarCensista(0, censistas().get(0));
		radioCensal.asignarCensista(1, censistas().get(0));

		assertFalse(radioCensal.getCensistas().size() == 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void censistaNoAsignado() {
		Manzana manzana = DatosCenso.getListaManzanas().get(0);
		@SuppressWarnings("unused")
		Censista censista = radioCensal.censistaAsignadoaManzana(manzana);
	}

	@Test
	public void censistaAsignado() {
		Manzana manzana = DatosCenso.getListaManzanas().get(0);
		radioCensal.asignarCensista(manzana.getIdentificador(), censistas().get(0));

		Censista censista = radioCensal.censistaAsignadoaManzana(manzana);

		assertEquals(censistas().get(0), censista);
	}

	@Test
	public void todasManzanasAsignadasACensistas() {
		asignarLosCensistas(censistas());
		assertTrue(radioCensal.todasManzanasAsignadas());
	}

	@Test
	public void todosCensistasAsignados() {
		asignarLosCensistas(censistas());
		assertEquals(3, radioCensal.cantCensistasAsignadosAManzanas());
	}

	private void asignarLosCensistas(ArrayList<Censista> censistas) {
		radioCensal.asignarCensista(0, censistas.get(0));
		radioCensal.asignarCensista(1, censistas.get(0));
		radioCensal.asignarCensista(2, censistas.get(0));
		radioCensal.asignarCensista(3, censistas.get(1));
		radioCensal.asignarCensista(4, censistas.get(1));
		radioCensal.asignarCensista(5, censistas.get(1));
		radioCensal.asignarCensista(6, censistas.get(2));
		radioCensal.asignarCensista(7, censistas.get(2));
		radioCensal.asignarCensista(8, censistas.get(2));
	}

	private ArrayList<Censista> censistas() {
		ArrayList<Censista> censistas = new ArrayList<Censista>();

		censistas.add(new Censista(1, "H"));
		censistas.add(new Censista(2, "H"));
		censistas.add(new Censista(3, "H"));

		return censistas;
	}

}
