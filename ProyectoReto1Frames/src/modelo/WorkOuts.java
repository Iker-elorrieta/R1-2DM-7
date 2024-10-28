package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import conexion.ConexionDB;

public class WorkOuts {

	private String nombre;
	private double nivel;
	private String video;
	private ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();

	private static final String collectionName = "workouts";
	private static final String fieldNivel = "nivel";
	private static final String fieldVideo = "videoUrl";

	public WorkOuts() {
		this.ejercicios = new ArrayList<>();
	}

	public WorkOuts(String nombre, double nivel, String video, ArrayList<Ejercicio> ejercicios) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.video = video;
		this.ejercicios = ejercicios;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNivel() {
		return nivel;
	}

	public void setNivel(double nivel) {
		this.nivel = nivel;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public ArrayList<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

	// Método para agregar un nuevo workout

	public void mInsertarWorkout() {
		Firestore co = null;
		try {
			co = ConexionDB.conectar();
			CollectionReference referencia = co.collection(collectionName);

			if (!referencia.document(nombre).get().get().exists()) {

				Map<String, Object> nuevoWorkout = new HashMap<>();
				nuevoWorkout.put(fieldNivel, this.nivel);
				nuevoWorkout.put(fieldVideo, this.video);

				for (Ejercicio ejercicio : ejercicios) {
					ejercicio.mIngresarEjercicio(collectionName, nombre);
				}
				referencia.document(this.nombre).set(nuevoWorkout);

				System.out.println("Workout insertado con éxito");
			} else {
				System.out.println("Este Workout ya ha sido introducido");
			}
			co.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<WorkOuts> mObtenerWorkouts() {
		Firestore co = null;
		ArrayList<WorkOuts> workOutsLista = new ArrayList<WorkOuts>();
		try {
			co = ConexionDB.conectar();

			ApiFuture<QuerySnapshot> query = co.collection(collectionName).get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> workouts = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot workout : workouts) {
				WorkOuts workOut = new WorkOuts();
				workOut.setNombre(workout.getId());
				workOut.setNivel(workout.getDouble(fieldNivel));
				workOut.setVideo(workout.getString(fieldVideo));

				workOut.setEjercicios(new Ejercicio().mObtenerEjercicios(collectionName, workOut.getNombre()));

				workOutsLista.add(workOut);
			}
			co.close();

		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Se ha producido un error al intentar obtener los WorkOut en la clase WorkOuts.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workOutsLista;
	}

}
