package br.com.csouza.services;

import br.com.csouza.entity.DatabaseObject;
import br.com.csouza.interfaces.IGenericDAO;
import br.com.csouza.interfaces.IGenericService;

import java.util.Map;

public class GenericService <T extends DatabaseObject> implements IGenericService<T> {
    private final IGenericDAO<T> dao;

    public GenericService(IGenericDAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public T show(String id) {
        return this.dao.show(id);
    }

    @Override
    public Map<String, T> index() {
        return this.dao.index();
    }

    @Override
    public boolean store(T object) {
        return this.dao.store(object);
    }

    @Override
    public boolean update(String id, T object) {
        return this.dao.update(id, object);
    }

    @Override
    public boolean destroy(String id) {
        return this.dao.destroy(id);
    }

    @Override
    public boolean hasRegister(String id) {
        return this.dao.hasRegister(id);
    }
}
