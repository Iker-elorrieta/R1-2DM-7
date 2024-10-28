package modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import conexion.ConexionDB;

public class Ejercicio implements Serializable {

	private String nombre;
	private String descripcion;
	private ArrayList<Serie> series;
	private String imagenURL;
	private double tiempoDescanso;

	private static final String collectionName = "ejercicios";
	private static final String fieldDescripcion = "descripcion";
	private static final String fieldSeries = "series";
	private static final String fieldImagen = "imagenUrl";
	private static final String fieldTiempo = "tiempoDescanso";

	// CONSTRUCTORES

	public Ejercicio() {

		this.series = new ArrayList<>();
	}

	public Ejercicio(String nombre, String descripcion, String imagenURL, ArrayList<Serie> series,
			double tiempoDescanso) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagenURL = imagenURL;
		this.tiempoDescanso = tiempoDescanso;
		this.series = series;
	}

	// GETTERS Y SETTERS

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Serie> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<Serie> series) {
		this.series = series;
	}

	public String getImagenURL() {
		return imagenURL;
	}

	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}

	public double getTiempoDescanso() {
		return tiempoDescanso;
	}

	public void setTiempoDescanso(double tiempoDescanso) {
		this.tiempoDescanso = tiempoDescanso;
	}

	// Método para agregar un nuevo ejercicio
	public void mIngresarEjercicio(String coleccion, String nombreWorkout) {
		Firestore co = null;
		try {
			co = ConexionDB.conectar();
			DocumentReference referenciaWorkout = co.collection(coleccion).document(nombreWorkout);
			CollectionReference ejerciciosCollection = referenciaWorkout.collection(collectionName);
			System.out.println(collectionName);

			if (!ejerciciosCollection.document(nombre).get().get().exists()) {
				Map<String, Object> nuevoEjercicio = new HashMap<>();
				nuevoEjercicio.put(fieldDescripcion, this.descripcion);
				nuevoEjercicio.put(fieldImagen, this.imagenURL);
				nuevoEjercicio.put(fieldTiempo, this.tiempoDescanso);
				for (Serie serie : series) {
					serie.mIngresarSerie(coleccion, collectionName, nombre, nombreWorkout);
				}
				ejerciciosCollection.document(this.nombre).set(nuevoEjercicio);

				System.out.println("Ejercicio insertado con éxito");
			} else {
				System.out.println("Este ejercicio ya ha sido previamente introducido");
			}

			co.close();
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para obtener todos los ejercicios

	public ArrayList<Ejercicio> mObtenerEjercicios(String coleccion, String nombreWorkout) {
		Firestore co = null;
		ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
		try {
			co = ConexionDB.conectar();

			DocumentReference referenciaWorkout = co.collection(coleccion).document(nombreWorkout);
			ApiFuture<QuerySnapshot> ejerciciosFuture = referenciaWorkout.collection(collectionName).get();
			QuerySnapshot ejerciciosSnapshot = ejerciciosFuture.get();
			List<QueryDocumentSnapshot> ejercicios = ejerciciosSnapshot.getDocuments();
			for (QueryDocumentSnapshot ejercicio : ejercicios) {

				Ejercicio e = new Ejercicio();
				e.setNombre(ejercicio.getId());
				e.setDescripcion(ejercicio.getString(fieldDescripcion));
				e.setImagenURL(ejercicio.getString(fieldImagen));
				e.setTiempoDescanso(ejercicio.getDouble(fieldTiempo));
				e.setSeries(new Serie().mObtenerSeries(coleccion, collectionName, e.getNombre(), nombreWorkout));

				listaEjercicios.add(e);
			}
			co.close();
		} catch (InterruptedException | ExecutionException | IOException e) {

			System.out.println("Se ha producido un error al intentar obtener los ejercicios en la clase Ejercicio.");
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaEjercicios;
	}

}
