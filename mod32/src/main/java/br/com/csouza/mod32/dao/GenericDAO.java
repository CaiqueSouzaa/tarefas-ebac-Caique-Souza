package br.com.csouza.mod32.dao;

import java.util.Collection;
import java.util.List;

import br.com.csouza.mod32.interfaces.dao.IGenericDAO;
import br.com.csouza.mod32.jdbc.PostgreSQLConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class GenericDAO<T> implements IGenericDAO<T> {
	protected abstract Class<T> getEntityClass();

	@Override
	public T cadastrar(T entity) {
		final EntityManager entityManager = PostgreSQLConnection.getConnection().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			this.closeConnection(entityManager);
		}

		return entity;
	}

	@Override
	public T buscar(Long id) {
		final EntityManager entityManager = PostgreSQLConnection.getConnection().createEntityManager();

		final T entity = entityManager.find(this.getEntityClass(), id);

		this.closeConnection(entityManager);

		return entity;
	}

	@Override
	public Collection<T> buscarTodos() {
		final EntityManager entityManager = PostgreSQLConnection.getConnection().createEntityManager();

		List<T> list = entityManager.createQuery("FROM " + this.getEntityClass().getName()).getResultList();

		this.closeConnection(entityManager);
		return list;
	}

	@Override
	public T atualizar(T newEntityData) {
		final EntityManager entityManager = PostgreSQLConnection.getConnection().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(newEntityData);
			entityManager.getTransaction().commit();

			return newEntityData;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			this.closeConnection(entityManager);
		}
	}

	@Override
	public Integer deletar(Long id) {
		final EntityManager entityManager = PostgreSQLConnection.getConnection().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			final T entity = entityManager.find(this.getEntityClass(), id);
			entityManager.remove(entity);
			entityManager.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			this.closeConnection(entityManager);
		}
	}
	
	protected void closeConnection(EntityManager entityManager) {
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
	}

}
