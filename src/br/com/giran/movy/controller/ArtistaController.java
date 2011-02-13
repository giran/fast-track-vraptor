package br.com.giran.movy.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.giran.movy.controller.common.GenericController;
import br.com.giran.movy.dao.ArtistaDao;
import br.com.giran.movy.model.Artista;

@Resource
public class ArtistaController extends GenericController {

	private final ArtistaDao artistaDao;
	private final Validator validator;

	ArtistaController(Result result, ArtistaDao artistaDao, Validator validator) {
		this.artistaDao = artistaDao;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/artista/imagem")
	public void atualizarImagem(UploadedFile file, Artista artista) {
		validator.onErrorForwardTo(this).exibir(artista);

		try {
			artistaDao.uploadImage(file, artista);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.redirectTo(this).exibir(artista);
	}

	@Put
	@Path("/artista")
	public void editar(Artista artista) {
		try {
			artista = artistaDao.loadById(artista.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("artista", artista).of(this).novo();
	}

	@Get
	@Path({"/artista/{artista.id}", "/artista/{artista.id}/*"})
	public Artista exibir(Artista artista) {
		try {
			return artistaDao.loadById(artista.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return artista;
	}
	
	@Get
	@Path(value = "/artista/{artista.id}/imagem", priority = Path.HIGH)
	public Download downloadImage(Artista artista) throws IOException {
		try {
			artista = artistaDao.loadById(artista.getId());
			
			if (artista == null) {
				throw new Exception("Imagem não encontrada.");
			}

			File file = new File(Artista.IMAGE_PATH, artista.getImagem());

			return new FileDownload(file, "image/jpg", artista.getNome().replaceAll(" ", "-") + ".jpg");
		} catch (Exception e) {
			e.printStackTrace();
			return new FileDownload(new File(Artista.IMAGE_PATH, "default.jpg"), "image/jpg", "default.jpg");
		}
	}

	@Get
	@Path("/artista")
	public void listagem() {

	}

	@Get
	@Path("/artista/pagina/{pagina}")
	public void listar(String key, int pagina) {
		try {
			Collection<Artista> artistas = artistaDao.listByName(key);

			result.use(json()).withoutRoot().from(artistas).serialize();
		} catch (Exception e) {
			serializeError(e);
		}
	}

	@Get
	@Path("/artista/novo")
	public void novo() {

	}

	@Delete
	@Path("/artista")
	public void remover(Artista artista) {
		try {
			artistaDao.remove(artista);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("message", "Artista removido com sucesso!").redirectTo(this).listagem();
	}

	@Delete
	@Path("/artista/{artista.id}/imagem")
	public void removerImagem(Artista artista) {
		try {
			artista = artistaDao.loadById(artista.getId());

			artistaDao.removeImage(artista);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.redirectTo(this).exibir(artista);
	}

	@Post
	@Path("/artista")
	public void save(Artista artista) {
		try {
			artistaDao.save(artista);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.include("message", "Salvo com sucesso!").redirectTo(this).listagem();
	}

}