package br.com.csouza.utils;

import br.com.csouza.annotations.PrimaryKeyAcronym;
import br.com.csouza.exceptions.WithoutPrimaryKey;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Classe responsável por permitir o acesso aos valores de
 * siglas presentes as classes que extendam do objeto "DatabaseObject".
 *
 * @author Caique Souza
 * @version 1.0
 */
public class PrimaryKeyManipulation {
    private Object object;

    public PrimaryKeyManipulation(Object object) {
        this.object = object;
    }

    /**
     * Método para obter a sigla da classe.
     * @return Sigla da classe.
     * @throws WithoutPrimaryKey Este erro ocorrerá caso a classe não possua a anotação configurada.
     */
    public String getValue() throws WithoutPrimaryKey {
        Class<?> objectClass = object.getClass();

        if (!objectClass.isAnnotationPresent(PrimaryKeyAcronym.class)) {
            throw new WithoutPrimaryKey("A classe não possui a definição de sigla");
        }

        return objectClass.getAnnotation(PrimaryKeyAcronym.class).value();
    }
}
