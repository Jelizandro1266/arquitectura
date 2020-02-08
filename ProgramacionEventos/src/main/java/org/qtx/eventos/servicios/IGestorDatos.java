package org.qtx.eventos.servicios;

import java.util.List;

import org.qtx.eventos.entidades.Eventos;
import org.qtx.eventos.entidades.Participacion;
import org.qtx.eventos.exceptions.ParticipacionException;

public interface IGestorDatos {
	
	public void insertarEvento(Eventos eventos);
	public List<Eventos> getEventos();
	public Eventos getEventoPorId(int idEvento);
	
	
	public List<Participacion> getParticipacionesPorEvento(int idEvento)throws ParticipacionException ;
	public Participacion getParticipacionPorId(int idEvento, int idPersona) ;
	public void insertarParticipacion(int idEvento, Participacion participacion) throws ParticipacionException;
}
