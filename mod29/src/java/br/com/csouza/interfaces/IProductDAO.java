package br.com.csouza.interfaces;

import java.sql.SQLException;

import br.com.csouza.entities.Product;
import br.com.csouza.exceptions.WithoutTableName;

public interface IProductDAO extends IGenericDAO<Product> {
	/**
	 * Método para obter um produto por seu código.
	 * @param code Código do produto a ser buscado.
	 * @return Produto buscado.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 * @throws WithoutTableName Exception lançada caso o nome da tabela não esteja configurada na entidade.
	 */
	public Product findByCode(String code) throws SQLException, WithoutTableName;
}
