package br.com.csouza.annotations;

import java.lang.annotation.*;

/**
 * Anotação responsável por informar o nome da tabela a qual a entidade pertence no banco de dados.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {
    public String value();
}
