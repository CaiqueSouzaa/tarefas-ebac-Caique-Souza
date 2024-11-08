package br.com.csouza.interfaces;

import java.sql.SQLException;

import br.com.csouza.entities.Client;
import br.com.csouza.exceptions.WithoutTableName;

public interface IClientDAO extends IGenericDAO<Client>{
	Client findByCPF(String cpf) throws SQLException, WithoutTableName;
}
