package br.com.csouza.exceptions;

public class EntityWithoutTableNameException extends Exception {
    public EntityWithoutTableNameException(String message) {
        super(message);
    }
}
