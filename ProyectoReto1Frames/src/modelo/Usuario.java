package modelo;

import java.util.Date;

public class Usuario {

	private String idUsuario;
	private String nombre;
	private String apellido;
	private String email;
	private Date fechaNac;
	private String contrasena;
	
	private static String collectionName = "Usuarios";
	private static String fieldNombre = "nombre";
	private static String fieldApellido = "apellido";
	private static String fieldContrasena = "contraseña";
	private static String fieldEmail = "email";
	private static String fieldFecha = "fecha_nacimiento";
	
	
	
	
	
	
	
	
	
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	public static String getCollectionName() {
		return collectionName;
	}
	public static void setCollectionName(String collectionName) {
		Usuario.collectionName = collectionName;
	}
	public static String getFieldNombre() {
		return fieldNombre;
	}
	public static void setFieldNombre(String fieldNombre) {
		Usuario.fieldNombre = fieldNombre;
	}
	public static String getFieldApellido() {
		return fieldApellido;
	}
	public static void setFieldApellido(String fieldApellido) {
		Usuario.fieldApellido = fieldApellido;
	}
	public static String getFieldContrasena() {
		return fieldContrasena;
	}
	public static void setFieldContrasena(String fieldContrasena) {
		Usuario.fieldContrasena = fieldContrasena;
	}
	public static String getFieldEmail() {
		return fieldEmail;
	}
	public static void setFieldEmail(String fieldEmail) {
		Usuario.fieldEmail = fieldEmail;
	}
	public static String getFieldFecha() {
		return fieldFecha;
	}
	public static void setFieldFecha(String fieldFecha) {
		Usuario.fieldFecha = fieldFecha;
	}
	
	

}
