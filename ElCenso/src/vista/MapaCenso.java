package vista;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import grafo.Arista;
import instancias.Manzana;
import instancias.Ubicacion;

public class MapaCenso extends JMapViewer{
	private static final long serialVersionUID = 4915280030741762589L;
	private List<Manzana> manzanas;

	public MapaCenso() {
	}

	public void guardarManzanas(List<Manzana> manzanasMapa) {
		manzanas = manzanasMapa;
	}

	public void cargarUbicacionesMapa() {
		if (manzanas != null) {
			Color color = Color.MAGENTA;
			for (int i = 0; i < manzanas.size(); i++) {
				MapMarkerDot marker = new MapMarkerDot(manzanas.get(i).getUbicacionManzana().getLatitud(),
						manzanas.get(i).getUbicacionManzana().getLongitud());
				marker.getStyle().setBackColor(color);
				addMapMarker(marker);
			}
		}
	}

	public void cargarCuadrasCompartidas(List<Arista> cuadras) throws Exception {
		for (Arista cuadra : cuadras) {
			Manzana manzanaOrigen = manzanas.get(cuadra.getOrigen());
			Manzana manzanaDestino = manzanas.get(cuadra.getDestino());
			agregarCuadras(manzanaOrigen.getUbicacionManzana(), manzanaDestino.getUbicacionManzana());
		}
	}

	private void agregarCuadras(Ubicacion ubicacion1, Ubicacion ubicacion2) {
		ArrayList<Coordinate> rutaMapa = new ArrayList<>();
		rutaMapa.add(new Coordinate(ubicacion1.getLatitud(), ubicacion1.getLongitud()));
		rutaMapa.add(new Coordinate(ubicacion2.getLatitud(), ubicacion2.getLongitud()));
		rutaMapa.add(new Coordinate(ubicacion1.getLatitud(), ubicacion1.getLongitud()));

		MapPolygon cuadra = new MapPolygonImpl(rutaMapa);

		addMapPolygon(cuadra);
	}

}