package org.qtx.eventos.exceptions;

import java.util.List;

public class ValidadorException extends Exception{
	
	private static final long serialVersionUID = 4992340928957550193L;

	public ValidadorException(List<String> entradasIncorrectas) {
		super();
		this.entradasIncorrectas = entradasIncorrectas;
	}

	private List<String> entradasIncorrectas;

	public List<String> getEntradasIncorrectas() {
		return entradasIncorrectas;
	}

	public void setEntradasIncorrectas(List<String> entradasIncorrectas) {
		this.entradasIncorrectas = entradasIncorrectas;
	}

}
