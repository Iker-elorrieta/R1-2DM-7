package vista;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

public class PanelHistorialWorkouts extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnAtras;
	private JTable tablaContactos;
	private DefaultTableModel defaultTableModel;
	// El color hay que cambiarlo a la main
	// El tema 1 el el suave y el tema2 Es para el borde y para los botones
	Color tema = new Color(50, 120, 215);
	Color tema2 = new Color(112, 162, 199);

	/**
	 * Create the panel.
	 */
	public PanelHistorialWorkouts() {

		setForeground(SystemColor.textHighlight);
		setBorder(new MatteBorder(5, 25, 5, 25, (tema)));
		setBackground(tema2);
		setLayout(null);

		btnAtras = new JButton("ATRÁS");
		btnAtras.setForeground(tema);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAtras.setBackground(SystemColor.controlHighlight);
		btnAtras.setBounds(42, 11, 111, 42);
		add(btnAtras);

		JScrollPane jScrollPanel;
		jScrollPanel = new JScrollPane();
		jScrollPanel.setBounds(76, 126, 851, 396);
		add(jScrollPanel);

		String columnas[] = { "Nombre Workout", "Nivel", "Tiempo Previsto", "Tiempo Total", "Fecha",
				"% Ejercicios Completados" };

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);

		tablaContactos.setDefaultEditor(Object.class, null); // Anulamos la edicion en la propia celda

		jScrollPanel.setViewportView(tablaContactos);
		
		JLabel lblGymApp = new JLabel("GymApp");
		lblGymApp.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblGymApp.setBounds(412, 11, 180, 57);
		add(lblGymApp);
		
		JLabel lblHistoricoWorkouts = new JLabel("HISTORIAL DE WORKOUTS");
		lblHistoricoWorkouts.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblHistoricoWorkouts.setBounds(367, 64, 278, 42);
		add(lblHistoricoWorkouts);

	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	public JTable getTablaContactos() {
		return tablaContactos;
	}

	public void setTablaContactos(JTable tablaContactos) {
		this.tablaContactos = tablaContactos;
	}
}
