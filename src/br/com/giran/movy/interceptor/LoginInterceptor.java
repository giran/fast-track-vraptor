package br.com.giran.movy.interceptor;

import java.util.Arrays;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.giran.movy.component.UserSession;
import br.com.giran.movy.controller.LoginController;

public class LoginInterceptor implements Interceptor {

	private Result result;
	private UserSession userSession;
	
	public LoginInterceptor(Result result, UserSession userSession) {
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

		if (userSession.isLogged()) {
			stack.next(method, resource);
		} else {
			result.redirectTo(LoginController.class).login();
		}
	}

}