package br.com.csouza.annotations;

import java.lang.annotation.*;

/**
 * Anotação responsável por informar qual será a chave primaria do objeto.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PrimaryKeyAcronym {
    public String value();
}
