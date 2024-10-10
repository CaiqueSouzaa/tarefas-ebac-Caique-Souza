package br.com.csouza.exceptions;

public class WithoutDatabaseMethodsException extends Exception {
    public WithoutDatabaseMethodsException(String message) {
        super(message);
    }
}
