package br.com.giran.movy.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import br.com.giran.movy.model.common.TipoPerfil;

@Retention(RetentionPolicy.RUNTIME)
public @interface Permissao {
	
	public TipoPerfil[] value();
	
}