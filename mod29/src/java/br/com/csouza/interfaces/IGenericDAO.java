package br.com.csouza.interfaces;

import java.util.Collection;
import java.sql.SQLException;
import br.com.csouza.entities.DatabaseEntity;
import br.com.csouza.exceptions.WithoutTableName;

/**
 * Interface responsável por conter os métodos que as classes DAOs devem implementar.
 * @param <T> Entidade a ser manipulada pelo DAO.
 * @author Caique Souza
 * @version 0.1
 */
public interface IGenericDAO<T extends DatabaseEntity> {
	/**
	 * Método para realizar um novo registro no banco de dados.
	 * @param entity Entidade a ser registrada no banco de dados.
	 * @return Quantia de registros.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 * @throws WithoutTableName 
	 */
	public Integer store(T entity) throws SQLException, WithoutTableName;
	
	/**
	 * Método para buscar um único registro no banco de dados.
	 * @param id ID do registro a ser buscado no banco de dados.
	 * @return Um objeto contendo as informações do registro.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 */
	public T show(Long id) throws SQLException, WithoutTableName;
	
	/**
	 * 
	 * @return Método para realizar a busca de todos os registro no banco de dados.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 */
	public Collection<T> index() throws SQLException, WithoutTableName;
	
	/**
	 * Método para atualizar um registro no banco de dados.
	 * @param entity Objeto contendo as informações a serem atualizadas.
	 * @return Quantia de registros atualizados.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 */
	public Integer update(T entity) throws SQLException, WithoutTableName;
	
	/**
	 * Método para excluir um registro no banco de dados.
	 * @param id ID do registro a ser excluido do banco de dados.
	 * @return Quantia de registros excluidos.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 */
	public Integer destroy(Long id) throws SQLException, WithoutTableName;
}
