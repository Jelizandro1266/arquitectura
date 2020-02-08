package org.qtx.eventos.servicios;

import java.util.List;

public class InsertarEventoResponse {
	
	private int idEvento;
	
	private List<String> EntradasIncorrectas;

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public List<String> getEntradasIncorrectas() {
		return EntradasIncorrectas;
	}

	public void setEntradasIncorrectas(List<String> entradasIncorrectas) {
		EntradasIncorrectas = entradasIncorrectas;
	}

}
