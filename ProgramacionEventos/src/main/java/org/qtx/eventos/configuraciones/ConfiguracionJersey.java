package org.qtx.eventos.configuraciones;

import org.glassfish.jersey.server.ResourceConfig;
import org.qtx.eventos.servicios.EventosRest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionJersey extends ResourceConfig {
	public ConfiguracionJersey() {
		register(EventosRest.class);
	}
}
