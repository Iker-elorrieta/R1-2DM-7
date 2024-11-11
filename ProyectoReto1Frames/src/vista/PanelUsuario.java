package vista;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JPasswordField;

public class PanelUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textEmailR, textNombreR, textApellidosR;
	private JButton btnGuardar, btnVolver;
	private JDateChooser fecCalendar;

	// El color hay que cambiarlo a la main
	// El tema 1 el el suave y el tema2 Es para el borde y para los botones
	Color tema = new Color(0, 120, 215);
	Color tema2 = new Color(112, 162, 199);
	private JPasswordField textContrasenaR;

	/**
	 * Create the panel.
	 * 
	 * @param controlador
	 */
	public PanelUsuario() {

		Calendar calendario = Calendar.getInstance();
		int anyo = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH) + 1;
		int dia = calendario.get(Calendar.DATE);
		String formato = anyo + "-" + mes + "-" + dia;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		setBounds(288, 11, 919, 632);
		setLayout(null);

		setForeground(SystemColor.textHighlight);
		setBorder(new MatteBorder(5, 25, 5, 25, (tema)));
		setBackground(tema2);
		setLayout(null);

		// Cargar la imagen de la carpeta imagenes
		JLabel lblImagen = new JLabel(new ImageIcon(
				"C:\\Users\\in2dm3-a\\eclipse-workspace\\Reto1_27_10_Final.zip_expanded\\ProyectoReto1Frames\\img\\logoGym2.jpg"));
		lblImagen.setForeground(Color.black);
		lblImagen.setBounds(412, 11, 127, 89); // Ajusta la posición y tamaño
		add(lblImagen);

		// Agregar el JLabel para el texto
		JLabel lblNewLabel = new JLabel("GymApp");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(399, 88, 180, 73); // Cambia la posición si es necesario
		add(lblNewLabel);

		JLabel lblRegistro = new JLabel("Perfil:");
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegistro.setBounds(432, 142, 126, 41);
		add(lblRegistro);

		JLabel lblUsuarioR = new JLabel("(Nombre) Usuario:");
		lblUsuarioR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuarioR.setBounds(275, 172, 206, 73);
		add(lblUsuarioR);

		textNombreR = new JTextField();
		textNombreR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNombreR.setColumns(10);
		textNombreR.setBounds(519, 194, 219, 33);
		add(textNombreR);

		textApellidosR = new JTextField();
		textApellidosR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textApellidosR.setColumns(10);
		textApellidosR.setBounds(519, 238, 219, 33);
		add(textApellidosR);

		textEmailR = new JTextField();
		textEmailR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textEmailR.setColumns(10);
		textEmailR.setBounds(519, 285, 219, 33);
		add(textEmailR);

		JLabel lblContrasenaR = new JLabel("Contraseña:");
		lblContrasenaR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasenaR.setBounds(275, 329, 127, 51);
		add(lblContrasenaR);

		JLabel lblApellidosR = new JLabel("Apellidos:");
		lblApellidosR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellidosR.setBounds(275, 227, 127, 51);
		add(lblApellidosR);

		JLabel lblEmailR = new JLabel("Email:");
		lblEmailR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailR.setBounds(275, 274, 127, 51);
		add(lblEmailR);

		JLabel lblFechaDeNacimientoR = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimientoR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFechaDeNacimientoR.setBounds(275, 379, 224, 51);
		add(lblFechaDeNacimientoR);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(tema);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGuardar.setBackground(SystemColor.controlHighlight);
		btnGuardar.setBounds(309, 453, 354, 33);
		add(btnGuardar);

		btnVolver = new JButton("Volver a WorkOuts");
		btnVolver.setBackground(SystemColor.controlHighlight);
		btnVolver.setForeground(new Color(0, 120, 215));
		btnVolver.setBounds(412, 512, 146, 23);
		add(btnVolver);

		fecCalendar = new JDateChooser();
		fecCalendar.setBounds(519, 379, 219, 35);
		add(fecCalendar);

		JTextFieldDateEditor editor = (JTextFieldDateEditor) fecCalendar.getDateEditor();
		editor.setEditable(false);

		try {
			fecCalendar.setMaxSelectableDate(dateFormat.parse(formato));
			fecCalendar.setDate(dateFormat.parse(formato));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		textContrasenaR = new JPasswordField();
		textContrasenaR.setBounds(519, 335, 219, 33);
		add(textContrasenaR);
	}

	public JTextField getTextNombreR() {
		return textNombreR;
	}

	public void setTextNombreR(JTextField textNombreR) {
		this.textNombreR = textNombreR;
	}

	public JTextField getTextApellidosR() {
		return textApellidosR;
	}

	public void setTextApellidosR(JTextField textApellidosR) {
		this.textApellidosR = textApellidosR;
	}

	public JTextField getTextEmailR() {
		return textEmailR;
	}

	public void setTextEmailR(JTextField textEmailR) {
		this.textEmailR = textEmailR;
	}

	public JPasswordField getTextContrasenaR() {
		return textContrasenaR;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public void setTextContrasenaR(JPasswordField textContrasenaR) {
		this.textContrasenaR = textContrasenaR;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JDateChooser getFecCalendar() {
		return fecCalendar;
	}

	public void setFecCalendar(JDateChooser fecCalendar) {
		this.fecCalendar = fecCalendar;
	}
}