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

public class PanelHistorialWorkouts extends JPanel {

	private static final long serialVersionUID = 1L;

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

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setForeground(tema);
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBackground(SystemColor.textText);
		btnAtras.setBounds(69, 55, 111, 42);
		add(btnAtras);

		JScrollPane jScrollPanel;
		jScrollPanel = new JScrollPane();
		jScrollPanel.setBounds(60, 121, 880, 457);
		add(jScrollPanel);

		String columnas[] = { "Nombre Workout", "Nivel", "Tiempo Total", "Tiempo Previsto", "Fecha",
				"% Ejercicios Compleados" };

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);

		tablaContactos.setDefaultEditor(Object.class, null); // Anulamos la edici�n en la propia celda

		jScrollPanel.setViewportView(tablaContactos);

	}

}
