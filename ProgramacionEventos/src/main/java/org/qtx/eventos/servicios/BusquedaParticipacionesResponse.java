package org.qtx.eventos.servicios;

import java.util.List;

import org.qtx.eventos.entidades.Participacion;

public class BusquedaParticipacionesResponse {
	
	private String entradasIncorrectas;
	
	private List<Participacion> participaciones;

	

	public List<Participacion> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<Participacion> participaciones) {
		this.participaciones = participaciones;
	}

	public String getEntradasIncorrectas() {
		return entradasIncorrectas;
	}

	public void setEntradasIncorrectas(String entradasIncorrectas) {
		this.entradasIncorrectas = entradasIncorrectas;
	}


}
