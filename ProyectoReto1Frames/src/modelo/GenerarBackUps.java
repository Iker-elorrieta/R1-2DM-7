package modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GenerarBackUps {
	
	public static void main(String[] args) {
		
		escribirUsuariosEnArchivo(new Usuario().mObtenerTodosLosUsuarios());
		escribirWorkoutsEnArchivo(new WorkOuts().mObtenerWorkouts());
	}

	private static final String USUARIOSFILEROUTE = "backup/usuarios.dat";
	private static final String WORKOUTSFILEROUTE = "backup/workouts.dat";

	private static void escribirUsuariosEnArchivo(ArrayList<Usuario> usuarios) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOSFILEROUTE))) {
			oos.writeObject(usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void escribirWorkoutsEnArchivo(ArrayList<WorkOuts> workouts) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(WORKOUTSFILEROUTE))) {
			oos.writeObject(workouts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
