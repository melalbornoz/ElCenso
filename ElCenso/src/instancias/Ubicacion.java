package instancias;

public class Ubicacion implements Cloneable{
	private double latitud;
	private double longitud;

	public Ubicacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	
	@Override
	public Ubicacion clone() throws CloneNotSupportedException{
		return (Ubicacion) super.clone();
	}

	@Override
	public String toString() {
		return "latitud:" + latitud + "\n                  longitud:" + longitud;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ubicacion other = (Ubicacion) obj;
		if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
			return false;
		if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
			return false;
		return true;
	}

}
