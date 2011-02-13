package br.com.giran.movy.interceptor;

import java.util.Arrays;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.giran.movy.annotation.Permissao;
import br.com.giran.movy.component.UserSession;
import br.com.giran.movy.controller.LoginController;
import br.com.giran.movy.controller.UsuarioController;
import br.com.giran.movy.model.Usuario;
import br.com.giran.movy.model.common.TipoPerfil;

public class PermissaoInterceptor implements Interceptor {

	private Result result;
	private UserSession userSession;
	
	public PermissaoInterceptor(Result result, UserSession userSession) {
		this.result = result;
		this.userSession = userSession;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean accepts(ResourceMethod method) {
		return !Arrays.asList(LoginController.class).contains(method.getMethod().getDeclaringClass());
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resource) {
		
		Permissao controllerList = method.getResource().getType().getAnnotation(Permissao.class);
		Permissao metodoList = method.getMethod().getAnnotation(Permissao.class);

		if (this.isAcesso(method, metodoList) && this.isAcesso(method, controllerList)) {
			stack.next(method, resource);
		} else {
			result.redirectTo(UsuarioController.class).negado();
		}
	}

	private boolean isAcesso(ResourceMethod method, Permissao permissaoList) {
		Usuario user = userSession.getUser();

		if (permissaoList != null) { // Com permissão anotada. Verificar...
			for (TipoPerfil perfil : permissaoList.value()) {
				if (perfil.equals(user.getPerfil())) {
					return true;
				}
			}		
		} else { // Sem permissão anotada. Continue...
			return true;
		}
		
		return false;
	}

}