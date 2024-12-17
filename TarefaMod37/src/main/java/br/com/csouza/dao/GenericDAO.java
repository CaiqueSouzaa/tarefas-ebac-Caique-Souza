package br.com.csouza.dao;

import java.util.Collection;
import java.util.List;

import br.com.csouza.database.DatabaseConnection;
import br.com.csouza.domain.DatabaseEntity;
import br.com.csouza.interfaces.IGenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public abstract class GenericDAO<T extends DatabaseEntity> implements IGenericDAO<T> {
	private final EntityManagerFactory entityManagerFactory;

	public GenericDAO(String persistenceUnitName) {
		this.entityManagerFactory = DatabaseConnection.getConnection(persistenceUnitName);
	}

	protected abstract Class<T> getEntityClass();

	@Override
	public T save(T entity) {
		final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			this.closeConnection(entityManager);
		}
	}

	@Override
	public T findById(Long id) {
		final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		T entity = entityManager.find(this.getEntityClass(), id);

		this.closeConnection(entityManager);

		return entity;
	}

	@Override
	public Collection<T> findAll() {
		final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		@SuppressWarnings("unchecked")
		List<T> itemsList = entityManager.createQuery("FROM " + this.getEntityClass().getName()).getResultList();

		this.closeConnection(entityManager);

		return itemsList;
	}

	@Override
	public T update(T entity) {
		final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			this.closeConnection(entityManager);
		}
	}

	@Override
	public void destroy(T entity) {
		final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			final T e = entityManager.find(this.getEntityClass(), entity.getId());
			entityManager.remove(e);
			entityManager.getTransaction().commit();
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
