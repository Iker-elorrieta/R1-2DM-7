package vista;

import javax.swing.JPanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;


public class PanelBienvenida extends JPanel {

	private static final long serialVersionUID = 1L;
	private String ruta="C:\\Users\\in2dm3-a\\eclipse-workspace\\ProyectoAFrames.zip_expanded\\ProyectoReto1Frames\\src\\imagenes\\bienvenidaGYM.jpg";
	private JButton btnComenzar;

	/**
	 * Create the panel.
	 */
	public PanelBienvenida() {
		setLayout(null);
		
		btnComenzar = new JButton("Comenzar");
	    btnComenzar.setFont(new Font("Tahoma", Font.PLAIN, 21));
	    btnComenzar.setBounds(428, 422, 129, 40);
	    add(btnComenzar);
		
	    JLabel lblImagen = new JLabel();
		lblImagen.setBounds(0, 0, 980, 589); 
	    ImageIcon iconoOriginal = new ImageIcon(ruta);
	    Image imagen = iconoOriginal.getImage();
	    Image imagenRedimensionada = imagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
	    lblImagen.setIcon(iconoRedimensionado);
	    add(lblImagen);
	    
	  
		
		
	}

	public JButton getBtnComenzar() {
		return btnComenzar;
	}

	public void setBtnComenzar(JButton btnComenzar) {
		this.btnComenzar = btnComenzar;
	}

	

	
	
}
