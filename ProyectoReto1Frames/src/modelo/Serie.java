package modelo;

import java.io.IOException;
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

public class Serie {

	private String nombre;
	private String imagenUrl;
	private double repeticiones;
	private double tiempoSerie;

	private static final String collectionName = "series";
	private static final String fieldImagen = "imagenUrl";
	private static final String fieldRepeticion = "repeticiones";
	private static final String fieldTiempo = "tiempoSerie";

	// Constructores

	public Serie() {

	}

	public Serie(String nombre, String imagenUrl, double repeticiones, double tiempoSerie) {

		this.nombre = nombre;
		this.repeticiones = repeticiones;
		this.imagenUrl = imagenUrl;
		this.tiempoSerie = tiempoSerie;
	}

	// Getters y Setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public double getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(double repeticiones) {
		this.repeticiones = repeticiones;
	}

	public double getTiempoSerie() {
		return tiempoSerie;
	}

	public void setTiempoSerie(double tiempoSerie) {
		this.tiempoSerie = tiempoSerie;
	}

	// Método para agregar una nueva serie

	public void mIngresarSerie(String nombreColeccion, String nombreColeccionEjercicio, String nombreEjercicio,
			String nombreWorkout) {
		Firestore firestoreConnection = null;
		try {
			firestoreConnection = ConexionDB.conectar();

			// Obtener referencia de documento de workout
			DocumentReference documentoWorkout = firestoreConnection.collection(nombreColeccion)
					.document(nombreWorkout);
			DocumentReference documentoEjercicio = documentoWorkout.collection(nombreColeccionEjercicio)
					.document(nombreEjercicio);
			CollectionReference coleccionSeries = documentoEjercicio.collection(collectionName);

			// Verificar si la serie ya existe
			if (!coleccionSeries.document(this.nombre).get().get().exists()) {
				Map<String, Object> nuevaSerie = new HashMap<>();
				nuevaSerie.put(fieldImagen, this.imagenUrl);
				nuevaSerie.put(fieldRepeticion, this.repeticiones);

				coleccionSeries.document(this.nombre).set(nuevaSerie);

				System.out.println("La serie ha sido insertada con éxito");
			} else {
				System.out.println("La serie ya fue previamente introducida");
			}

			firestoreConnection.close();
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para obtener todas las series

	public ArrayList<Serie> mObtenerSeries(String nombreColeccion, String nombreColeccionEjercicio,
			String nombreEjercicio, String nombreWorkout) {

		Firestore firestoreConnection = null;
		ArrayList<Serie> listaDeSeries = new ArrayList<>();

		try {
			firestoreConnection = ConexionDB.conectar();

			DocumentReference documentoWorkout = firestoreConnection.collection(nombreColeccion)
					.document(nombreWorkout);
			DocumentReference documentoEjercicio = documentoWorkout.collection(nombreColeccionEjercicio)
					.document(nombreEjercicio);
			ApiFuture<QuerySnapshot> seriesFuture = documentoEjercicio.collection(collectionName).get();
			QuerySnapshot seriesSnapshot = seriesFuture.get();
			List<QueryDocumentSnapshot> documentosSeries = seriesSnapshot.getDocuments();

			for (QueryDocumentSnapshot documentoSerie : documentosSeries) {
				Serie serie = new Serie();
				serie.setNombre(documentoSerie.getId());
				serie.setRepeticiones(documentoSerie.getDouble(fieldRepeticion));
				serie.setImagenUrl(documentoSerie.getString(fieldImagen));
				listaDeSeries.add(serie);
			}
			firestoreConnection.close();
		} catch (InterruptedException | ExecutionException | IOException e) {
			System.out.println("Se ha producido un error al intentar obtener las series en la clase Serie.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaDeSeries;
	}

	// Método para actualizar una serie

	public void actualizarSerie(String nombreColeccion, String nombreWorkout, String nombreEjercicio) {
		Firestore firestoreConnection = null;
		try {
			firestoreConnection = ConexionDB.conectar();
			DocumentReference ejercicioDoc = firestoreConnection.collection(nombreColeccion).document(nombreEjercicio);
			CollectionReference ejerciciosCollection = ejercicioDoc.collection(collectionName);

			DocumentReference serieDoc = ejerciciosCollection.document(nombreWorkout);
			Map<String, Object> serieActualizada = new HashMap<>();
			serieActualizada.put(fieldImagen, this.imagenUrl);
			serieActualizada.put(fieldRepeticion, this.repeticiones);

			serieDoc.update(serieActualizada);

			System.out.println("Serie actualizada con éxito");
			firestoreConnection.close();
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una serie

	public void eliminarSerie(String nombreColeccion, String nombreWorkout, String nombreEjercicio) {
		Firestore firestoreConnection = null;
		try {
			firestoreConnection = ConexionDB.conectar();
			CollectionReference coleccionReferencia = firestoreConnection.collection(nombreColeccion);
			DocumentReference documentoWorkout = coleccionReferencia.document(nombreWorkout);
			DocumentReference documentoEjercicio = documentoWorkout.collection("ejercicios").document(nombreEjercicio);
			CollectionReference coleccionSeries = documentoEjercicio.collection(collectionName);

			coleccionSeries.document(nombre).delete();

			System.out.println("Serie eliminada con éxito");
			firestoreConnection.close();
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
