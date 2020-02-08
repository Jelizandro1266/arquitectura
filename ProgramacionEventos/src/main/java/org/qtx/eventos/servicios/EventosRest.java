package org.qtx.eventos.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.qtx.eventos.entidades.Eventos;
import org.qtx.eventos.entidades.Participacion;
import org.qtx.eventos.exceptions.ParticipacionException;
import org.qtx.eventos.exceptions.ValidadorException;
import org.qtx.eventos.utilerias.ValidadorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/")
public class EventosRest {
	
	@Autowired
	private IGestorDatos gestorDatos;
	
	@Autowired
	private ValidadorRequest validadorRequest;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public InsertarEventoResponse setEvento(Eventos eventos) {
		System.out.println("we si jalo");
		InsertarEventoResponse eventoResponse = new InsertarEventoResponse();
		try {
			validadorRequest.validarPojo(eventos);
		} catch (ValidadorException e) {
			eventoResponse.setEntradasIncorrectas(e.getEntradasIncorrectas());
			return eventoResponse;
		}
		
		gestorDatos.insertarEvento(eventos);
		
		eventoResponse.setIdEvento(eventos.getId());
		
		return eventoResponse;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Eventos> getEventos() {
		
		return gestorDatos.getEventos();
		
	}
	
	@Path("{idEvento}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BusquedaEventoResponse getEventoPorId(
			@PathParam("idEvento")
			int idEvento) {
		BusquedaEventoResponse busquedaEventoResponse = new BusquedaEventoResponse();
		if(idEvento <= 0) {
			busquedaEventoResponse.setEntradasIncorrectas("El numero del evento es invalido");
			return busquedaEventoResponse;
		}
			
		
		busquedaEventoResponse.setEvento(gestorDatos.getEventoPorId(idEvento));
		return busquedaEventoResponse;
	}
	
	@Path("{idEvento}/participaciones")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BusquedaParticipacionesResponse getParticipacionesPorEvento(
			@PathParam("idEvento")
			int idEvento) {
		BusquedaParticipacionesResponse busquedaParticipacionesResponse = new BusquedaParticipacionesResponse();
		if(idEvento <= 0) {
			busquedaParticipacionesResponse.setEntradasIncorrectas("El numero del evento es invalido");
			return busquedaParticipacionesResponse;
		}
		
		try {
			busquedaParticipacionesResponse.setParticipaciones(gestorDatos.getParticipacionesPorEvento(idEvento));
		} catch (ParticipacionException e) {
			busquedaParticipacionesResponse.setEntradasIncorrectas(e.getEntradaIncorrecta().get(0));
		}
		return busquedaParticipacionesResponse;
	}
	
	@Path("{idEvento}/participaciones/{idPersona}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BusquedaParticipacionXidResponse getParticipacionPorId(
			@PathParam("idEvento")
			int idEvento,
			@PathParam("idPersona")
			int idPersona
			) {
		BusquedaParticipacionXidResponse busquedaParticipacionesResponse = new BusquedaParticipacionXidResponse();
		
		List<String> fallos = validarEventoParticipacion(idEvento, idPersona);
		
		if(!fallos.isEmpty()) {
			busquedaParticipacionesResponse.setEntradasIncorrectas(fallos);
			return busquedaParticipacionesResponse;
		}
		
		busquedaParticipacionesResponse.setParticipacion(gestorDatos.getParticipacionPorId(idEvento, idPersona));
		return busquedaParticipacionesResponse;
	}
	
	
	@Path("{idEvento}/participaciones")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public InsertarParticipacionResponse setParticipacionPorEvento(
			@PathParam("idEvento")
			int idEvento,
			Participacion participacion
			) {
		InsertarParticipacionResponse insertarParticipacionResponse = new InsertarParticipacionResponse();
		List<String> fallos = new ArrayList<>();
		if(idEvento <= 0)
			fallos.add("El numero del evento es invalido");
		
		try {
			validadorRequest.validarPojo(participacion);
		} catch (ValidadorException e) {
			fallos.addAll(e.getEntradasIncorrectas());
		}
		
		if(!fallos.isEmpty()) {
			insertarParticipacionResponse.setEntradasIncorrectas(fallos);
			return insertarParticipacionResponse;
		}
		
		try {
			gestorDatos.insertarParticipacion(idEvento, participacion);
		} catch (ParticipacionException e) {
			insertarParticipacionResponse.setEntradasIncorrectas(e.getEntradaIncorrecta());
		}
		
		
		return insertarParticipacionResponse;
	}
	
	
	
	private List<String> validarEventoParticipacion(int idEvento, int idPersona){
		List<String> fallosEntrada = new ArrayList<>();
		if(idEvento < 1) {
			fallosEntrada.add("El numero del evento es invalido");
		}
		if(idPersona<1) {
			fallosEntrada.add("El numero de la persona es invalido");
		}
		return fallosEntrada;
	}
	
	
}
