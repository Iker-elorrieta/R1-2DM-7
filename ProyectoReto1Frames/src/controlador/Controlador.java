package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Principal;

public class Controlador implements ActionListener{
	
	private vista.Principal vistaPrincipal;
	
	public Controlador(vista.Principal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
		
		this.inicializarControlador();
		
	}

	private void inicializarControlador() {
		
		//Activo la vista de Bienvenida y hago que su botón nos derive al panel Login
		
		this.vistaPrincipal.getPanelBienvenida().setVisible(true);
		
		this.vistaPrincipal.getBtnComenzar().addActionListener(this);
		this.vistaPrincipal.getBtnComenzar()
			.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_LOGIN.toString());
		
		//Una vez en el Login atribuyo el botón de iniciar sesion y el cambio de panel a registro
		
		this.vistaPrincipal.getBtnIniciarSesion().addActionListener(this);
		this.vistaPrincipal.getBtnIniciarSesion()
			.setActionCommand(Principal.enumAcciones.INICIAR_SESION.toString());
		
		this.vistaPrincipal.getBtnRegistro().addActionListener(this);
		this.vistaPrincipal.getBtnRegistro()
			.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_REGISTRO.toString());
		
		//Atribuyo el botón de registrar el usuario del panel de registro
		
		this.vistaPrincipal.getBtnRegistrarse().addActionListener(this);
		this.vistaPrincipal.getBtnRegistrarse()
			.setActionCommand(Principal.enumAcciones.REGISTRAR_USUARIO.toString());
		
		this.vistaPrincipal.getBtnVolver().addActionListener(this);
		this.vistaPrincipal.getBtnVolver()
			.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_LOGIN.toString());
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {

		Principal.enumAcciones accion = Principal.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {
		
			case CARGAR_PANEL_BIENVENIDA:
				this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_BIENVENIDA);
				
				break;
				
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
				
			/*case CARGAR_PANEL_WORKOUTS:
				this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_WORKOUTS);
				
				break;
			
			case CARGAR_PANEL_HISTORIAL_WORKOUTS:
				this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_HISTORIAL_WORKOUTS);
				
				break;
				
			case CARGAR_PANEL_EJERCICIOS:
				this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_EJERCICIOS);
				
				break;
				*/
			
			
			default:
				break;
				
		}

	}

	
	private void mIniciarSesion() {
		
		/*String nombreLogin, contraLogin;
	
		
		try {
			
			nombreLogin= this.vistaPrincipal.getPanelLogin().getTextUsuarioL().getText();
			contraLogin= this.vistaPrincipal.getPanelLogin().getTextContrasenaL().toString(); //COMPROBAR ESTO CON GETTEXT
			
		}catch (Exception e) {
			
			System.out.println("Error");
			
		}
		
		if(nombreLogin.isBlank() || contraLogin.isBlank()) {
			
			JOptionPane.showMessageDialog(null, "Todos los campos deben estar rellenos");
		}
		else if(telefono<111111111 || telefono > 999999999) {
			
			JOptionPane.showMessageDialog(null, "Introduce un número de teléfono valido");
		}
		
		Contacto contactoInsertado=new Contacto(nombre,telefono,email);
		contactoInsertado.mInsertarContacto(contactoInsertado);
		
		JOptionPane.showMessageDialog(null, "Contacto introducido correctamente");
		
		this.vistaPrincipal.getPanelInsertar().getTxtNombre().setText("");
		this.vistaPrincipal.getPanelInsertar().getTxtMail().setText("");
		this.vistaPrincipal.getPanelInsertar().getTextTel().setText("");
		
		mCargarContactos(Principal.enumAcciones.CARGAR_PANEL_INSERTAR);*/
	}

	private void mRegistrarUsuario() {

		String nombreLogin, contraLogin;
	
		
		try {
			
			nombreLogin= this.vistaPrincipal.getPanelLogin().getTextUsuarioL().getText();
			contraLogin= this.vistaPrincipal.getPanelLogin().getTextContrasenaL().toString(); //COMPROBAR ESTO CON GETTEXT
			
		}catch (Exception e) {
			
			System.out.println("Error");
			
		}
		
		/*if(nombreLogin.isBlank() || contraLogin.isBlank()) {
			
			JOptionPane.showMessageDialog(null, "Todos los campos deben estar rellenos");
		}
		else if(telefono<111111111 || telefono > 999999999) {
			
			JOptionPane.showMessageDialog(null, "Introduce un número de teléfono valido");
		}
		
		Contacto contactoInsertado=new Contacto(nombre,telefono,email);
		contactoInsertado.mInsertarContacto(contactoInsertado);
		
		JOptionPane.showMessageDialog(null, "Contacto introducido correctamente");
		
		this.vistaPrincipal.getPanelInsertar().getTxtNombre().setText("");
		this.vistaPrincipal.getPanelInsertar().getTxtMail().setText("");
		this.vistaPrincipal.getPanelInsertar().getTextTel().setText("");
		
		mCargarContactos(Principal.enumAcciones.CARGAR_PANEL_INSERTAR);*/
		
	}


	
}

