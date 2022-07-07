package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import instancias.Manzana;

public class CiudadJSON {
	private Gson gson;
	private String nombreArchivo;
	private ArrayList<Manzana> manzanas;

	public CiudadJSON(String archivo) throws IOException {
		gson = new Gson();
		nombreArchivo = archivo;
		manzanas=this.leerJSON(this.nombreArchivo);
	}

	private ArrayList<Manzana> leerJSON(String nombreArchivo)throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		Type collectionType=new TypeToken<ArrayList<Manzana>>(){}.getType();
		ArrayList<Manzana> manzanas=gson.fromJson(br,collectionType);
		if(manzanas==null){
				manzanas=new ArrayList<Manzana>();
		}
		return manzanas;
	}
	
	public ArrayList<Manzana> getMazanas() {
		return this.manzanas;
	}

	public int tamanio() {
		return manzanas.size();
	}
}
