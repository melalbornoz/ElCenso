package tests;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;

import instancias.Cuadra;
import instancias.Manzana;
import instancias.Ubicacion;

public class ManzanasTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void cudrasRepetidas () {
		Ubicacion ubicacion = new Ubicacion (0.0, 0.0);
		
		ArrayList<Cuadra> cuadras = new ArrayList<Cuadra>();
		cuadras.add(new Cuadra("M0-O"));
		cuadras.add(new Cuadra("M0-O"));
		cuadras.add(new Cuadra("M0-E"));
		cuadras.add(new Cuadra("M0-S"));
		
		@SuppressWarnings("unused")
		Manzana manzana = new Manzana (ubicacion, cuadras, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cuadrasVacias() {
	    Ubicacion ubicacion = new Ubicacion (0.0, 0.0);
		
		ArrayList<Cuadra> cuadras = new ArrayList<Cuadra>();
		
		@SuppressWarnings("unused")
		Manzana manzana = new Manzana (ubicacion, cuadras, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void identificadorNegativo() {
	    Ubicacion ubicacion = new Ubicacion (0.0, 0.0);
		
		ArrayList<Cuadra> cuadras = new ArrayList<Cuadra>();
		cuadras.add(new Cuadra("M0-O"));
		cuadras.add(new Cuadra("M0-N"));
		cuadras.add(new Cuadra("M0-E"));
		cuadras.add(new Cuadra("M0-S"));
		
		@SuppressWarnings("unused")
		Manzana manzana = new Manzana (ubicacion, cuadras, -1);
	}
	
	@Test
	public void algunaCuadraIgualACuadraVecina() {
        Ubicacion ubicacion = new Ubicacion (0.0, 0.0);
		
		ArrayList<Cuadra> cuadras = new ArrayList<Cuadra>();
		cuadras.add(new Cuadra("M0-O"));
		cuadras.add(new Cuadra("M0-N"));
		cuadras.add(new Cuadra("M0-E"));
		cuadras.add(new Cuadra("M0-S"));
		
		Manzana manzana = new Manzana (ubicacion, cuadras, 1);
		
		ArrayList<Cuadra> cuadras2 = new ArrayList<Cuadra>();
		cuadras2.add(new Cuadra("M0-O"));
		
		assertTrue(manzana.algunaCuadraEsIgual(cuadras2));
	
	}

}
