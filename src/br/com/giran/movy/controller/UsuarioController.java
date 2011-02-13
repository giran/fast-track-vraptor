package br.com.giran.movy.controller;

import java.util.Collection;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.giran.movy.annotation.Permissao;
import br.com.giran.movy.component.UserSession;
import br.com.giran.movy.dao.UsuarioDao;
import br.com.giran.movy.model.Usuario;
import br.com.giran.movy.model.common.TipoPerfil;

@Resource
public class UsuarioController {

	private final Result result;
	private final UsuarioDao usuarioDao;
	private final UserSession userSession;

	UsuarioController(Result result, UsuarioDao usuarioDao, UserSession userSession) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.userSession = userSession;
	}

	@Get
	@Path("/cor/{cor:[a-fA-F0-9]{6}}")
	public void alterarCor(String cor) {
		userSession.setCor(cor);

		result.redirectTo(IndexController.class).index();
	}

	@Put
	@Path("/usuario")
	public void editar(Usuario usuario) {
		try {
			usuario = usuarioDao.loadById(usuario.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("usuario", usuario).of(this).novo();
	}

	@Post
	@Path("/usuario")
	public void save(Usuario usuario) {
		try {
			usuarioDao.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("message", "Salvo com sucesso!").redirectTo(this).listagem();
	}

	@Get
	@Path("/usuario")
	public Collection<Usuario> listagem() {
		try {
			return usuarioDao.loadAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Get
	@Path("/usuario/novo")
	public void novo() {
	}

	@Delete
	@Path("/usuario")
	@Permissao(TipoPerfil.ADMINISTRADOR)
	public void remover(Usuario usuario) {
		try {
			usuarioDao.remove(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("message", "Usuário removido com sucesso!").redirectTo(this).listagem();
	}

	@Get
	@Path("/negado")
	public void negado() {

	}

}