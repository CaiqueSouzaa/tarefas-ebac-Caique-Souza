package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

import java.util.Collection;

/**
 * @author Caique Souza
 * @version 1.0
 * @param <T>
 */
public interface IIndex<T extends DatabaseEntity> {
    /**
     * Método para obter todos os registros.
     * @return Collection de registros.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    Collection<T> index() throws Exception;
}
