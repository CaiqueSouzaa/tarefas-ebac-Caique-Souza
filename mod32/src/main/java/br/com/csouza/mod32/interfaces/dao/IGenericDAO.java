package br.com.csouza.mod32.interfaces.dao;

import java.util.Collection;

public interface IGenericDAO<T> {
	public T cadastrar(T entity);
	
	public T buscar(Long id);
	
	public Collection<T> buscarTodos();
	
	public T atualizar(T newEntityData);
	
	public Integer deletar(Long id);
}
