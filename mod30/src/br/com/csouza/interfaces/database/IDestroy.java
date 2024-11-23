package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

/**
 * @author Caique Souza
 * @version 1.0
 * @param <T>
 */
public interface IDestroy<T extends DatabaseEntity> {
    /**
     * Método responsável por excluir um registro.
     * @param entity Objeto, contendo o ID de registro, a ser excluído.
     * @return Inteiro informando o número de registros excluídos.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    Integer destroy(T entity) throws Exception;
}
