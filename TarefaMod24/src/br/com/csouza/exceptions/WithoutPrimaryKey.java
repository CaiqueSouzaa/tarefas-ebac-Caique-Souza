package br.com.csouza.exceptions;

public class WithoutPrimaryKey extends Exception {
    public WithoutPrimaryKey(String message) {
        super(message);
    }
}
