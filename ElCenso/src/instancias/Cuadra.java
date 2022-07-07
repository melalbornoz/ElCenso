package instancias;

public class Cuadra {
	private String nombre;

	public Cuadra(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCuadra() {
		return nombre;
	}

	@Override
	public String toString() {
		return " Cuadra : " + nombre + "\n             ";
	}
	
	@Override
	public Cuadra clone() throws CloneNotSupportedException{
		return (Cuadra) super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuadra other = (Cuadra) obj;
		if ( nombre == null&& other.nombre != null || !nombre.equals(other.nombre))
			return false;
		return true;
	}
}
