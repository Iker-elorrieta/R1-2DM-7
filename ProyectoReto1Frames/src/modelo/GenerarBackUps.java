package modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GenerarBackUps {
	
	public static void main(String[] args) {
		
		guardarUsuarios(new Usuario().mObtenerTodosLosUsuarios());
		guardarWorkouts(new WorkOuts().mObtenerWorkouts());
	}

	private static final String USUARIOSFILEROUTE = "backup/usuarios.dat";
	private static final String WORKOUTSFILEROUTE = "backup/workouts.dat";

    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOSFILEROUTE))) {
            for (Usuario usu : usuarios) {
                oos.writeObject(usu);
            }
            System.out.println("Users saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarWorkouts(ArrayList<WorkOuts> workouts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(WORKOUTSFILEROUTE))) {
            for (WorkOuts wot : workouts) {
                oos.writeObject(wot);
            }
            System.out.println("Workouts saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
