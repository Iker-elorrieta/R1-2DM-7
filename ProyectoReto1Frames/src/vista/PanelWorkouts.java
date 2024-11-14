package vista;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;

import modelo.Ejercicio;
import modelo.Usuario;
import javax.swing.event.ListSelectionListener;
import modelo.WorkOuts;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

public class PanelWorkouts extends JPanel {

	private static final long serialVersionUID = 1L;

	// El color hay que cambiarlo a la main
	// El tema 1 el el suave y el tema2 Es para el borde y para los botones
	Color tema = new Color(50, 120, 215);
	Color tema2 = new Color(112, 162, 199);

	private JButton btnModificarPerfil, btnIniciarWorkout, btnHistorialWorkouts, btnVideo, btnAtras;
	private JComboBox<String> cmbBox;
	private JList<String> listaWorkOuts, listaEjercicios;
	private ArrayList<WorkOuts> WorkOuts;
	private WorkOuts workOutSeleccionado = null;
	private Usuario usuario;
	private JLabel lblVideo, lblEjercicioSeleccionado;
	private DefaultListModel<String> modeloTemporal;
	private JTextArea txtAreaVideo;

	/**
	 * Create the panel.
	 */
	public PanelWorkouts() {

		setForeground(SystemColor.textHighlight);
		setBorder(new MatteBorder(5, 25, 5, 25, (tema)));
		setBackground(tema2);
		setLayout(null);

		btnModificarPerfil = new JButton("Perfil");
		btnModificarPerfil.setForeground(tema);
		btnModificarPerfil.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnModificarPerfil.setBackground(new Color(227, 227, 227));
		btnModificarPerfil.setBounds(868, 11, 116, 51);
		add(btnModificarPerfil);

		btnHistorialWorkouts = new JButton("Historial Workouts");
		btnHistorialWorkouts.setForeground(tema);
		btnHistorialWorkouts.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnHistorialWorkouts.setBackground(SystemColor.controlHighlight);
		btnHistorialWorkouts.setBounds(719, 148, 238, 32);
		add(btnHistorialWorkouts);

		JLabel lblGymApp = new JLabel("GymApp");
		lblGymApp.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblGymApp.setBounds(434, 10, 180, 73);
		add(lblGymApp);

		JLabel lblWorkouts = new JLabel("WORKOUTS");
		lblWorkouts.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblWorkouts.setBounds(455, 63, 180, 73);
		add(lblWorkouts);

		JLabel lblSelecciona = new JLabel("SELECCIONA EL NIVEL:\r\n\r\n");
		lblSelecciona.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelecciona.setBounds(67, 132, 169, 32);
		add(lblSelecciona);

		cmbBox = new JComboBox<>(
				new String[] { "TODOS LOS NIVELES DISPONIBLES", "Nivel 0", "Nivel 1", "Nivel 2", "Nivel 3" });
		cmbBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbBox.setBounds(67, 175, 433, 32);
		cmbBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String nivelSeleccionado = (String) cmbBox.getSelectedItem();
				if (!nivelSeleccionado.equals("TODOS LOS NIVELES DISPONIBLES")) {
					int selectedNivel = Integer.parseInt(nivelSeleccionado.split(" ")[1]);
					usuario.setNivelUsuario(selectedNivel);
				} else {
					usuario.setNivelUsuario(-1); // Nivel -1 si no se selecciona ninguno
				}
				actualizarListaWorkOuts(); // Actualiza la lista según el nivel seleccionado
			}
		});
		add(cmbBox);

		JLabel lblVisualizarHistorial = new JLabel("VISUALIZA TU HISTORIAL");
		lblVisualizarHistorial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVisualizarHistorial.setBounds(522, 150, 210, 32);
		add(lblVisualizarHistorial);

		listaWorkOuts = new JList<>();
		listaWorkOuts.setBounds(67, 247, 433, 283);
		listaWorkOuts.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					String nombreWorkout = listaWorkOuts.getSelectedValue();
					/*
					 * if (selectedWorkout != null) { //Muestra el nombre del workout String
					 * nombreWorkout = selectedWorkout; workOutSeleccionado = workout; // Asignar el
					 * workout seleccionado mostrarEjercicios(nombreWorkout); // Método para mostrar
					 * los ejercicios }
					 */
					for (WorkOuts workout : WorkOuts) {

						if (workout.getNombre().equals(nombreWorkout)) {
							workOutSeleccionado = workout; // Asignar el workout seleccionado
							mostrarEjercicios(workout.getNombre());
							break;
						}
					}
				}
			}
		});
		add(listaWorkOuts);

		JLabel lblAccede = new JLabel("ACCEDE A LOS DATOS DE TU PERFIL:");
		lblAccede.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAccede.setBounds(651, 23, 231, 32);
		add(lblAccede);

		JLabel lblDetalles = new JLabel("DETALLES DEL EJERCICIO");
		lblDetalles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDetalles.setBounds(558, 359, 263, 32);
		add(lblDetalles);

		lblVideo = new JLabel("VIDEO:");
		lblVideo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVideo.setBounds(558, 489, 426, 32);
		lblVideo.setVisible(false);
		add(lblVideo);

		txtAreaVideo = new JTextArea();
		txtAreaVideo.setFont(new Font("Arial", Font.BOLD, 11));
		txtAreaVideo.setBounds(556, 534, 392, 23);
		txtAreaVideo.setVisible(false);
		add(txtAreaVideo);

		btnVideo = new JButton("LINK DE VIDEO EXPLICATORIO");
		btnVideo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (workOutSeleccionado != null && workOutSeleccionado.getVideo() != null) {

					//Obtenemos el url del video desde firebase
					String link = workOutSeleccionado.getVideo();
					
					lblVideo.setVisible(true);
					txtAreaVideo.setVisible(true);
					//Se lo asignamos al TextArea
					txtAreaVideo.setText(link);

				} else {

					txtAreaVideo.setVisible(true);
					txtAreaVideo.setText("No hay video disponible para este workout");
					lblVideo.setVisible(true);
				}
			}
		});
		btnVideo.setForeground(new Color(50, 120, 215));
		btnVideo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVideo.setBackground(SystemColor.controlHighlight);
		btnVideo.setBounds(558, 447, 253, 32);
		add(btnVideo);

		JLabel lblEjercicios = new JLabel("EJERCICIOS:\r\n");
		lblEjercicios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEjercicios.setBounds(558, 205, 186, 32);
		add(lblEjercicios);

		listaEjercicios = new JList<>();
		listaEjercicios.setBounds(556, 247, 394, 102);
		listaEjercicios.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting() && listaEjercicios.getSelectedValue() != null) {

					lblEjercicioSeleccionado
							.setText("EJERCICIO SELECCIONADO: " + listaEjercicios.getSelectedValue().toUpperCase());
					lblEjercicioSeleccionado.setVisible(true);
				}
			}
		});
		add(listaEjercicios);

		btnIniciarWorkout = new JButton("INICIAR WORKOUT\r\n");
		btnIniciarWorkout.setForeground(new Color(50, 120, 215));
		btnIniciarWorkout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnIniciarWorkout.setBackground(SystemColor.controlHighlight);
		btnIniciarWorkout.setBounds(67, 557, 433, 32);
		add(btnIniciarWorkout);

		btnAtras = new JButton("ATRÁS");
		btnAtras.setForeground(new Color(50, 120, 215));
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAtras.setBackground(SystemColor.controlHighlight);
		btnAtras.setBounds(42, 11, 116, 51);
		add(btnAtras);

		lblEjercicioSeleccionado = new JLabel("EJERCICIO SELECCIONADO:");
		lblEjercicioSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEjercicioSeleccionado.setBounds(556, 404, 400, 32);
		lblEjercicioSeleccionado.setVisible(false);
		add(lblEjercicioSeleccionado);

		if (usuario != null) {

			actualizarListaWorkOuts();
		}

	}

	// Método para actualizar la lista de WorkOuts según el nivel del usuario
	public void actualizarListaWorkOuts() {

		modeloTemporal = new DefaultListModel<>();
		modeloTemporal.clear();
		String nivelSeleccionado = (String) cmbBox.getSelectedItem();
		int filtrarNivel = nivelSeleccionado.equals("TODOS LOS NIVELES DISPONIBLES") ? -1
				: Integer.parseInt(nivelSeleccionado.split(" ")[1]);

		for (WorkOuts workout : this.WorkOuts) {
			// Si filtrarNivel es -1, muestra todos los workouts, si no, filtra por el nivel
			// específico

			if (filtrarNivel == -1 || workout.getNivel() == filtrarNivel) {

				if (workout.getNivel() <= usuario.getNivelUsuario() || filtrarNivel == -1) {

					modeloTemporal.addElement(workout.getNombre());
				}
			}
		}
		listaWorkOuts.setModel(modeloTemporal);
	}

	private void mostrarEjercicios(String nombreWorkout) {

		DefaultListModel<String> modeloEjercicios = new DefaultListModel<>(); // Crear un nuevo modelo para la lista de
																				// ejercicios

		for (WorkOuts workout : this.WorkOuts) {

			if (workout.getNombre().equals(nombreWorkout)) {

				// Recorremos los ejercicios del workout encontrado y los agregamos al modelo de
				// la lista

				for (Ejercicio ejercicio : workout.getEjercicios()) {

					modeloEjercicios.addElement(ejercicio.getNombre()); // Añadir cada ejercicio al modelo
				}
				break; // Salimos del bucle una vez que encontramos el workout
			}
		}

		listaEjercicios.setModel(modeloEjercicios); // Asignar el modelo a la lista de ejercicios
	}

	// GETTERS Y SETTERS

	public JButton getBtnModificarPerfil() {
		return btnModificarPerfil;
	}

	public void setBtnModificarPerfil(JButton btnModificarPerfil) {
		this.btnModificarPerfil = btnModificarPerfil;
	}

	public JButton getBtnIniciarWorkout() {
		return btnIniciarWorkout;
	}

	public void setBtnIniciarWorkout(JButton btnIniciarWorkout) {
		this.btnIniciarWorkout = btnIniciarWorkout;
	}

	public JButton getBtnVerVideo() {
		return btnVideo;
	}

	public void setBtnVerVideo(JButton btnVerVideo) {
		this.btnVideo = btnVerVideo;
	}

	public JComboBox<String> getCmbBox() {
		return cmbBox;
	}

	public void setCmbBox(JComboBox<String> cmbBox) {
		this.cmbBox = cmbBox;
	}

	public JList<?> getListaWorkOuts() {
		return listaWorkOuts;
	}

	public void setListaWorkOuts(JList<String> listaWorkOuts) {
		this.listaWorkOuts = listaWorkOuts;
	}

	public ArrayList<WorkOuts> getWorkOuts() {
		return WorkOuts;
	}

	public void setWorkOuts(ArrayList<WorkOuts> workOuts) {
		WorkOuts = workOuts;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public JLabel getLblVideo() {
		return lblVideo;
	}

	public void setLblVideo(JLabel lblVideo) {
		this.lblVideo = lblVideo;
	}

	public JLabel getLblEjercicioSeleccionado() {
		return lblEjercicioSeleccionado;
	}

	public void setLblEjercicioSeleccionado(JLabel lblEjercicioSeleccionado) {
		this.lblEjercicioSeleccionado = lblEjercicioSeleccionado;
	}

	public JList<?> getListaEjercicios() {
		return listaEjercicios;
	}

	public void setListaEjercicios(JList<String> listaEjercicios) {
		this.listaEjercicios = listaEjercicios;
	}

	public JButton getBtnVolver() {
		return btnAtras;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnAtras = btnVolver;
	}

	public WorkOuts getWorkOutSeleccionado() {
		return workOutSeleccionado;
	}

	public void setWorkOutSeleccionado(WorkOuts workOutSeleccionado) {
		this.workOutSeleccionado = workOutSeleccionado;
	}

	public JButton getBtnHistorialWorkouts() {
		return btnHistorialWorkouts;
	}

	public void setBtnHistorialWorkouts(JButton btnHistorialWorkouts) {
		this.btnHistorialWorkouts = btnHistorialWorkouts;
	}
}