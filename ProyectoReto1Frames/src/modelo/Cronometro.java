package modelo;

import javax.swing.JLabel;

public class Cronometro extends Thread {

	private JLabel lblTiempoCronometro;
	private JLabel lblTiempoEjercicio;
	private JLabel lblDescanso;

	private boolean enDescanso;
	private boolean corriendo;
	private int tiempoSerie; // Tiempo de la serie actual
	private int serieActual = 0; // Índice de la serie actual

	public Cronometro(JLabel lblTiempoCronometro, JLabel lblTiempoEjercicio, JLabel lblDescanso) {
		this.lblTiempoCronometro = lblTiempoCronometro;
		this.lblTiempoEjercicio = lblTiempoEjercicio;
		this.lblDescanso = lblDescanso;
		this.enDescanso = false;
		this.corriendo = false;
	}

	// Modificación para recibir el tiempo de la serie actual como un solo int
	public void setTiempoSerie(int tiempoSerie) {
		this.tiempoSerie = tiempoSerie;
	}

	public void iniciar() {
		corriendo = true;
		this.start();
	}

	public void detener() {
		corriendo = false;
	}

	public void reanudar() {
		corriendo = true;

	}

	public JLabel getLblTiempoCronometro() {
		return lblTiempoCronometro;
	}

	public void setLblTiempoCronometro(JLabel lblTiempoCronometro) {
		this.lblTiempoCronometro = lblTiempoCronometro;
	}

	public boolean isEnDescanso() {
		return enDescanso;
	}

	public void setEnDescanso(boolean enDescanso) {
		this.enDescanso = enDescanso;
	}

	@Override
	public void run() {
		// Ejecutar el cronómetro para la serie actual

		while ( serieActual < 1) { // Solo una serie por ahora
			ejecutarSerie(tiempoSerie);
			System.out.println("3");

			// Pausa de 5 segundos entre series (si no está detenido)
			if (corriendo) {
				cuentaRegresiva(lblDescanso, 5);
			}

			serieActual++; // Incrementar el índice de la serie
		}



	}

	private void ejecutarSerie(int tiempoSerie) {
		try {
			 
			 
			while ( tiempoSerie  >= 0) {
				if (corriendo) {
					int minutos = tiempoSerie / 60;
					int segundos = tiempoSerie % 60;
					String tiempo = String.format("%02d:%02d", minutos, segundos);
					Thread.sleep(1000);
					lblTiempoEjercicio.setText(tiempo);
					tiempoSerie--;
					System.out.println(tiempoSerie);
				}

				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void cuentaRegresiva(JLabel label, int segundosTotales) {

		try {
			while ( segundosTotales  >= 0) {
				if (corriendo) {

					int minutos = segundosTotales / 60;
					int segundos = segundosTotales % 60;
					String tiempo = String.format("%02d:%02d", minutos, segundos);
					label.setText(tiempo);
					segundos--;
				}
				Thread.sleep(500);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
