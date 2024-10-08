package br.com.csouza.utils;

import br.com.csouza.annotations.TableName;
import br.com.csouza.exceptions.EntityWithoutTableNameException;

public class GetTableName {
    public static <T> String getTableName(T entity) throws EntityWithoutTableNameException {
        Class<?> className = entity.getClass();
        TableName tableName = className.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new EntityWithoutTableNameException("O objeto fornecido não possui o nome de tabela");
        }

        return tableName.value();
    }
}