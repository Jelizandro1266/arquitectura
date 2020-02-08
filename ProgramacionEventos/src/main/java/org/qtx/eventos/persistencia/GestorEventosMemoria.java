package org.qtx.eventos.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qtx.eventos.entidades.Eventos;
import org.qtx.eventos.entidades.Participacion;
import org.qtx.eventos.entidades.Tarea;
import org.qtx.eventos.exceptions.ParticipacionException;
import org.qtx.eventos.servicios.IGestorDatos;
import org.springframework.stereotype.Repository;

@Repository
public class GestorEventosMemoria implements IGestorDatos{

	private static Map<Integer, Eventos> mapaEventos = new HashMap<>();
	
	private static int id = 0;
	
	private static synchronized int getNuevoIdEvento() {
		return ++id;
	}
	
	@Override
	public void insertarEvento(Eventos evento) {
		evento.setId(getNuevoIdEvento());
		mapaEventos.put(evento.getId(), evento);
	}

	@Override
	public List<Eventos> getEventos() {
		return new ArrayList<Eventos>(mapaEventos.values());
	}

	
	
	
	public GestorEventosMemoria() {
		super();

		Participacion participacion = new Participacion();
		participacion.setIdPersona(1);
		participacion.setNombreCompleto("Luis Daniel Correa Dominguez");
		participacion.setRol("Staff");
		participacion.setTareas(Arrays.asList(new Tarea("Hielos", "Encargado de llevar los Hielos", false, null)));
		
		Participacion participacionTavo = new Participacion();
		participacionTavo.setIdPersona(2);
		participacionTavo.setNombreCompleto("Octavio Nonel");
		participacionTavo.setRol("Staff");
		participacionTavo.setTareas(Arrays.asList(new Tarea("Torres", "Encargado de llevar botellas de Torres 20", false, null)));
		
		List<Participacion> participaciones = new ArrayList<>();
		participaciones.add(participacion);
		participaciones.add(participacionTavo);
		
		Eventos evento = new Eventos();
		
		evento.setId(getNuevoIdEvento());
		evento.setDescripcion("Fiesta para celebrar cumpleaños de Bruno");
		evento.setNombre("Cumpleaños Bruno");
		evento.setParticipaciones(participaciones);
		
		mapaEventos.put(evento.getId(), evento);
		
		
	}

	@Override
	public Eventos getEventoPorId(int idEvento) {
		return mapaEventos.get(idEvento);
	}

	@Override
	public List<Participacion> getParticipacionesPorEvento(int idEvento) throws ParticipacionException {
		if(mapaEventos.get(idEvento) == null) {
			throw new ParticipacionException(Arrays.asList("El evento no existe")) ;
		}
		return mapaEventos.get(idEvento).getParticipaciones();
	}

	@Override
	public Participacion getParticipacionPorId(int idEvento, int idPersona) {
		
		if(mapaEventos.get(idEvento) == null) {
			return null;
		}
		
		return getParticipacion(idEvento, idPersona);
	}

	@Override
	public void insertarParticipacion(int idEvento, Participacion participacion) throws ParticipacionException {

		if(mapaEventos.get(idEvento) == null) {
			throw new ParticipacionException(Arrays.asList("El evento no existe"));
		}
		if(getParticipacion(idEvento, participacion.getIdPersona()) != null) {
			throw new ParticipacionException(Arrays.asList("La persona ya existe en este evento"));
		}		
		
		mapaEventos.get(idEvento).getParticipaciones().add(participacion);
		
	}
	
	private Participacion getParticipacion(int idEvento, int idPersona) {
		List<Participacion> participaciones = mapaEventos.get(idEvento).getParticipaciones();
		return participaciones.stream().filter(
				x -> x.getIdPersona() == idPersona)
				.findAny().orElse(null);
	}
	
	
}
