package org.qtx.eventos.exceptions;

import java.util.List;

public class ParticipacionException extends Exception {

	private static final long serialVersionUID = -5346717391354710334L;
	
	private final List<String> entradaIncorrecta;

	public List<String> getEntradaIncorrecta() {
		return entradaIncorrecta;
	}

	public ParticipacionException(List<String> entradaIncorrecta) {
		super();
		this.entradaIncorrecta = entradaIncorrecta;
	}

}
