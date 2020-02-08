package org.qtx.eventos.entidades;

import javax.validation.constraints.NotEmpty;

public class Tarea {
	
	@NotEmpty(message = "El nombre de la tarea es requerido")
	private String nombre;
	@NotEmpty(message = "La descripcion de la tarea es requerido")
	private String descripcion;
	
	private boolean estatus; 
	
	private String comentario;
	
	

	public Tarea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tarea(String nombre, String descripcion, boolean estatus, String comentario) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.comentario = comentario;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
