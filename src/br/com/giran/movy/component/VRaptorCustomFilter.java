package br.com.giran.movy.component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.VRaptor;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class VRaptorCustomFilter extends VRaptor {

	private static final String SUFIX = "/servlet";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest baseRequest = (HttpServletRequest) request;

		String context = baseRequest.getContextPath();
		String URI = baseRequest.getRequestURI().substring(context.length());

		if (URI != null && URI.startsWith(SUFIX)) {
			chain.doFilter(baseRequest, response);
		} else {
			super.doFilter(request, response, chain);
		}

	}

}
