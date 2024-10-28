package modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import conexion.ConexionDB;

public class Usuario implements Serializable {

	private String nombre;
	private String apellidos;
	private String email; // Usaremos el email del usuario como id
	private Date fechaNac;
	private String contrasena;
	private double nivelUsuario;

	private static final String collectionName = "Usuarios";

	private static final String fieldNombre = "nombre";
	private static final String fieldApellidos = "apellido";
	private static final String fieldContrasena = "contraseña";
	private static final String fieldFecha = "fechaNacimiento";
	private static final String fieldNivel = "nivel";

	public Usuario() {

	}

	public Usuario(String email, String contrasena) {

		this.email = email;
		this.contrasena = contrasena;
	}

	public Usuario(String nombre, String apellidos, String email, String contrasena, Date fechaNac) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNac = fechaNac;
		this.nivelUsuario = 0; // Lo inicializamos a 0
	}

	public Usuario(String nombre, String apellidos, String email, String contrasena, Date fechaNac,
			double nivelUsuario) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNac = fechaNac;
		this.nivelUsuario = nivelUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public double getNivelUsuario() {
		return nivelUsuario;
	}

	public void setNivelUsuario(double nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}

	// Metodo de Registrar usuario en el Firebase

	public void mRegistrarUsuario() {
		Firestore co = null;
		try {
			co = ConexionDB.conectar();

			CollectionReference root = co.collection(collectionName);

			if (!root.document(this.email).get().get().exists()) {

				Map<String, Object> nuevoUsuario = new HashMap<>();
				nuevoUsuario.put(fieldNombre, this.nombre);
				nuevoUsuario.put(fieldApellidos, this.apellidos);
				nuevoUsuario.put(fieldContrasena, this.contrasena);
				nuevoUsuario.put(fieldFecha, this.fechaNac);
				nuevoUsuario.put(fieldNivel, (Double) this.nivelUsuario);
				DocumentReference newCont = root.document(this.email);
				newCont.set(nuevoUsuario);
				JOptionPane.showMessageDialog(null, "Usuario creado con éxito");

			} else {

				JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese email");
			}
			co.close();

		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Metodo para Obtener un usuario para poder Iniciar Sesion

	public Usuario mObtenerUsuario(String usuarioIntroducido, String passIntroducida) {

		Firestore co = null;

		try {
			co = ConexionDB.conectar();

			if (co.collection(collectionName).document(usuarioIntroducido).get().get().exists()) {

				DocumentSnapshot dsUsuario = co.collection(collectionName).document(usuarioIntroducido).get().get();

				if (dsUsuario.getString(fieldContrasena).equals(passIntroducida)) {

					setEmail(dsUsuario.getId());
					setNombre(dsUsuario.getString(fieldNombre));
					setApellidos(dsUsuario.getString(fieldApellidos));
					setContrasena(dsUsuario.getString(fieldContrasena));
					setFechaNac(obtenerFechaDate(dsUsuario, fieldFecha));
					// setNivelUsuario(dsUsuario.getDouble(fieldNivel));
					setNivelUsuario(dsUsuario.getDouble(fieldNivel) != null ? dsUsuario.getDouble(fieldNivel) : 0.0); // Manejar
																														// null

					JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
					return this;

				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error de Inicio de Sesión", "ERROR", JOptionPane.ERROR_MESSAGE);

			}

			co.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Convertir el timeStamp de Firestore al formato date

	public Date obtenerFechaDate(DocumentSnapshot documentSnapshot, String nombreCampo) {

		Timestamp timestamp = documentSnapshot.getTimestamp(nombreCampo);

		return (timestamp != null) ? timestamp.toDate() : null;

	}

	// Metodo para obtener todos los usuarios
	public ArrayList<Usuario> mObtenerTodosLosUsuarios() {
		Firestore co = null;

		ArrayList<Usuario> usuariosLista = new ArrayList<Usuario>();

		try {
			co = ConexionDB.conectar();

			ApiFuture<QuerySnapshot> query = co.collection(collectionName).get();

			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> usuariosFireBase = querySnapshot.getDocuments();

			for (QueryDocumentSnapshot usuarioFireBase : usuariosFireBase) {

				Usuario usuario = new Usuario(usuarioFireBase.getString(fieldNombre),
						usuarioFireBase.getString(fieldApellidos), usuarioFireBase.getId(),
						usuarioFireBase.getString(fieldContrasena), usuarioFireBase.getDate(fieldFecha),
						usuarioFireBase.getDouble(fieldNivel));

				usuariosLista.add(usuario);
			}
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Se ha producido un error al intentar obtener los usuarios en la clase Usuario.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuariosLista;
	}

}
