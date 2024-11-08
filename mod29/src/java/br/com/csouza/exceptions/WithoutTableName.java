package br.com.csouza.exceptions;

public class WithoutTableName extends Exception {
	public WithoutTableName(String msg) {
		super(msg);
	}
}
