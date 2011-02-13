package br.com.giran.movy.interceptor;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.InterceptorSequence;

@Intercepts
public class SequenceInterceptor implements InterceptorSequence {

	@SuppressWarnings("unchecked")
	public Class<? extends Interceptor>[] getSequence() {
		return new Class[] { LoginInterceptor.class, PermissaoInterceptor.class };
	}

}