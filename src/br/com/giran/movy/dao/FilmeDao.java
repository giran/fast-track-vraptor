package br.com.giran.movy.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.giran.movy.model.Filme;

@Component
public class FilmeDao {

	private EntityManager manager;

	FilmeDao(EntityManager manager) {
		this.manager = manager;
	}

	public Collection<Filme> listar(String key) throws Exception {
		try {
			Query query = manager.createQuery("from Filme where titulo like :titulo or title like :title");
			query.setParameter("titulo", "%" + key + "%");
			query.setParameter("title", "%" + key + "%");

			@SuppressWarnings("unchecked")
			Collection<Filme> list = query.getResultList();

			return list;
		} catch (Exception e) {
			throw new Exception("Não foi possível listar os registros por título.", e);
		}
	}

	public Filme loadById(Long id) throws Exception {
		try {
			return manager.find(Filme.class, id);
		} catch (Exception e) {
			throw new Exception("Não foi possível carregar o registro.", e);
		}
	}

	public Collection<Filme> loadAll() throws Exception {
		try {
			Query query = manager.createQuery("from Filme");

			@SuppressWarnings("unchecked")
			Collection<Filme> list = query.getResultList();

			return list;
		} catch (Exception e) {
			throw new Exception("Não foi possível listar os registros.", e);
		}
	}

	public void remove(Filme filme) throws Exception {
		try {
			manager.remove(manager.find(Filme.class, filme.getId()));
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível remover o registro", e);
		}
	}

	public void save(Filme filme) throws Exception {
		try {
			manager.merge(filme);
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível inserir o registro.", e);
		}
	}

}