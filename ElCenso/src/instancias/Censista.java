package instancias;

import java.util.ArrayList;
import java.util.List;

public class Censista implements Cloneable {
	private int DNI;
	private String nombre;
	private String rutaImagen;

	private List<Manzana> manzanasAsignadas;

	public Censista(int DNI, String nombre) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.manzanasAsignadas = new ArrayList<Manzana>();
	}

	public void asignarManzanas(Manzana manzana) {
		manzanasAsignadas.add(manzana);
	}

	public boolean manzanaAsignada(Manzana manzana) {
		return manzanasAsignadas.contains(manzana);
	}

	public int getDNI() {
		return DNI;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setListaManzanas(List<Manzana> manzanasAsignadas) {
		this.manzanasAsignadas = manzanasAsignadas;
	}

	public List<Manzana> getManzanasAsignadas() {
		return manzanasAsignadas;
	}

	public int getCantManzanasAsignadas() {
		return manzanasAsignadas.size();
	}

	@Override
	public Censista clone() throws CloneNotSupportedException {
		Censista copia = (Censista) super.clone();
		@SuppressWarnings("unchecked")
		List<Manzana> copiaManzanas = (List<Manzana>) ((ArrayList<Manzana>) manzanasAsignadas).clone();

		copia.setListaManzanas(copiaManzanas);

		return copia;
	}

	@Override
	public String toString() {
		StringBuilder datosCensista = new StringBuilder();

		datosCensista.append("************************************\n")
		        .append("DNI: ").append(DNI)
		        .append("\nNombre: ").append(nombre)
				.append("\nManzanas asignadas: ").append(manzanasAsignadas)
				.append("\n************************************\n");
		
		return datosCensista.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Censista other = (Censista) obj;
		if (DNI != other.DNI)
			return false;
		if (nombre == null && other.nombre != null || !nombre.equals(other.nombre))
			return false;
		return true;
	}

}
