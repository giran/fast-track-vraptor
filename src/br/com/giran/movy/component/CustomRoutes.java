package br.com.giran.movy.component;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.http.route.RoutesConfiguration;
import br.com.caelum.vraptor.http.route.Rules;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.HttpMethod;
import br.com.giran.movy.controller.FilmeController;
import br.com.giran.movy.controller.UsuarioController;

@Component
@ApplicationScoped
public class CustomRoutes implements RoutesConfiguration {

	public void config(Router router) {
		 new Rules(router) {
			public void routes() {
				routeFor("/movie").is(FilmeController.class).listagem();
				routeFor("/movie/{filme.titulo}").is(FilmeController.class).listar(null);
				
				routeFor("/c/{cor}")
					.with(HttpMethod.GET)
					.withPriority(Path.HIGHEST)
					.withParameter("cor").matching("[a-zA-Z0-9]{3}")
					.is(UsuarioController.class).alterarCor(null);
			}
		};
	}

}