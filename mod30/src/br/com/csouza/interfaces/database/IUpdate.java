package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

public interface IUpdate<T extends DatabaseEntity> {
    Integer update(T entity) throws Exception;
}
