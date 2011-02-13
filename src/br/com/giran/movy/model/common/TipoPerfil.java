package br.com.giran.movy.model.common;

import java.util.ArrayList;
import java.util.Collection;

public enum TipoPerfil {

	MEMBRO("Membro"), MODERADOR("Moderador"), ADMINISTRADOR("Administrador");

	private String label;

	private TipoPerfil(String label) {
		this.label = label;
	}

	public static Collection<TipoPerfil> loadAll() {
		Collection<TipoPerfil> perfis = new ArrayList<TipoPerfil>();

		for (TipoPerfil perfil : values()) {
			perfis.add(perfil);
		}
		return perfis;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}