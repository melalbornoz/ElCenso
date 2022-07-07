package grafo;

public class Arista {

	private int origen;
	private int destino;

	public Arista(int origen, int destino) {
		this.origen = origen;
		this.destino = destino;
	}

	public int getOrigen() {
		return this.origen;
	}

	public int getDestino() {
		return this.destino;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		return (origen == other.origen && destino == other.destino)
				|| (origen == other.destino && destino == other.origen);
	}
	

}
