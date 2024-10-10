package br.com.csouza.services;

import br.com.csouza.entities.DatabaseObject;
import br.com.csouza.interfaces.IGenericDAO;
import br.com.csouza.interfaces.IGenericService;

import java.util.Collection;

/**
 * Classe abstrata responsável por permitir a criação de serviços para diferentes entidades/objetos.
 * @param <T> Entity - Objeto que será gerenciado pelo serviço.
 *
 * @author Caique Souza
 * @version 1.0
 */
public abstract class GenericService <T extends DatabaseObject> implements IGenericService<T> {
    private final IGenericDAO<T> dao;

    /**
     * Método construtor.
     * Necessário informar a base de dados que será manipulada pelo serviço.
     * @param dao Base de dados a ser manipulada.
     */
    public GenericService(IGenericDAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public T show(Long id) throws Exception {
        return dao.show(id);
    }

    @Override
    public boolean store(T entity) throws Exception {
        return dao.store(entity);
    }

    @Override
    public Collection<T> index() throws Exception {
        return dao.index();
    }

    @Override
    public boolean update(Long id, T entity) throws Exception {
        return dao.update(id, entity);
    }

    @Override
    public boolean destroy(Long id) throws Exception {
        return dao.destroy(id);
    }
}
