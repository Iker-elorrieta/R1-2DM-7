package vista;


import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import controlador.Controlador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelRegistro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textUsuarioR;
	private JTextField textApellidosR;
	private JTextField textEmailR;
	private JTextField textContrasenaR;
	
	private JButton btnRegistrarse, btnVolver;
	
	 
	//El color hay que cambiarlo a la main
	//El tema 1 el el suave y el tema2 Es para el borde y para los botones
	Color tema =new Color(0, 120, 215);
	Color tema2 =new Color(112, 162, 199);
	 
	/**
	 * Create the panel.
	 * @param controlador 
	 */
	 public PanelRegistro() {
		 

		 
		 
 		setForeground(SystemColor.textHighlight);
		setBorder(new MatteBorder(5, 25, 5, 25, (tema)));
		setBackground(tema2);
	    setLayout(null);

	        // Cargar la imagen de la carpeta imagenes
	        ImageIcon icon = new ImageIcon("imagenes/logoGym2.jpg");
	        JLabel lblImagen = new JLabel(new ImageIcon("E:\\2DamElorrieta\\RETOOOO\\GymApp\\imagenes\\logoGym2.jpg"));
	        lblImagen.setForeground(Color.black); 
	        lblImagen.setBounds(369, 0, 127, 89); // Ajusta la posición y tamaño
	        add(lblImagen);

	        // Agregar el JLabel para el texto
	        JLabel lblNewLabel = new JLabel("GymApp");
	        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
	        lblNewLabel.setBounds(379, 46, 180, 73); // Cambia la posición si es necesario
	        add(lblNewLabel);
	        
	        JLabel lblRegistro = new JLabel("Registro:");
	        lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 25));
	        lblRegistro.setBounds(382, 128, 187, 51);
	        add(lblRegistro);
	        
	        JLabel lblUsuarioR = new JLabel("(Nombre) Usuario:");
	        lblUsuarioR.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblUsuarioR.setBounds(249, 172, 206, 73);
	        add(lblUsuarioR);
	        
	        textUsuarioR = new JTextField();
	        textUsuarioR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        textUsuarioR.setColumns(10);
	        textUsuarioR.setBounds(493, 186, 219, 44);
	        add(textUsuarioR);
	        
	        textApellidosR = new JTextField();
	        textApellidosR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        textApellidosR.setColumns(10);
	        textApellidosR.setBounds(493, 250, 219, 44);
	        add(textApellidosR);
	        
	        textEmailR = new JTextField();
	        textEmailR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        textEmailR.setColumns(10);
	        textEmailR.setBounds(493, 316, 219, 44);
	        add(textEmailR);
	        
	        textContrasenaR = new JTextField();
	        textContrasenaR.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        textContrasenaR.setColumns(10);
	        textContrasenaR.setBounds(493, 378, 219, 44);
	        add(textContrasenaR);
	        
	        JLabel lblContrasenaR = new JLabel("Contraseña:");
	        lblContrasenaR.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblContrasenaR.setBounds(259, 378, 127, 51);
	        add(lblContrasenaR);
	        
	        JLabel lblApellidosR = new JLabel("Apellidos:");
	        lblApellidosR.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblApellidosR.setBounds(259, 243, 127, 51);
	        add(lblApellidosR);
	        
	        JLabel lblEmailR = new JLabel("Email:");
	        lblEmailR.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblEmailR.setBounds(259, 316, 127, 51);
	        add(lblEmailR);
	        
	        JLabel lblFechaDeNacimientoR = new JLabel("Fecha de nacimiento:");
	        lblFechaDeNacimientoR.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblFechaDeNacimientoR.setBounds(259, 438, 224, 51);
	        add(lblFechaDeNacimientoR);
	        
	
	        
	        btnRegistrarse = new JButton("Registrarse");
	        btnRegistrarse.setForeground(tema);
	        btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        btnRegistrarse.setBackground(SystemColor.textText);
	        btnRegistrarse.setBounds(289, 500, 354, 51);
	        add(btnRegistrarse);
	        
	        btnVolver = new JButton("Volver a Login");
	        btnVolver.setBounds(423, 576, 136, 23);
	        add(btnVolver);
	    }

	
	 public JTextField getTextUsuarioR() {
		return textUsuarioR;
	}

	public void setTextUsuarioR(JTextField textUsuarioR) {
		this.textUsuarioR = textUsuarioR;
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

	public JTextField getTextContrasenaR() {
		return textContrasenaR;
	}

	public void setTextContrasenaR(JTextField textContrasenaR) {
		this.textContrasenaR = textContrasenaR;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}

	public void setBtnRegistrarse(JButton btnRegistrarse) {
		this.btnRegistrarse = btnRegistrarse;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}
	 
	 	
}