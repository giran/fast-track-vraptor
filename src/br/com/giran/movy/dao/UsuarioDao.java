package br.com.giran.movy.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.giran.movy.model.Usuario;

@Component
public class UsuarioDao {

	private EntityManager manager;

	UsuarioDao(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario loadById(Long id) throws Exception {
		try {
			return manager.find(Usuario.class, id);
		} catch (Exception e) {
			throw new Exception("Não foi possível carregar o registro.", e);
		}
	}

	public Collection<Usuario> loadAll() throws Exception {
		try {
			Query query = manager.createQuery("from Usuario");

			@SuppressWarnings("unchecked")
			Collection<Usuario> list = query.getResultList();

			return list;
		} catch (Exception e) {
			throw new Exception("Não foi possível listar os registros.", e);
		}
	}

	public void remove(Usuario usuario) throws Exception {
		try {
			manager.remove(manager.find(Usuario.class, usuario.getId()));
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível remover o registro", e);
		}
	}

	public void save(Usuario usuario) throws Exception {
		try {
			manager.merge(usuario);
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível inserir o registro.", e);
		}
	}

}