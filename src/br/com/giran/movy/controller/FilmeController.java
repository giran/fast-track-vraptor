package br.com.giran.movy.controller;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Collection;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.giran.movy.dao.FilmeDao;
import br.com.giran.movy.model.Filme;
import br.com.giran.movy.util.Util;

@Resource
public class FilmeController {

	private final Result result;
	private final FilmeDao filmeDao;
	private final Validator validator;

	FilmeController(Result result, FilmeDao filmeDao, Validator validator) {
		this.filmeDao = filmeDao;
		this.result = result;
		this.validator = validator;
	}

	@Get
	@Path({"/filme/{filme.id}", "/filme/{filme.id}/*"})
	public Filme exibir(Filme filme) {
		try {
			return filmeDao.loadById(filme.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filme;
	}

	@Put
	@Path("/filme")
	public void editar(Filme filme) {
		try {
			filme = filmeDao.loadById(filme.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("filme", filme).of(this).novo();
	}

	@Post
	@Path("/filme")
	public void save(final Filme filme) {
		//validator.add(new ValidationMessage("Ano Inválido!", "ano"));

		validator.validate(filme);

		validator.checking(new Validations() {{
			if (that(filme, is(notNullValue()), "filme", "filmeNulo")) {
				that(filme.getAno(), lessThan(Util.getAnoAtual()), "ano", "deveSerMenorQue", i18n("ano"), String.valueOf(Util.getAnoAtual()));
			}
	    }});

		validator.onErrorUsePageOf(this).novo();

		try {
			filmeDao.save(filme);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("message", "Salvo com sucesso!").redirectTo(this).listagem();
	}

	@Get
	@Path("/filme")
	public Collection<Filme> listagem() {
		try {
			return filmeDao.loadAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Get
	@Path(value = "/filme/novo", priority = Path.HIGH)
	public void novo() {

	}

	@Get
	@Path("/filme/{filme.titulo}")
	public void listar(Filme filme) {
		try {
			String key = filme.getTitulo().replaceAll("%20", " ").replaceAll("\\+", " ");
			result.include("filmeList", filmeDao.listar(key));
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.of(this).listagem();
	}

	@Delete
	@Path("/filme")
	public void remover(Filme filme) {
		try {
			filmeDao.remove(filme);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("message", "Filme removido com sucesso!").redirectTo(this).listagem();
	}

}