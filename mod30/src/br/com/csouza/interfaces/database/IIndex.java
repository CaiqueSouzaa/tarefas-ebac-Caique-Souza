package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

import java.util.Collection;

public interface IIndex<T extends DatabaseEntity> {
    Collection<T> index() throws Exception;
}
