package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import instancias.Censista;
import instancias.Censo;
import instancias.Manzana;

public class CensoTest {

	private Censo censo;
	
	@Before
	public void setUp() {
		censo = new Censo(DatosCenso.getListaCensistas(), DatosCenso.getListaManzanas() );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void listaManzanasVacias() {
		ArrayList<Manzana> listaManzana = new ArrayList<Manzana>();
		
		@SuppressWarnings("unused")
		Censo censoPrueba = new Censo(DatosCenso.getListaCensistas(), listaManzana);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void listaCensistasVacias() {
		ArrayList<Censista> listaCensista = new ArrayList<Censista>();
		
		@SuppressWarnings("unused")
		Censo censoPrueba = new Censo(listaCensista, DatosCenso.getListaManzanas());	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void indiceCensistaNoValido() {
	    @SuppressWarnings("unused")
		Censista censista = censo.getCensistaIndice(9);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void censistaNoValido() {
	    Censista censista = new Censista (444, "X");
	    censo.asignarCensistaAlRadio(0, censista);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearRedCensistasRepetidos() {
		ArrayList<Censista> censistas = new ArrayList<Censista>();
		censistas.add(DatosCenso.getListaCensistas().get(0));
		censistas.add(DatosCenso.getListaCensistas().get(0));
		
	    @SuppressWarnings("unused")
		Censo radio = new Censo(censistas, DatosCenso.getListaManzanas());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearRedManzanasRepetidas() {
		ArrayList<Manzana> manzanas = new ArrayList<Manzana>();
		manzanas.add(DatosCenso.getListaManzanas().get(0));
		manzanas.add(DatosCenso.getListaManzanas().get(0));
		
	    @SuppressWarnings("unused")
		Censo radio = new Censo(DatosCenso.getListaCensistas(),manzanas);
	}
	
	@Test
	public void censistaSinManzana() {
		Censista censista = DatosCenso.getListaCensistas().get(0);
		
		assertTrue(censo.censistaSinManzanasAsignadas(censista));
	}
	
	@Test
	public void censistaConTresManzanas() {
		Censista censista = DatosCenso.getListaCensistas().get(0);
		asignarCensistaAlRadio(censista);
		
		assertTrue(censo.censistaTieneTresManzanas(censista));
	}
	
	@Test
	public void censistaConMenosDeTresManzanas() {
		Censista censista = DatosCenso.getListaCensistas().get(0);	
		assertFalse(censo.censistaTieneTresManzanas(censista));;
	}
	
	@Test
	public void idManzanasCensista() {
		Censista censista = DatosCenso.getListaCensistas().get(0);
		asignarCensistaAlRadio(censista);	
		
		LinkedList<Integer> idManzanas = new LinkedList<Integer>();
	    idManzanas.add(0);
	    idManzanas.add(1);
	    idManzanas.add(2);
		
		assertEquals(idManzanas.size(), censo.idManzanasCensista(censista).size());
		
		boolean todosIds = true;
		for(Integer idmanzana: censo.idManzanasCensista(censista)) {
			todosIds = todosIds && idManzanas.contains(idmanzana);
		}
		assertTrue(todosIds);
	}
	
	@Test
	public void censistasNoAsignados() {
		Censista censista = DatosCenso.getListaCensistas().get(0);	
		asignarCensistaAlRadio(censista);
		
		assertEquals(noAsignados().size(), censo.censistasNoAsignadosAlRadio().size());
		
		boolean todosSinAsignar = true;
		for(Censista c: censo.censistasNoAsignadosAlRadio()) {
				todosSinAsignar = todosSinAsignar && noAsignados().contains(c);		   
		   }
		 
		assertTrue(todosSinAsignar);	
	}
	
	private List<Censista> noAsignados(){
		List<Censista> noAsignados = new ArrayList<Censista>();
		Censista asignado = DatosCenso.getListaCensistas().get(0);
		for(Censista c : DatosCenso.getListaCensistas()) {
			if(!c.equals(asignado))
				noAsignados.add(c);
		}
		return noAsignados;
	}
	
	private void asignarCensistaAlRadio(Censista censista) {
		censo.asignarCensistaAlRadio(0, censista);
		censo.asignarCensistaAlRadio(1, censista);
		censo.asignarCensistaAlRadio(2, censista);
	}
	
	
	
	
	

}
