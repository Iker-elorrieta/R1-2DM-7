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
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;

public class PanelWorkouts extends JPanel {

	private static final long serialVersionUID = 1L;

// El color hay que cambiarlo a la main
// El tema 1 el el suave y el tema2 Es para el borde y para los botones
	Color tema = new Color(50, 120, 215);
	Color tema2 = new Color(112, 162, 199);

	private JButton btnModificarPerfil, btnIniciarWorkout, btnVerVideo, btnVolver;
	private JComboBox<String> cmbBox;
	private JList<String> listaWorkOuts;
	private ArrayList<WorkOuts> WorkOuts;
	private WorkOuts workOutSeleccionado = null;
	private Usuario usuario;
	private JLabel lblVideo, lblEjercicioSeleccionado;
	private JTextArea listaEjercicios;
	private DefaultListModel<String> modeloTemporal;

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

		JButton btnHistorialWorkouts = new JButton("Historial Workouts");
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
		lblSelecciona.setBounds(67, 146, 169, 32);
		add(lblSelecciona);

		cmbBox = new JComboBox<>(
				new String[] { "TODOS LOS NIVELES DISPONIBLES", "Nivel 0", "Nivel 1", "Nivel 2", "Nivel 3" });
		cmbBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbBox.setBounds(232, 148, 477, 32);
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
		lblVisualizarHistorial.setBounds(747, 105, 210, 32);
		add(lblVisualizarHistorial);

		listaWorkOuts = new JList<>();
		listaWorkOuts.setBounds(67, 247, 433, 283);
		listaWorkOuts.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					String selectedWorkout = listaWorkOuts.getSelectedValue();
					if (selectedWorkout != null) {
						// Extrae el nombre del workout (asumiendo que el formato es "WorkOut: nombre")
						String nombreWorkout = selectedWorkout.split(": ")[1];
						mostrarEjercicios(nombreWorkout); // Método para mostrar los ejercicios
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

		btnVerVideo = new JButton("VER VIDEO EXPLICATORIO\r\n");
		btnVerVideo.setForeground(new Color(50, 120, 215));
		btnVerVideo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerVideo.setBackground(SystemColor.controlHighlight);
		btnVerVideo.setBounds(558, 447, 253, 32);
		add(btnVerVideo);

		lblVideo = new JLabel("VIDEO:");
		lblVideo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVideo.setBounds(558, 489, 426, 32);
		add(lblVideo);
		lblVideo.setVisible(false);

		JLabel lblEjercicios = new JLabel("EJERCICIOS:\r\n");
		lblEjercicios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEjercicios.setBounds(558, 205, 186, 32);
		add(lblEjercicios);

		listaEjercicios = new JTextArea();
		listaEjercicios.setEditable(false);
		listaEjercicios.setBounds(556, 247, 394, 102);
		add(listaEjercicios);

		btnIniciarWorkout = new JButton("INICIAR WORKOUT\r\n");
		btnIniciarWorkout.setForeground(new Color(50, 120, 215));
		btnIniciarWorkout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnIniciarWorkout.setBackground(SystemColor.controlHighlight);
		btnIniciarWorkout.setBounds(67, 557, 433, 32);
		add(btnIniciarWorkout);

		btnVolver = new JButton("VOLVER");
		btnVolver.setForeground(new Color(50, 120, 215));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVolver.setBackground(SystemColor.controlHighlight);
		btnVolver.setBounds(42, 11, 116, 51);
		add(btnVolver);

		lblEjercicioSeleccionado = new JLabel("EJERCICIO SELECCIONADO:");
		lblEjercicioSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
	// Si filtrarNivel es -1, muestra todos los workouts, si no, filtra por el nivel específico
			if (filtrarNivel == -1 || workout.getNivel() == filtrarNivel) {
				if (workout.getNivel() <= usuario.getNivelUsuario() || filtrarNivel == -1) {
					modeloTemporal.addElement("WorkOut: " + workout.getNombre());
				}
			}
		}
		listaWorkOuts.setModel(modeloTemporal);
	}

	private void mostrarEjercicios(String nombreWorkout) {
		for (WorkOuts workout : this.WorkOuts) {
			if (workout.getNombre().equals(nombreWorkout)) {
				// Inicializamos una cadena vacía para almacenar los ejercicios
				String ejercicios = "";

				for (Ejercicio ejercicio : workout.getEjercicios()) {
					ejercicios += ejercicio.getNombre() + "\n"; // Concatenar cada ejercicio con un salto de línea
				}

				listaEjercicios.setText(ejercicios); // Mostrar los ejercicios en el JTextArea
				break; // Salimos del bucle una vez que encontramos el workout
			}
		}
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
		return btnVerVideo;
	}

	public void setBtnVerVideo(JButton btnVerVideo) {
		this.btnVerVideo = btnVerVideo;
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

	public JTextArea getListaEjercicios() {
		return listaEjercicios;
	}

	public void setListaEjercicios(JTextArea listaEjercicios) {
		this.listaEjercicios = listaEjercicios;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public WorkOuts getWorkOutSeleccionado() {
		return workOutSeleccionado;
	}

	public void setWorkOutSeleccionado(WorkOuts workOutSeleccionado) {
		this.workOutSeleccionado = workOutSeleccionado;
	}

}