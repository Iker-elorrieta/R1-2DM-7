package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import modelo.Cronometro;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JTextArea;

public class PanelEjercicio extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel lblNombreEjer, lblNombreWorkout, lblDescripcionEjercicioWorkouts;
    private JButton btnSalir, btnIniciar;
    private JLabel lblTiempoCronometro, lblTiempoCronometro_1, lblTiempoCronometro_2, lblImagen;
    private Cronometro cronometro;
    private int contBoton=0;
    Color tema = new Color(50, 120, 215);
    Color tema2 = new Color(112, 162, 199);
    private JTextArea txtSeries;

    /**
     * Create the panel.
     */
    public PanelEjercicio() {
        setForeground(SystemColor.textHighlight);
        setBorder(new MatteBorder(5, 25, 5, 25, (tema)));
        setBackground(tema2);
        setLayout(null);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnSalir.setBackground(Color.RED);
        btnSalir.setForeground(new Color(147, 0, 0));
        btnSalir.setBounds(654, 512, 131, 41);
        add(btnSalir);

        btnIniciar = new JButton("Iniciar");
        btnIniciar.setForeground(new Color(0, 128, 0));
        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnIniciar.setBackground(new Color(0, 255, 0));
        btnIniciar.setBounds(297, 512, 131, 41);
        add(btnIniciar);

        Panel panel = new Panel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(242, 158, 656, 337);
        add(panel);
        panel.setLayout(null);
        
        txtSeries = new JTextArea();
        txtSeries.setBounds(10, 11, 621, 315);
        panel.add(txtSeries);

        Panel panel_1 = new Panel();
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBounds(31, 23, 205, 472);
        add(panel_1);
        panel_1.setLayout(null);

        JLabel lblCronometro = new JLabel("Cronómetro:");
        lblCronometro.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCronometro.setBounds(10, 169, 112, 44);
        panel_1.add(lblCronometro);

        lblTiempoCronometro = new JLabel("00:00");
        lblTiempoCronometro.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblTiempoCronometro.setBounds(20, 213, 112, 44);
        panel_1.add(lblTiempoCronometro);

        JLabel lblTiempoDelEjercicio = new JLabel("Tiempo Del Ejercicio:\r\n");
        lblTiempoDelEjercicio.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblTiempoDelEjercicio.setBounds(10, 277, 197, 44);
        panel_1.add(lblTiempoDelEjercicio);

        lblTiempoCronometro_1 = new JLabel("00:00");
        lblTiempoCronometro_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblTiempoCronometro_1.setBounds(20, 321, 112, 44);
        panel_1.add(lblTiempoCronometro_1);

        JLabel lblDescanso = new JLabel("Descanso de un minuto:");
        lblDescanso.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblDescanso.setBounds(10, 376, 224, 44);
        panel_1.add(lblDescanso);

        lblTiempoCronometro_2 = new JLabel("01:00");
        lblTiempoCronometro_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblTiempoCronometro_2.setBounds(20, 417, 112, 44);
        panel_1.add(lblTiempoCronometro_2);

        Panel panel_2 = new Panel();
        panel_2.setBackground(SystemColor.menu);
        panel_2.setBounds(242, 23, 656, 129);
        add(panel_2);
        panel_2.setLayout(null);

        lblNombreEjer = new JLabel("Nombre Ejercicio\r\n");
        lblNombreEjer.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNombreEjer.setBounds(369, 16, 224, 44);
        panel_2.add(lblNombreEjer);

        lblDescripcionEjercicioWorkouts = new JLabel("Descripción Ejercicio \r\nWorkouts");
        lblDescripcionEjercicioWorkouts.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDescripcionEjercicioWorkouts.setBounds(96, 71, 523, 44);
        panel_2.add(lblDescripcionEjercicioWorkouts);

        lblNombreWorkout = new JLabel("Nombre WorkOut\r\n");
        lblNombreWorkout.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNombreWorkout.setBounds(96, 16, 235, 44);
        panel_2.add(lblNombreWorkout);

        // Crear la instancia de Cronometro y asignar los JLabel
        cronometro = new Cronometro(lblTiempoCronometro, lblTiempoCronometro_1, lblTiempoCronometro_2);
        
        lblImagen = new JLabel(new ImageIcon("img/crono.png"));
        lblImagen.setBounds(22, 11, 156, 147);
        panel_1.add(lblImagen);

        
        JButton btnPausar = new JButton("Pausar");
        btnPausar.setForeground(new Color(0, 128, 0));
        btnPausar.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnPausar.setBackground(Color.GREEN);
        btnPausar.setVisible(false);
        add(btnPausar);

        // Acción del botón Iniciar para iniciar el cronómetro
        btnIniciar.addActionListener(e -> {
            
        	cronometro.iniciar();
           
        	contBoton++;
            if(contBoton==1) {
        	   
        	    btnIniciar.setVisible(false);
        	    btnPausar.setVisible(true);
        	    btnPausar.setBounds(297, 512, 143, 41);
            }
            
        });

        // Acción del botón Salir para detener el cronómetro
        btnSalir.addActionListener(e -> {
            cronometro.detener();
        });
        
        btnPausar.addActionListener(e ->{
        	
        	if(btnPausar.getText().equals("Pausar")) {
        		
        		cronometro.detener();
        		btnPausar.setText("Reanudar");
        		
        	} else {
        		
        		cronometro.reanudar();
        		btnPausar.setText("Pausar");
        	}
        	
        	
        });
    }

    public JLabel getLblNombreEjer() {
        return lblNombreEjer;
    }

    public void setLblNombreEjer(JLabel lblNombreEjer) {
        this.lblNombreEjer = lblNombreEjer;
    }

    public JLabel getLblNombreWorkout() {
        return lblNombreWorkout;
    }

    public void setLblNombreWorkout(JLabel lblNombreWorkout) {
        this.lblNombreWorkout = lblNombreWorkout;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public void setBtnIniciar(JButton btnIniciar) {
        this.btnIniciar = btnIniciar;
    }

    public JLabel getLblDescripcionEjercicioWorkouts() {
        return lblDescripcionEjercicioWorkouts;
    }

    public void setLblDescripcionEjercicioWorkouts(JLabel lblDescripcionEjercicioWorkouts) {
        this.lblDescripcionEjercicioWorkouts = lblDescripcionEjercicioWorkouts;
    }

	public Cronometro getCronometro() {
		return cronometro;
	}

	public void setCronometro(Cronometro cronometro) {
		this.cronometro = cronometro;
	}

	public JTextArea getTxtSeries() {
		return txtSeries;
	}

	public void setTxtSeries(JTextArea txtSeries) {
		this.txtSeries = txtSeries;
	}
	
}
