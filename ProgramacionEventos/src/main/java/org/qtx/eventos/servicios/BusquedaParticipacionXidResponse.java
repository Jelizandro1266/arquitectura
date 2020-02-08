package org.qtx.eventos.servicios;

import java.util.List;

import org.qtx.eventos.entidades.Participacion;

public class BusquedaParticipacionXidResponse {
	
	private List<String> entradasIncorrectas;
	
	private Participacion participacion;

	public List<String> getEntradasIncorrectas() {
		return entradasIncorrectas;
	}

	public void setEntradasIncorrectas(List<String> entradasIncorrectas) {
		this.entradasIncorrectas = entradasIncorrectas;
	}

	public Participacion getParticipacion() {
		return participacion;
	}

	public void setParticipacion(Participacion participacion) {
		this.participacion = participacion;
	}


}
