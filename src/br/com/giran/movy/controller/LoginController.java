package br.com.giran.movy.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.giran.movy.component.UserSession;
import br.com.giran.movy.dao.LoginDao;
import br.com.giran.movy.model.Usuario;

@Resource
public class LoginController {

	private final Result result;
	private final LoginDao loginDao;
	private final UserSession userSession;

	LoginController(Result result, LoginDao loginDao, UserSession userSession) {
		this.result = result;
		this.loginDao = loginDao;
		this.userSession = userSession;
	}

	@Get
	@Path("/login")
	public void login() {
	}

	@Post
	@Path("/login")
	public void login(Usuario usuario) {
		try {
			Usuario user = loginDao.login(usuario.getEmail(), usuario.getSenha());

			userSession.setUser(user);

			result.redirectTo(IndexController.class).index();
		} catch (Exception e) {
			result.include("error", e.getMessage()).redirectTo(this).login();
		}
	}

	@Get
	@Path("/logout")
	public void logout() {
		userSession.setUser(null);
		result.redirectTo(this).login();
	}

}