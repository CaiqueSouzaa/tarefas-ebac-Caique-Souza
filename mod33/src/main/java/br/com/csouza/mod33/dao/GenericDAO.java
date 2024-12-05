package br.com.csouza.mod33.dao;

import br.com.csouza.mod33.interfaces.dao.IGenericDAO;
import br.com.csouza.mod33.jdbc.PostgreSQL;
import jakarta.persistence.EntityManager;

import java.util.Collection;

/**
 * DAO generica para controle das entidades.
 * 
 * @author Caique Souza
 */
public abstract class GenericDAO<T> implements IGenericDAO<T> {
    /**
     * Método responsável por obter a ".class" das entidades.
     * @return .class da entidade.
     */
    protected abstract Class<T> getEntityClass();

    @Override
    public T create(T entity) {
        final EntityManager entityManager = PostgreSQL.getConnection();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();

            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            PostgreSQL.closeConnection(entityManager);
        }
    }

    @Override
    public T getById(Long id) {
        try (final EntityManager entityManager = PostgreSQL.getConnection()) {
            return entityManager.find(this.getEntityClass(), id);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<T> getAll() {
        try (final EntityManager entityManager = PostgreSQL.getConnection()) {
            return entityManager.createQuery("FROM " + this.getEntityClass().getName()).getResultList();
        }
    }

    @Override
    public T update(T entity) {
        final EntityManager entityManager = PostgreSQL.getConnection();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();

            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            PostgreSQL.closeConnection(entityManager);
        }
    }

    @Override
    public Boolean delete(Long id) {
        final EntityManager entityManager = PostgreSQL.getConnection();

        try {
            entityManager.getTransaction().begin();
            final T e = entityManager.find(this.getEntityClass(), id);
            entityManager.remove(e);
            entityManager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            PostgreSQL.closeConnection(entityManager);
        }
    }
}
