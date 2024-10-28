package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	public static enum enumAcciones {

		CARGAR_PANEL_LOGIN, CARGAR_PANEL_REGISTRO, CARGAR_PANEL_WORKOUTS, CARGAR_PANEL_HISTORIAL_WORKOUTS,
		CARGAR_PANEL_EJERCICIOS,

		INICIAR_SESION, REGISTRAR_USUARIO,
	}

	private JPanel panelContenedor;

	private PanelLogin panelLogin;
	private PanelRegistro panelRegistro;
	private PanelWorkouts panelWorkouts;
	private PanelHistorialWorkouts panelHistorialWorkouts;
	private PanelEjercicio panelEjercicio;

	public Principal() {

		// JPanel que contiene todo el contenido de la ventana
		mCrearPanelContenedor();

		// Panel que contiene todo el contenido del Login
		mCrearPanelLogin();

		// Panel que contiene todo el contenido del Registro
		mCrearPanelRegistro();

		// Panel que contiene todo el contenido de los Workouts
		mCrearPanelWorkouts();

		// Panel que contiene todo el contenido de los Histroiales de Workouts
		mCrearPanelHistorialWorkouts();

		// Panel que contiene todo el contenido de los Ejercicios
		mCrearPanelEjercicio();

	}

	private void mCrearPanelContenedor() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelContenedor = new JPanel();
		setBounds(0, 0, 1000, 600);
		panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenedor);
		panelContenedor.setLayout(null);
	}

	private void mCrearPanelLogin() {

		panelLogin = new PanelLogin();
		panelLogin.setBounds(0, 0, 1000, 600);
		panelContenedor.add(panelLogin);
		panelLogin.setVisible(true);

	}

	private void mCrearPanelRegistro() {

		panelRegistro = new PanelRegistro();
		panelRegistro.setBounds(0, 0, 1000, 600);
		panelContenedor.add(panelRegistro);
		panelRegistro.setVisible(false);

	}

	private void mCrearPanelWorkouts() {
		panelWorkouts = new PanelWorkouts();
		panelWorkouts.setBounds(0, 0, 1036, 753);
		panelContenedor.add(panelWorkouts);
		panelWorkouts.setVisible(false);

	}

	private void mCrearPanelHistorialWorkouts() {
		panelHistorialWorkouts = new PanelHistorialWorkouts();
		panelHistorialWorkouts.setBounds(0, 0, 1000, 600);
		panelContenedor.add(panelHistorialWorkouts);
		panelHistorialWorkouts.setVisible(false);

	}

	private void mCrearPanelEjercicio() {

		panelEjercicio = new PanelEjercicio();
		panelEjercicio.setBounds(0, 0, 1000, 600);
		panelContenedor.add(panelEjercicio);
		panelEjercicio.setVisible(false);

	}

	// *** FIN creaci�n de paneles ***

	public void mVisualizarPaneles(enumAcciones panel) {

		// Ocultamos todos los paneles antes de mostrar el deseado
		panelLogin.setVisible(false);
		panelRegistro.setVisible(false);
		panelWorkouts.setVisible(false);
		panelHistorialWorkouts.setVisible(false);
		panelEjercicio.setVisible(false);

		// Generamos un switch para acceder a cada panel
		switch (panel) {

		case CARGAR_PANEL_LOGIN:
			setSize(1000, 600);
			panelLogin.setVisible(true);
			break;

		case CARGAR_PANEL_REGISTRO:
			setSize(1000, 600);
			panelRegistro.setVisible(true);
			break;

		case CARGAR_PANEL_WORKOUTS:
			setSize(1036, 753); // Cambia el tamaño de la ventana solo para PanelWorkouts
			panelWorkouts.setVisible(true);
			break;

		case CARGAR_PANEL_HISTORIAL_WORKOUTS:
			setSize(1000, 600);
			panelHistorialWorkouts.setVisible(true);
			break;

		case CARGAR_PANEL_EJERCICIOS:
			setSize(1000, 600);
			panelEjercicio.setVisible(true);
			break;

		default:
			break;
		}
	}

	public JPanel getPanelContenedor() {
		return panelContenedor;
	}

	public void setPanelContenedor(JPanel panelContenedor) {
		this.panelContenedor = panelContenedor;
	}

	public PanelLogin getPanelLogin() {
		return panelLogin;
	}

	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}

	public PanelRegistro getPanelRegistro() {
		return panelRegistro;
	}

	public void setPanelRegistro(PanelRegistro panelRegistro) {
		this.panelRegistro = panelRegistro;
	}

	public PanelWorkouts getPanelWorkouts() {
		return panelWorkouts;
	}

	public void setPanelWorkouts(PanelWorkouts panelWorkouts) {
		this.panelWorkouts = panelWorkouts;
	}

	public PanelHistorialWorkouts getPanelHistorialWorkouts() {
		return panelHistorialWorkouts;
	}

	public void setPanelHistorialWorkouts(PanelHistorialWorkouts panelHistorialWorkouts) {
		this.panelHistorialWorkouts = panelHistorialWorkouts;
	}

	public PanelEjercicio getPanelEjercicio() {
		return panelEjercicio;
	}

	public void setPanelEjercicio(PanelEjercicio panelEjercicio) {
		this.panelEjercicio = panelEjercicio;
	}

}
