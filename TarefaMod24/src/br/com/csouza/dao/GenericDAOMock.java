package br.com.csouza.dao;

import br.com.csouza.entity.DatabaseObject;
import br.com.csouza.exceptions.WithoutPrimaryKey;
import br.com.csouza.interfaces.IGenericDAO;
import br.com.csouza.utils.PrimaryKeyManipulation;

import java.util.HashMap;
import java.util.Map;

public class GenericDAOMock<T extends DatabaseObject> implements IGenericDAO <T> {
    private final Map<String, T> dao;

    public GenericDAOMock() {
        this.dao = new HashMap<>();
    }

    @Override
    public T show(String id) {
        return this.dao.get(id);
    }

    @Override
    public Map<String, T> index() {
        return dao;
    }

    @Override
    public boolean store(T object) {
        if (object.getId() != null && this.hasRegister(object.getId())) {
            return false;
        }

        String acronym = "";
        final PrimaryKeyManipulation keyManipulation = new PrimaryKeyManipulation(object);

        try {
            acronym = keyManipulation.getValue();
        } catch (WithoutPrimaryKey err) {
            System.out.println(err.getMessage());
        }


        object.setId(acronym + "-" + (this.dao.size() + 1));
        this.dao.put(object.getId(), object);
        return true;
    }

    @Override
    public boolean update(String id, T object) {
        if (!this.hasRegister(id)) return false;

        object.setId(id);
        this.dao.replace(id, object);
        return true;
    }

    @Override
    public boolean destroy(String id) {
        if (!this.hasRegister(id)) return false;

        this.dao.remove(id);
        return true;
    }

    @Override
    public boolean hasRegister(String id) {
        return this.dao.containsKey(id);
    }
}
