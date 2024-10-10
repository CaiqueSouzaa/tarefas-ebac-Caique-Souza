package br.com.csouza.utils;

import br.com.csouza.annotations.TableName;
import br.com.csouza.exceptions.EntityWithoutTableNameException;

/**
 * Classe responsável por permitir a manipulação da anotação "TableName".
 *
 * @author Caique Souza
 * @version 1.0
 */
public class GetTableName {
    /**
     * Método responsável por retornar o nome da tabela.
     * @param entity Entidade onde deve ser buscada pela anotação "TableName".
     * @return Nome da tabela.
     * @param <T> Tipo do objeto.
     * @throws EntityWithoutTableNameException Exeção lançada caso a entidade não possua os métodos necessários para manipulação no banco de dados.
     */
    public static <T> String getTableName(T entity) throws EntityWithoutTableNameException {
        Class<?> className = entity.getClass();
        TableName tableName = className.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new EntityWithoutTableNameException("O objeto fornecido não possui o nome de tabela");
        }

        return tableName.value();
    }

    /**
     * Método responsável por retornar o nome da tabela.
     * @param itemClass Entidade onde deve ser buscada pela anotação "TableName".
     * @return Nome da tabela.
     * @param <T> Tipo do objeto.
     * @throws EntityWithoutTableNameException Exeção lançada caso a entidade não possua os métodos necessários para manipulação no banco de dados.
     */
    public static <T> String getTableNameClass(Class<T> itemClass) throws EntityWithoutTableNameException {
        TableName tableName = itemClass.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new EntityWithoutTableNameException("O objeto fornecido não possui o nome de tabela");
        }

        return tableName.value();
    }
}