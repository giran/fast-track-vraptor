package br.com.giran.movy.controller.common;

import static br.com.caelum.vraptor.view.Results.json;
import br.com.caelum.vraptor.Result;

public abstract class GenericController {

	protected Result result;

	protected void serialize(Object obj) {
		result.use(json()).withoutRoot().from(obj).serialize();
	}

	protected void serializeError(Exception e) {
		e.printStackTrace();
		this.serialize(e.getMessage());
	}

}