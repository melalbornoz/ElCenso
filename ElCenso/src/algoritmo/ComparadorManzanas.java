package algoritmo;

import java.util.Comparator;

import instancias.RadioCensal;

public class ComparadorManzanas {

	private RadioCensal radioCensal;

	public ComparadorManzanas(RadioCensal radioCensal) {
		this.radioCensal = radioCensal;
	}

	public Comparator<Integer> comparadorManzanasConMasVecinos() {
		Comparator<Integer> ordenar = new Comparator<Integer>() {
			@Override
			public int compare(Integer uno, Integer otro) {

				return (radioCensal.getCantVecinosVertice(uno) < radioCensal.getCantVecinosVertice(otro)) ? 1
						: (radioCensal.getCantVecinosVertice(uno) > radioCensal.getCantVecinosVertice(otro)) ? -1 : 0;
			}
		};
		return ordenar;
	}

	public Comparator<Integer> comparadorManzanasConMenosVecinos() {
		Comparator<Integer> ordenar = new Comparator<Integer>() {
			@Override
			public int compare(Integer uno, Integer otro) {

				return (radioCensal.getCantVecinosVertice(uno) > radioCensal.getCantVecinosVertice(otro)) ? 1
						: (radioCensal.getCantVecinosVertice(uno) < radioCensal.getCantVecinosVertice(otro)) ? -1 : 0;
			}
		};
		return ordenar;
	}

}