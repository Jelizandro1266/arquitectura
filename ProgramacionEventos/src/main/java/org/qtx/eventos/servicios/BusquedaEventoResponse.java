package org.qtx.eventos.servicios;

import org.qtx.eventos.entidades.Eventos;

public class BusquedaEventoResponse {
	
	private Eventos evento;
	
	private String entradasIncorrectas;

	public Eventos getEvento() {
		return evento;
	}

	public void setEvento(Eventos evento) {
		this.evento = evento;
	}

	public String getEntradasIncorrectas() {
		return entradasIncorrectas;
	}

	public void setEntradasIncorrectas(String entradasIncorrectas) {
		this.entradasIncorrectas = entradasIncorrectas;
	}

}
