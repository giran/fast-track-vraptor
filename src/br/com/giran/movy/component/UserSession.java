package br.com.giran.movy.component;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.giran.movy.model.Usuario;

@Component
@SessionScoped
public class UserSession implements java.io.Serializable {

	private static final long serialVersionUID = -228464043432854104L;

	private Usuario user;
	private String cor;

	public boolean isLogged() {
		return user != null;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

}