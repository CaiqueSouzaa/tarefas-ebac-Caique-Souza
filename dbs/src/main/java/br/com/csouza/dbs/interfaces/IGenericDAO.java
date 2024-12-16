package br.com.csouza.dbs.interfaces;

import java.util.Collection;

import br.com.csouza.dbs.domain.DatabaseEntity;

public interface IGenericDAO<T extends DatabaseEntity> {
	public T save(T entity);
	
	public T findById(Long id);
	
	public Collection<T> findAll();
	
	public T update(T entity);
	
	public void destroy(T entity);
}
