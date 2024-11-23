package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

/**
 * @author Caique Souza
 * @version 1.0
 * @param <T>
 */
public interface IStore<T extends DatabaseEntity> {
    /**
     * Método responsável por realizar novos registros.
     * @param entity Entidade a ser registrada.
     * @return Inteiro informando o número de registros.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    Integer store(T entity) throws Exception;
}
