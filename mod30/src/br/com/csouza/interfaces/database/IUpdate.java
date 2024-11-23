package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

/**
 * @author Caique Souza
 * @version 1.0
 * @param <T>
 */
public interface IUpdate<T extends DatabaseEntity> {
    /**
     * Método responsável por atualizar um registro.
     * @param entity Novas informações, contendo o ID do registro, a serem atualizadas.
     * @return Inteiro informando o número de registros atualizados.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    Integer update(T entity) throws Exception;
}
