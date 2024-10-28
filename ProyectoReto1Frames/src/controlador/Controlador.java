package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.WorkOuts;
import vista.PanelLogin;
import vista.PanelRegistro;
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

		// Al estar en el Login atribuyo el botón de iniciar sesion y el cambio de panel
		// a registro

		this.vistaPrincipal.getPanelLogin().getBtnIniciarSesion().addActionListener(this);
		this.vistaPrincipal.getPanelLogin().getBtnIniciarSesion()
				.setActionCommand(Principal.enumAcciones.INICIAR_SESION.toString());

		this.vistaPrincipal.getPanelLogin().getBtnRegistro().addActionListener(this);
		this.vistaPrincipal.getPanelLogin().getBtnRegistro()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_REGISTRO.toString());

		// Atribuyo el botón de registrar el usuario del panel de registro y el botón de
		// volver al inicio de sesion

		this.vistaPrincipal.getPanelRegistro().getBtnRegistrarse().addActionListener(this);
		this.vistaPrincipal.getPanelRegistro().getBtnRegistrarse()
				.setActionCommand(Principal.enumAcciones.REGISTRAR_USUARIO.toString());

		this.vistaPrincipal.getPanelRegistro().getBtnVolver().addActionListener(this);
		this.vistaPrincipal.getPanelRegistro().getBtnVolver()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_LOGIN.toString());

		// Atribuyo el botón de volver de WorkOut al inicio de sesion

		this.vistaPrincipal.getPanelWorkouts().getBtnVolver().addActionListener(this);
		this.vistaPrincipal.getPanelWorkouts().getBtnVolver()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_LOGIN.toString());

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

		case CARGAR_PANEL_WORKOUTS:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUTS);

			break;

		case CARGAR_PANEL_HISTORIAL_WORKOUTS:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_HISTORIAL_WORKOUTS);

			break;

		case CARGAR_PANEL_EJERCICIOS:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_EJERCICIOS);

			break;

		default:

			break;

		}

	}

	// Metodo para que el usuario inicie sesion y lo valide

	private void mIniciarSesion() {
		PanelLogin panelLogin = this.vistaPrincipal.getPanelLogin();

		String usuarioIntroducido = panelLogin.getTextUsuarioL().getText().trim();
		String contraIntroducida = new String(panelLogin.getTextContrasenaL().getPassword()).trim();

		if (usuarioIntroducido.isEmpty() || contraIntroducida.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Usuario usuario = new Usuario();

		usuarioLogeado = usuario.mObtenerUsuario(usuarioIntroducido, contraIntroducida);

		if (usuarioLogeado != null) {
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
	private void mRegistrarUsuario() {

		PanelRegistro panelRegistro = this.vistaPrincipal.getPanelRegistro();

		String nombre = panelRegistro.getTextNombreR().getText();
		String apellidos = panelRegistro.getTextApellidosR().getText();
		String email = panelRegistro.getTextEmailR().getText();
		String contrasena = new String(panelRegistro.getTextContrasenaR().getPassword()).trim();
		Date fecNacimiento = panelRegistro.getFecCalendar().getDate();

		if (!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !contrasena.isEmpty()
				&& emailRegistro(email)) {

			Usuario usuario = new Usuario(nombre, apellidos, email, contrasena, fecNacimiento);
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

	// Validamos el mail del usuario con una expresión regular

	private boolean emailRegistro(String email) {

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

}
