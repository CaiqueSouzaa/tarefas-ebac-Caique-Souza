package br.com.csouza.interfaces;

import java.sql.SQLException;

import br.com.csouza.entities.Client;
import br.com.csouza.exceptions.WithoutTableName;

public interface IClientDAO extends IGenericDAO<Client>{
	/**
	 * Método para obter um cliente por seu CPF;
	 * @param cpf CPF do cliente a ser buscado.
	 * @return Cliente buscado.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 * @throws WithoutTableName Exception lançada caso o nome da tabela não esteja configurada na entidade.
	 */
	Client findByCPF(String cpf) throws SQLException, WithoutTableName;
}
