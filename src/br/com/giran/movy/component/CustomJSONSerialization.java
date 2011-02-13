package br.com.giran.movy.component;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;

@Component
public class CustomJSONSerialization extends XStreamJSONSerialization {

	private final HttpServletResponse response;

	public CustomJSONSerialization(HttpServletResponse response, TypeNameExtractor extractor, ProxyInitializer initializer) {
		super(response, extractor, initializer);
		this.response = response;
	}
	
	@Override
	public <T> Serializer from(T object) {
		response.setContentType("application/json; charset=UTF-8");
		return getSerializer().from(object);
	}

	@Override
	public <T> Serializer from(T object, String alias) {
		response.setContentType("application/json; charset=UTF-8");
		return getSerializer().from(object, alias);
	}

}