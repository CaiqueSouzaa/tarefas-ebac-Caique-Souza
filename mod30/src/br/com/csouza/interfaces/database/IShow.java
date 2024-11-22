package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

public interface IShow<T extends DatabaseEntity> {
    T show(Long id) throws Exception;
}
