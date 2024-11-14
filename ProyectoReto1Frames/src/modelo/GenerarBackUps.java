package modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GenerarBackUps {
	
	private static final String USUARIOSFILEROUTE = "backup/usuarios.dat";
	private static final String WORKOUTSFILEROUTE = "backup/workouts.dat";

	public static void main(String[] args) {
		
		guardarUsuarios(new Usuario().mObtenerTodosLosUsuarios());
		guardarWorkouts(new WorkOuts().mObtenerWorkouts());
	}

	
	public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOSFILEROUTE))) {
	        oos.writeObject(usuarios);  // Guarda el ArrayList completo en una sola operación
	        System.out.println("Usuarios almacenados");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
   
	public static void guardarWorkouts(ArrayList<WorkOuts> workouts) {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(WORKOUTSFILEROUTE))) {
	        oos.writeObject(workouts);  // Guarda el ArrayList completo en una sola operación
	        System.out.println("Workouts almacenados");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
