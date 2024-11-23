package br.com.csouza.interfaces.database;

import br.com.csouza.entities.DatabaseEntity;

/**
 * @author Caique Souza
 * @version 1.0
 * @param <T>
 */
public interface IShow<T extends DatabaseEntity> {
    /**
     * Método para obter um uníco registro com base no ID.
     * @param id ID de registro a ser buscado.
     * @return Objeto com as inforamações do registro.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    T show(Long id) throws Exception;
}
