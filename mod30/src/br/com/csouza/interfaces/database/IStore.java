package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

public interface IStore<T extends DatabaseEntity> {
    Integer store(T entity) throws Exception;
}
