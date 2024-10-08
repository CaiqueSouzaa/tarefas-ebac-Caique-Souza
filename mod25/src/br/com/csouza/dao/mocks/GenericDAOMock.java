package br.com.csouza.dao.mocks;

import br.com.csouza.interfaces.IGenericDAO;
import br.com.csouza.entities.DatabaseObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDAOMock<T extends DatabaseObject> implements IGenericDAO<T> {

    private Map<String, List<T>> dao;

    public GenericDAOMock() {
        this.dao = new HashMap<>();
    }

    @Override
    public T show(Long id) {
        return null;
    }

    @Override
    public boolean store(T entity) {
        return false;
    }

    @Override
    public Collection<T> index() {
        return List.of();
    }

    @Override
    public boolean update(Long id, T entity) {
        return false;
    }

    @Override
    public boolean destroy(Long id) {
        return false;
    }
}
