package br.com.giran.movy.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.giran.movy.model.Artista;
import br.com.giran.movy.util.Util;

@Component
public class ArtistaDao {

	private EntityManager manager;

	ArtistaDao(EntityManager manager) {
		this.manager = manager;
	}

	public Collection<Artista> listByName(String nome) throws Exception {
		try {
			Query query = manager.createQuery("from Artista where nome like :nome");
			query.setParameter("nome", "%" + nome + "%");
			
			@SuppressWarnings("unchecked")
			Collection<Artista> list = query.getResultList();
			
			return list;
		} catch (Exception e) {
			throw new Exception("Não foi possível listar os registros por nome.", e);
		}
	}

	public Collection<Artista> loadAll() throws Exception {
		try {
			Query query = manager.createQuery("from Artista");
			
			@SuppressWarnings("unchecked")
			Collection<Artista> list = query.getResultList();
			
			return list;
		} catch (Exception e) {
			throw new Exception("Não foi possível listar os registros.", e);
		}
	}

	public Artista loadById(Long id) throws Exception {
		try {
			return manager.find(Artista.class, id);
		} catch (Exception e) {
			throw new Exception("Não foi possível carregar o registro.", e);
		}
	}

	public void remove(Artista artista) throws Exception {
		try {
			manager.remove(manager.find(Artista.class, artista.getId()));
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível remover o registro", e);
		}
	}

	public Artista removeImage(Artista artista) throws Exception {
		try {
			if (artista != null && artista.getId() != null && !artista.getImagem().equalsIgnoreCase("default.jpg")) {

				File file = new File(Artista.IMAGE_PATH, artista.getImagem());

				if (file.exists() && !file.delete()) {
					throw new Exception("Não foi possível apagar a imagem.");
				}

				artista.setImagem("default.jpg");

				this.updateImage(artista);
			}
		} catch (Exception e) {
			throw new Exception("Não foi possível apagar o registro da imagem.", e);
		}

		return artista; 
	}

	public void save(Artista artista) throws Exception {
		try {
			manager.merge(artista);
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível inserir o registro.", e);
		}
	}

	private void updateImage(Artista artista) throws Exception {
		try {
			Query query = manager.createQuery("update Artista set imagem = :imagem where id = :id");
			query.setParameter("imagem", artista.getImagem());
			query.setParameter("id", artista.getId());
			query.executeUpdate();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível salvar o registro da imagem.", e);
		}
	}

	public Artista uploadImage(UploadedFile file, Artista artista) throws Exception {
		final String oldName = artista.getImagem();

		try {
			String extensao = Util.recuperarExtensao(file.getFileName());

			if (!Util.isExtensaoValida(extensao)) {
				throw new Exception("Tipo de arquivo não permitido!\nPermitidos: JPG, JPEG, GIF, BMP e PNG.");
			}

			artista.setImagem(artista.getId() + extensao);

			File diretorio = new File(Artista.IMAGE_PATH);

			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}

			IOUtils.copy(file.getFile(), new FileOutputStream(new File(diretorio, artista.getImagem())));

			if (oldName != null) {
				Util.removerFotoDuplicada(file, Artista.IMAGE_PATH, oldName, artista.getImagem());
			}
			
			this.updateImage(artista);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Arquivo não encontrado!");
		} catch (SizeLimitExceededException e) {
			throw new Exception("Arquivo muito grande. Escolha um menor.");
		} catch (Exception e) {
			throw new Exception("Erro ao salvar a imagem", e);
		}
		
		return artista;
	}

}