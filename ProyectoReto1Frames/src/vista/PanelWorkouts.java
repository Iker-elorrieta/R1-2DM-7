package vista;


import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import controlador.Controlador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PanelWorkouts extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNivelNumero;
	
	
	//El color hay que cambiarlo a la main
	//El tema 1 el el suave y el tema2 Es para el borde y para los botones
	Color tema =new Color(50, 120, 215);
	Color tema2 =new Color(112, 162, 199);
	int nivel =0; // Este nivel se debera recoger comprobando el numero de workouts
	String numeroNivel=String.valueOf(nivel);
    
	

	/**
	 * Create the panel.
	 */
	public PanelWorkouts() {

		
		setForeground(SystemColor.textHighlight);
		setBorder(new MatteBorder(5, 25, 5, 25, (tema)));
		setBackground(tema2);
	    setLayout(null);
	    
	    JButton btnModificarPerfil = new JButton("Perfil");
	    btnModificarPerfil.setForeground(tema);
	    btnModificarPerfil.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnModificarPerfil.setBackground(SystemColor.desktop);
	    btnModificarPerfil.setBounds(858, 11, 116, 51);
	    add(btnModificarPerfil);
	    
	    JButton btnHistorialWorkouts = new JButton("Historial Workouts");
	    btnHistorialWorkouts.setForeground(tema);
	    btnHistorialWorkouts.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnHistorialWorkouts.setBackground(SystemColor.desktop);
	    btnHistorialWorkouts.setBounds(736, 63, 238, 51);
	    add(btnHistorialWorkouts);
	    
	    JButton btnCerrarSesion = new JButton("Cerrar Sesion");
	    btnCerrarSesion.setForeground(tema);
	    btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnCerrarSesion.setBackground(SystemColor.desktop);
	    btnCerrarSesion.setBounds(786, 540, 188, 51);
	    add(btnCerrarSesion);
	    
	    JList list = new JList();
	    list.setBounds(250, 118, 300, 383);
	    add(list);
	    
	    JLabel lblWorkouts = new JLabel("WORKOUTS");
	    lblWorkouts.setFont(new Font("Tahoma", Font.BOLD, 25));
	    lblWorkouts.setBounds(311, 56, 188, 51);
	    add(lblWorkouts);
	    
	    JLabel lblNivel = new JLabel("Nivel de usuario:");
	    lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    lblNivel.setBounds(29, 11, 169, 51);
	    add(lblNivel);
	    
	    
	    
	    lblNivelNumero = new JLabel(numeroNivel);
	    lblNivelNumero.setForeground(Color.WHITE);
	    lblNivelNumero.setFont(new Font("Dialog", Font.ITALIC, 25));
	    lblNivelNumero.setBounds(192, 13, 67, 45);
	    add(lblNivelNumero);
	    
	    JButton btnIniciar = new JButton("Iniciar");
	    btnIniciar.setForeground(tema);
	    btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnIniciar.setBounds(325, 521, 174, 51);
	    add(btnIniciar);
	    
	    JButton btnNivel0 = new JButton("Nivel 0");
	    btnNivel0.setForeground(new Color(50, 120, 215));
	    btnNivel0.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnNivel0.setBounds(50, 129, 174, 51);
	    add(btnNivel0);
	    
	    JButton btnNivel1 = new JButton("Nivel 1");
	    btnNivel1.setForeground(new Color(50, 120, 215));
	    btnNivel1.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnNivel1.setBounds(50, 207, 174, 51);
	    add(btnNivel1);
	    
	    JButton btnNivel2 = new JButton("Nivel 2");
	    btnNivel2.setForeground(new Color(50, 120, 215));
	    btnNivel2.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnNivel2.setBounds(50, 281, 174, 51);
	    add(btnNivel2);
	    
	    JButton btnNivel3 = new JButton("Nivel 3");
	    btnNivel3.setForeground(new Color(50, 120, 215));
	    btnNivel3.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnNivel3.setBounds(50, 362, 174, 51);
	    add(btnNivel3);
	    
	    JButton btnNivel4 = new JButton("Nivel 4");
	    btnNivel4.setForeground(new Color(50, 120, 215));
	    btnNivel4.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnNivel4.setBounds(50, 437, 174, 51);
	    add(btnNivel4);
	    
	    JLabel lblNewLabel = new JLabel("Filtrar");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    lblNewLabel.setBounds(95, 88, 93, 26);
	    add(lblNewLabel);
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setBounds(566, 172, 391, 330);
	    add(textArea);
	    
	    JLabel lblDetalles = new JLabel("Detalles del workout:");
	    lblDetalles.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    lblDetalles.setBounds(570, 125, 216, 41);
	    add(lblDetalles);
	    
	

	}
}
