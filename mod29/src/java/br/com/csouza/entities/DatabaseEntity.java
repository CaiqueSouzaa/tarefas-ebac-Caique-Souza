package br.com.csouza.entities;

import java.sql.Timestamp;

/**
 * Classe pai a qual deve extendida em outras classe que representem registros no banco de dados;
 * @author Caique Souza
 * @version 0.1
 */
public class DatabaseEntity {
	private Long id;
	private Timestamp createdAt;
	
	public DatabaseEntity() {}

	/**
	 * @param id ID do registro.
	 * @param createdAt Timestamp de criação do registro.
	 */
	public DatabaseEntity(Long id, Timestamp createdAt) {
		this.id = id;
		this.createdAt = createdAt;
	}
	
	/**
	 * Método para definir o ID do registro.
	 * @param id ID do registro;
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Método para definir o Timestamp de crição do registro.
	 * @param createdAt Timestamp de crição do registro.
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * Método para obter o ID do registro.
	 * @return ID do registro.
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Método para obter o Timestamp de criação do registro.
	 * @return Timestamp de crição do registro.
	 */
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}
}
