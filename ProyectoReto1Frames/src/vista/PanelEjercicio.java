package vista;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PanelEjercicio extends JPanel {

	private static final long serialVersionUID = 1L;
	// El color hay que cambiarlo a la main
	// El tema 1 el el suave y el tema2 Es para el borde y para los botones
	Color tema = new Color(50, 120, 215);
	Color tema2 = new Color(112, 162, 199);

	/**
	 * Create the panel.
	 */
	public PanelEjercicio() {

		setForeground(SystemColor.textHighlight);
		setBorder(new MatteBorder(5, 25, 5, 25, (tema)));
		setBackground(tema2);
		setLayout(null);

		JLabel lblNombreEjercicio = new JLabel("NombreEjercicioSeleccionado y descripcion");
		lblNombreEjercicio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreEjercicio.setBounds(215, 22, 407, 77);
		add(lblNombreEjercicio);

		JLabel lblNombreWorkout = new JLabel("NombreDelWorkout");
		lblNombreWorkout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreWorkout.setBounds(692, 22, 276, 77);
		add(lblNombreWorkout);

		JLabel lblSerie_1 = new JLabel("Serie1");
		lblSerie_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSerie_1.setBounds(387, 207, 82, 33);
		add(lblSerie_1);

		JLabel lblSerie_2 = new JLabel("Serie2");
		lblSerie_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSerie_2.setBounds(387, 273, 82, 33);
		add(lblSerie_2);

		JLabel lblSerie_3 = new JLabel("Serie3");
		lblSerie_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSerie_3.setBounds(387, 331, 82, 33);
		add(lblSerie_3);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSalir.setBackground(Color.RED);
		btnSalir.setForeground(Color.RED);
		btnSalir.setBounds(837, 546, 131, 41);
		add(btnSalir);

		JLabel lblIMGConometro = new JLabel();
		lblIMGConometro.setBounds(24, 11, 82, 77);
		ImageIcon iconoOriginal = new ImageIcon("E:\\2DamElorrieta\\RETOOOO\\GymApp\\imagenes\\cronometro.png");
		Image imagen = iconoOriginal.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(lblIMGConometro.getWidth(), lblIMGConometro.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		lblIMGConometro.setIcon(iconoRedimensionado);
		add(lblIMGConometro);

		JLabel lblSegundos = new JLabel("12");
		lblSegundos.setForeground(Color.WHITE);
		lblSegundos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSegundos.setBounds(51, 83, 75, 33);
		add(lblSegundos);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setForeground(new Color(0, 128, 0));
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnIniciar.setBackground(new Color(0, 255, 0));
		btnIniciar.setBounds(359, 476, 131, 41);
		add(btnIniciar);

		JLabel lblTiempoEjercicio = new JLabel("Tiempo Ejericio:");
		lblTiempoEjercicio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTiempoEjercicio.setBounds(38, 127, 187, 33);
		add(lblTiempoEjercicio);

		JLabel lblTiempoDescanso = new JLabel("Tiempo descanso:");
		lblTiempoDescanso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTiempoDescanso.setBounds(38, 183, 187, 33);
		add(lblTiempoDescanso);

	}
}
