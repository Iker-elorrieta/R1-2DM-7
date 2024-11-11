package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import modelo.Ejercicio;
import modelo.Usuario;
import modelo.WorkOuts;
import vista.PanelEjercicio;
import vista.PanelLogin;
import vista.PanelRegistro;
import vista.PanelUsuario;
import vista.PanelWorkouts;
import vista.Principal;

public class Controlador implements ActionListener {

	private vista.Principal vistaPrincipal;
	private Usuario usuarioLogeado;
	private ArrayList<WorkOuts> lista;
	
	

	public Controlador(vista.Principal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;

		this.inicializarControlador();

	}

	private void inicializarControlador() {

		//Panel Login
		
		// Al estar en el Login atribuyo el botón de iniciar sesion y el cambio de panel a registro

		this.vistaPrincipal.getPanelLogin().getBtnIniciarSesion().addActionListener(this);
		this.vistaPrincipal.getPanelLogin().getBtnIniciarSesion()
				.setActionCommand(Principal.enumAcciones.INICIAR_SESION.toString());

		this.vistaPrincipal.getPanelLogin().getBtnRegistro().addActionListener(this);
		this.vistaPrincipal.getPanelLogin().getBtnRegistro()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_REGISTRO.toString());

		//Panel Registro
		
		// Atribuyo el botón de registrar el usuario del panel de registro y el botón de volver al inicio de sesion

		this.vistaPrincipal.getPanelRegistro().getBtnRegistrarse().addActionListener(this);
		this.vistaPrincipal.getPanelRegistro().getBtnRegistrarse()
				.setActionCommand(Principal.enumAcciones.REGISTRAR_USUARIO.toString());
	
		this.vistaPrincipal.getPanelRegistro().getBtnVolver().addActionListener(this);
		this.vistaPrincipal.getPanelRegistro().getBtnVolver()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_LOGIN.toString());

		//Panel WorkOuts
		
		// Atribuyo el botón de volver de WorkOut al inicio de sesion

		this.vistaPrincipal.getPanelWorkouts().getBtnVolver().addActionListener(this);
		this.vistaPrincipal.getPanelWorkouts().getBtnVolver()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_LOGIN.toString());
		
		//boton para modificar usuario
		this.vistaPrincipal.getPanelWorkouts().getBtnModificarPerfil().addActionListener(this);
		this.vistaPrincipal.getPanelWorkouts().getBtnModificarPerfil().setActionCommand(Principal.enumAcciones.CARGAR_PANEL_USUARIO.toString());
		
		//boton que nos lleva al Historial
		this.vistaPrincipal.getPanelWorkouts().getBtnHistorialWorkouts().addActionListener(this);
		this.vistaPrincipal.getPanelWorkouts().getBtnHistorialWorkouts().setActionCommand(Principal.enumAcciones.CARGAR_PANEL_HISTORIAL_WORKOUTS.toString());
		
		//boton que nos lleva a los Ejercicios
		this.vistaPrincipal.getPanelWorkouts().getBtnIniciarWorkout().addActionListener(this);
		this.vistaPrincipal.getPanelWorkouts().getBtnIniciarWorkout().setActionCommand(Principal.enumAcciones.CARGAR_PANEL_EJERCICIOS.toString());
		
		//Panel Usuario
		
		//panel ModificarUsuario Boton volver a inicio
		this.vistaPrincipal.getPanelUsuario().getBtnVolver().addActionListener(this);
		this.vistaPrincipal.getPanelUsuario().getBtnVolver().setActionCommand(Principal.enumAcciones.CARGAR_PANEL_WORKOUTS.toString());
		
		//panel ModificarUsuario boton guardar
		this.vistaPrincipal.getPanelUsuario().getBtnGuardar().addActionListener(this);
		this.vistaPrincipal.getPanelUsuario().getBtnGuardar().setActionCommand(Principal.enumAcciones.MODIFICAR_USUARIO.toString());
		
		//Panel Historico
		
		//panel Historico boton volver a Ejercicios
		this.vistaPrincipal.getPanelHistorialWorkouts().getBtnAtras().addActionListener(this);
		this.vistaPrincipal.getPanelHistorialWorkouts().getBtnAtras().setActionCommand(Principal.enumAcciones.CARGAR_PANEL_WORKOUTS.toString());
		

	}

	// Llamamiento a los metodos de cargar los paneles y funciones dentro de estas

	public void actionPerformed(ActionEvent e) {

		Principal.enumAcciones accion = Principal.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {

		case CARGAR_PANEL_LOGIN:

			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_LOGIN);

			break;

		case CARGAR_PANEL_REGISTRO:

			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_REGISTRO);

			break;

		case INICIAR_SESION:

			this.mIniciarSesion();

			break;

		case REGISTRAR_USUARIO:

			this.mRegistrarUsuario();

			break;
			
		case MODIFICAR_USUARIO:
			
			this.mGuardarCambios();
			
			break;
			
		case CARGAR_PANEL_WORKOUTS:
			
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUTS);

			break;

		case CARGAR_PANEL_HISTORIAL_WORKOUTS:
			
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_HISTORIAL_WORKOUTS);
			this.mInsertarInfoEnHistorico();

			break;

		case CARGAR_PANEL_EJERCICIOS:
		
			this.mostrarSeries();
			this.mActualizarPanelEjercicio();
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_EJERCICIOS);

			break;
		case CARGAR_PANEL_USUARIO:
			
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_USUARIO);
			this.mObtenerUsuario();
			
			break;

		default:

			break;

		}

	}


	// Metodo para que el usuario inicie sesion y lo valide

	

	private void mIniciarSesion() {
		
		PanelLogin panelLogin = this.vistaPrincipal.getPanelLogin();

		String usuarioIntroducido = panelLogin.getTextUsuarioL().getText().trim();
		String contrasenaIntroducida = new String(panelLogin.getTextContrasenaL().getPassword()).trim();

		if (usuarioIntroducido.isEmpty() || contrasenaIntroducida.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Usuario usuario = new Usuario();

		usuarioLogeado = usuario.mObtenerUsuario(usuarioIntroducido, contrasenaIntroducida);

		if (usuarioLogeado != null) {
			//Generamos un BackUp
			try {
                ProcessBuilder builder = new ProcessBuilder("java", "-jar", "backUp.jar");
                builder.start();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
			//Cargamos el panel de WorkOuts
			mCargarWorkOuts(Principal.enumAcciones.CARGAR_PANEL_WORKOUTS);
			panelLogin.getTextUsuarioL().setText("");
			panelLogin.getTextContrasenaL().setText("");
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			panelLogin.getTextUsuarioL().setText("");
			panelLogin.getTextContrasenaL().setText("");
		}
	}

	// Metodo para visualizar el listado de WorkOuts

	public void mCargarWorkOuts(Principal.enumAcciones accion) {

		switch (accion) {

		case CARGAR_PANEL_WORKOUTS:

			lista = new WorkOuts().mObtenerWorkouts();
			this.vistaPrincipal.getPanelWorkouts().setWorkOuts(lista);
			this.vistaPrincipal.getPanelWorkouts().setUsuario(usuarioLogeado);
			this.vistaPrincipal.getPanelWorkouts().getCmbBox()
					.setSelectedIndex((int) usuarioLogeado.getNivelUsuario() + 1);
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUTS);

			break;

		default:
			break;

		}

	}

	// Metodo para registrar el usuario
	
	public void mRegistrarUsuario() {

		PanelRegistro panelRegistro = this.vistaPrincipal.getPanelRegistro();

		String nombre = panelRegistro.getTextNombreR().getText();
		String apellidos = panelRegistro.getTextApellidosR().getText();
		String email = panelRegistro.getTextEmailR().getText();
		String contrasena = new String(panelRegistro.getTextContrasenaR().getPassword()).trim();
		Date fechaNacimiento = panelRegistro.getFecCalendar().getDate();

		if (!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !contrasena.isEmpty()
				&& emailRegistro(email)) {

			Usuario usuario = new Usuario(nombre, apellidos, email, contrasena, fechaNacimiento);
			usuario.mRegistrarUsuario();

			panelRegistro.getTextNombreR().setText("");
			panelRegistro.getTextApellidosR().setText("");
			panelRegistro.getTextEmailR().setText("");
			panelRegistro.getTextContrasenaR().setText("");
			panelRegistro.getFecCalendar().setDate(new Date());

		} else if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Algún campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (!emailRegistro(email)) {

			JOptionPane.showMessageDialog(null, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	public void mObtenerWorkout() {
	    PanelWorkouts panelWorkouts = this.vistaPrincipal.getPanelWorkouts();
	    PanelEjercicio panelEjercicio = this.vistaPrincipal.getPanelEjercicio();

	    Ejercicio ejercicio = new Ejercicio();
	    String nombreEjercicio = panelWorkouts.getListaEjercicios().getModel().getElementAt(0).toString();
	    String nombreWorkout = panelWorkouts.getListaWorkOuts().getSelectedValue().toString();

	    // Obtener descripción del ejercicio
	    String descripcionEjercicio = ejercicio.mObtenerDescripcion(nombreWorkout, nombreEjercicio);

	    // Obtener tiempos de las series
	    int tiemposSeries = ejercicio.obtenerTiempoSerie(nombreWorkout, nombreEjercicio);

	    // Asignar descripción y nombres a los labels
	    panelEjercicio.getLblNombreEjer().setText(nombreEjercicio);
	    panelEjercicio.getLblNombreWorkout().setText(nombreWorkout);
	    panelEjercicio.getLblDescripcionEjercicioWorkouts().setText(descripcionEjercicio);

	    // Pasar los tiempos al cronómetro
	    panelEjercicio.getCronometro().setTiempoSerie(tiemposSeries);
	}



	// Validamos el mail del usuario con una expresión regular

	public boolean emailRegistro(String email) {

		String emailExpresion = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		// Compila la expresión regular en un objeto del tipo Pattern.
		Pattern pattern = Pattern.compile(emailExpresion);
		// Crea un Matcher para verificar si el correo proporcionado coincide con el
		// patrón o no.
		Matcher matcher = pattern.matcher(email);
		// Devuelve true si el correo electrónico coincide con el patrón, sino devuelve
		// false.
		return matcher.matches();
	}
	
	public void mActualizarPanelEjercicio() {
		
	    PanelWorkouts panelWorkouts = this.vistaPrincipal.getPanelWorkouts();
	    PanelEjercicio panelEjercicio = this.vistaPrincipal.getPanelEjercicio();

	    Ejercicio ejercicio = new Ejercicio();
	    String nombreEjercicio = panelWorkouts.getListaEjercicios().getModel().getElementAt(0).toString();
	    String nombreWorkout = panelWorkouts.getListaWorkOuts().getSelectedValue().toString();

	    // Obtener descripción del ejercicio
	    String descripcionEjercicio = ejercicio.mObtenerDescripcion(nombreWorkout, nombreEjercicio);

	    // Obtener tiempos de las series
	    int tiemposSeries = ejercicio.obtenerTiempoSerie(nombreWorkout, nombreEjercicio);

	    // Asignar descripción y nombres a los labels
	    panelEjercicio.getLblNombreEjer().setText(nombreEjercicio);
	    panelEjercicio.getLblNombreWorkout().setText(nombreWorkout);
	    panelEjercicio.getLblDescripcionEjercicioWorkouts().setText(descripcionEjercicio);

	    // Pasar los tiempos al cronómetro
	    panelEjercicio.getCronometro().setTiempoSerie(tiemposSeries);
	}
	
	// Método para mostrar las series del ejercicio seleccionado
	
	/*public void mostrarSeries(String nombreWorkout, String nombreEjercicio) {
		
		 
	    ArrayList<Ejercicio> ejercicios = new Ejercicio().mObtenerEjercicios("coleccion", nombreWorkout);
	    PanelEjercicio panelEjercicio = this.vistaPrincipal.getPanelEjercicio();
	    // Buscar el ejercicio específico en la lista
	    
	    for (Ejercicio ejercicio : ejercicios) {
	    	
	        if (ejercicio.getNombre().equals(nombreEjercicio)) {
	           
	        	// Obtener las series de ese ejercicio	        	
	            ArrayList<Serie> series = ejercicio.getSeries();

	            // Crear un StringBuilder para almacenar los nombres de las series	            
	            StringBuilder sb = new StringBuilder();
	            
	            for (Serie serie : series) {	  	
	                sb.append(serie.getNombre()).append("\n");
	            }

	            // Establecer el texto en el JTextArea con los nombres de las series
	            panelEjercicio.getTxtSeries().setText(sb.toString());
	            break; // Salir del ciclo una vez que se encuentra el ejercicio
	        }
	    }
		
		
	    
	}*/

	private void mostrarSeries() {
		
		
	}
	public void mObtenerUsuario() {
		
	    PanelUsuario panelUsuario = this.vistaPrincipal.getPanelUsuario();

	    panelUsuario.getTextNombreR().setText(usuarioLogeado.getNombre());
	    panelUsuario.getTextApellidosR().setText(usuarioLogeado.getApellidos());
	    panelUsuario.getTextEmailR().setText(usuarioLogeado.getEmail());
	    panelUsuario.getTextContrasenaR().setText(usuarioLogeado.getContrasena());

	    panelUsuario.getTextEmailR().setEditable(false); // Para que el email no se pueda modificar
	}

	
	public void mGuardarCambios() {
		
	    PanelUsuario panelUsuario = this.vistaPrincipal.getPanelUsuario();

	    
	    usuarioLogeado.setNombre(panelUsuario.getTextNombreR().getText());
	    usuarioLogeado.setApellidos(panelUsuario.getTextApellidosR().getText());
	    usuarioLogeado.setContrasena(new String(panelUsuario.getTextContrasenaR().getPassword()));
	   
	    usuarioLogeado.mModificarUsuario(usuarioLogeado);
	}
	
	
	private void mInsertarInfoEnHistorico() {
		
		
	}


}
