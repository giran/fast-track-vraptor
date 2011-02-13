package br.com.giran.movy.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.giran.movy.model.Usuario;

@Component
public class LoginDao {

	private EntityManager manager;

	LoginDao(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario login(String email, String senha) throws Exception {
		try {
			Query query = manager.createQuery("from Usuario where email = :email and senha = :senha");
			query.setParameter("email", email);
			query.setParameter("senha", senha);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			throw new Exception("Usuário ou senha incorreta!", e);
		} catch (NonUniqueResultException e) {
			throw new Exception("Erro! Usuário duplicado.", e);
		} catch (Exception e) {
			throw new Exception("Não foi possível acessar o sistema!", e);
		}
	}

}