package vista;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;


public class PanelLogin extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textUsuarioL;
	private JPasswordField textContrasenaL;
	private JButton btnIniciarSesion, btnRegistro;
	
	
	public PanelLogin() {
    	Color tema =new Color(0, 120, 215);
    	Color tema2 =new Color(112, 162, 199);

    

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
	        lblNewLabel.setBounds(379, 80, 180, 73); // Cambia la posición si es necesario
	        add(lblNewLabel);
	        
	        JLabel lblLogin = new JLabel("Login:");
	        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
	        lblLogin.setBounds(399, 144, 91, 51);
	        add(lblLogin);
	        
	        JLabel lblUsuarioL = new JLabel("(Nombre) Usuario:");
	        lblUsuarioL.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblUsuarioL.setBounds(257, 229, 180, 51);
	        add(lblUsuarioL);
	        
	        JLabel lblContrasenaL = new JLabel("Contraseña:");
	        lblContrasenaL.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblContrasenaL.setBounds(317, 304, 127, 51);
	        add(lblContrasenaL);
	        
	        textUsuarioL = new JTextField();
	        textUsuarioL.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        textUsuarioL.setBounds(452, 235, 219, 44);
	        add(textUsuarioL);
	        textUsuarioL.setColumns(10);
	        
	        //En vez de un JTextField hemos usado JpasswordField lo cual deberia ocultar la contraseña con asteriscos
	        
	        textContrasenaL = new JPasswordField();
	        textContrasenaL.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        textContrasenaL.setColumns(10);
	        textContrasenaL.setBounds(452, 310, 219, 44);
	        add(textContrasenaL);
	        
	        //Boton iniciar sesion nos llevara a un validador 
	        
	        btnIniciarSesion = new JButton("Iniciar sesion");
	        btnIniciarSesion.setBackground(SystemColor.textText);
	        btnIniciarSesion.setForeground(tema);
	        btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        btnIniciarSesion.setBounds(315, 401, 354, 51);
	        add(btnIniciarSesion);
	        
	        //Label en el que usaremos un mouseClicked para ocultar parte del login y visualizar el registro
	        
	        JLabel lblNoCuenta = new JLabel("¿No tienes cuenta? Registrate aquí");
	        lblNoCuenta.setFont(new Font("Times New Roman", Font.BOLD, 15));
	        lblNoCuenta.setBounds(346, 463, 241, 29);
	        add(lblNoCuenta);
	        
	        btnRegistro = new JButton("Registrar");
	        btnRegistro.setBounds(426, 496, 89, 23);
	        add(btnRegistro);
        
        
        
    }


	public JTextField getTextUsuarioL() {
		return textUsuarioL;
	}


	public void setTextUsuarioL(JTextField textUsuarioL) {
		this.textUsuarioL = textUsuarioL;
	}

	public JPasswordField getTextContrasenaL() {
		return textContrasenaL;
	}


	public void setTextContrasenaL(JPasswordField textContrasenaL) {
		this.textContrasenaL = textContrasenaL;
	}


	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}


	public void setBtnIniciarSesion(JButton btnIniciarSesion) {
		this.btnIniciarSesion = btnIniciarSesion;
	}


	public JButton getBtnRegistro() {
		// TODO Auto-generated method stub
		return btnRegistro;
	}

	public void setBtnRegistro(JButton btnRegistro) {
		this.btnRegistro = btnRegistro;
	}


	
	
	
}
