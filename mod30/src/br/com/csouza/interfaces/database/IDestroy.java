package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

public interface IDestroy<T extends DatabaseEntity> {
    Integer destroy(T entity) throws Exception;
}
