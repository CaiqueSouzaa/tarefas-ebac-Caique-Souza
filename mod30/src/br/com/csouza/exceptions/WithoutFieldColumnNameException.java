package br.com.csouza.exceptions;

import java.sql.SQLException;

public class WithoutFieldColumnNameException extends SQLException {
    public WithoutFieldColumnNameException(String msg) {
        super(msg);
    }
}
