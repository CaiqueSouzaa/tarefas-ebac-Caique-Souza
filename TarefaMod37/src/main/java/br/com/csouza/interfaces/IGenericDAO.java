package br.com.csouza.interfaces;

import java.util.Collection;

import br.com.csouza.domain.DatabaseEntity;

public interface IGenericDAO<T extends DatabaseEntity> {
	public T save(T entity);

	public T findById(Long id);

	public Collection<T> findAll();

	public T update(T entity);

	public void destroy(T entity);
}
