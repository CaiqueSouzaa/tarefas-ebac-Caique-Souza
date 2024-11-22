package br.com.csouza.exceptions;

import java.sql.SQLException;

public class WithoutTableNameException extends SQLException {
    public WithoutTableNameException(String msg) {
        super(msg);
    }
}
