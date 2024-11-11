package modelo;

import java.io.Serializable;
import java.util.Date;

public class Historicos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private WorkOuts workout;
	private double tiempoTotal;
	private Date fechaEjercicio;
	private double porcentaje;
	
	/* private static final String collectionName = "Historicos";
	private static final String fieldNivel = "nivel";
	private static final String fieldVideo = "videoUrl";
	private static final String fieldTiempo = "tiempoEstimado"; */
	
	
	public WorkOuts getWorkout() {
		return workout;
	}
	public void setWorkout(WorkOuts workout) {
		this.workout = workout;
	}
	public double getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(double tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	public Date getFechaEjercicio() {
		return fechaEjercicio;
	}
	public void setFechaEjercicio(Date fechaEjercicio) {
		this.fechaEjercicio = fechaEjercicio;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	
}
