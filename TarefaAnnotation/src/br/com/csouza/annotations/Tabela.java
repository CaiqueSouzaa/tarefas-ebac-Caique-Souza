package br.com.csouza.annotations;

import java.lang.annotation.*;

/**
 * Anotação para tabelas
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Tabela {
    /**
     * Método para definir nome da tabela.
     * @return Nome da tabela
     */
    String value();
}
