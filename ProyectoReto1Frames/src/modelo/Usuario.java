package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import principal.Principal;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellidos;
	private String email; // Usaremos el email del usuario como id
	private Date fechaNacimiento;
	private String contrasena;
	private double nivelUsuario;
	private ArrayList<Historicos> historicoUsuario;
	
	
	private static final String USUARIOSFILEROUTE = "backup/usuarios.dat";

	private static final String collectionName = "Usuarios";

	private static final String fieldNombre = "nombre";
	private static final String fieldApellidos = "apellido";
	private static final String fieldContrasena = "contrasena";
	private static final String fieldFecha = "fechaNacimiento";
	private static final String fieldNivel = "nivel";

	public Usuario() {

	}

	public Usuario(String email, String contrasena) {

		this.email = email;
		this.contrasena = contrasena;
	}

	public Usuario(String nombre, String apellidos, String email, String contrasena, Date fechaNacimiento) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.nivelUsuario = 0; // Lo inicializamos a 0
	}

	public Usuario(String nombre, String apellidos, String email, String contrasena, Date fechaNacimiento,
			double nivelUsuario) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public ArrayList<Historicos> getHistoricoUsuario() {
		return historicoUsuario;
	}

	public void setHistoricoUsuario(ArrayList<Historicos> historicoUsuario) {
		this.historicoUsuario = historicoUsuario;
	}
	
	public static String getCollectionname() {
		return collectionName;
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
				nuevoUsuario.put(fieldFecha, this.fechaNacimiento);
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

			e.printStackTrace();
		}

	}

	public void mModificarUsuario(Usuario usuarioLogeado) {

		Firestore co = null;

		try {

			co = ConexionDB.conectar();

			DocumentReference dsUsuario = co.collection(collectionName).document(usuarioLogeado.getEmail());

			ApiFuture<DocumentSnapshot> future = dsUsuario.get();
			DocumentSnapshot document = future.get();

			if (document.exists()) {
				Map<String, Object> datos = new HashMap<>();

				datos.put(fieldNombre, usuarioLogeado.nombre);
				datos.put(fieldApellidos, usuarioLogeado.apellidos);
				datos.put(fieldContrasena, usuarioLogeado.contrasena);
				datos.put(fieldFecha, usuarioLogeado.fechaNacimiento);

				dsUsuario.update(datos);
			} else {

				JOptionPane.showMessageDialog(null, "Error al modificar los datos");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Metodo para Obtener un usuario para poder Iniciar Sesion

	public Usuario mObtenerUsuario(String usuarioIntroducido, String passIntroducida) {

		Principal principal = new Principal();
		
		if (principal.getInternet()) {
			
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
						setFechaNacimiento(obtenerFechaDate(dsUsuario, fieldFecha));
						setNivelUsuario(dsUsuario.getDouble(fieldNivel));
						//setNivelUsuario(dsUsuario.getDouble(fieldNivel) != null ? dsUsuario.getDouble(fieldNivel) : 0.0); // Manejar
																															// null
	
						JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
						return this;
	
					} else {
						JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrectos", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error de Inicio de Sesión", "ERROR", JOptionPane.ERROR_MESSAGE);
	
				}
	
				co.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
			    FileInputStream fic = new FileInputStream(USUARIOSFILEROUTE);
			    ObjectInputStream ois = new ObjectInputStream(fic);
			    
			    // Leer el ArrayList completo en lugar de un único Usuario
			    @SuppressWarnings("unchecked")
			    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) ois.readObject();
			    ois.close();
			    
			    for (Usuario usuario : usuarios) {
			        if (usuario.getEmail().equals(usuarioIntroducido) && usuario.getContrasena().equals(passIntroducida)) {
			            return usuario;
			        }
			    }
			} catch (IOException | ClassNotFoundException e) {
			    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
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
