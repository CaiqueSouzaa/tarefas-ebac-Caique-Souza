package br.com.csouza.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.csouza.entities.DatabaseEntity;
import br.com.csouza.exceptions.WithoutTableName;
import br.com.csouza.interfaces.IGenericDAO;
import br.com.csouza.jdbc.ConnectionFactory;

public abstract class GenericDAO<T extends DatabaseEntity> implements IGenericDAO<T> {

	@Override
	public Integer store(T entity) throws SQLException, WithoutTableName {
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = ConnectionFactory.getConnection();
			stm = conn.prepareStatement(this.getInsertSQL());
			this.preparedInsertSQL(stm, entity);
			
			return stm.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeConnection(conn, stm, null);
		}
	}

	@Override
	public T show(Long id) throws SQLException, WithoutTableName {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stm = conn.prepareStatement(this.getSelectSQL());
			this.preparedSelectSQL(stm, id);
			
			rs = stm.executeQuery();
			
			if (rs.next()) {
				return this.setAttributes(rs);
			}
			
			return null;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeConnection(conn, stm, rs);
		}
	}

	@Override
	public Collection<T> index() throws SQLException, WithoutTableName {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stm = conn.prepareStatement(this.getIndexSQL());
			
			rs = stm.executeQuery();
			
			final List<T> itemsList = new ArrayList<>();
			while (rs.next()) {
				itemsList.add(this.setAttributes(rs));
			}
			
			return itemsList;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeConnection(conn, stm, rs);
		}
	}

	@Override
	public Integer update(T entity) throws SQLException, WithoutTableName {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer destroy(Long id) throws SQLException, WithoutTableName {
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stm = conn.prepareStatement(this.getDeleteSQL());
			this.preparedDeleteSQL(stm, id);
			
			return stm.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeConnection(conn, stm, null);
		}
	}
	
	/**
	 * Método abstrato responsável por obter a consulta SQL de inserção.
	 * @return SQL de inserção não formatada.
	 * @throws WithoutTableName Exception lançada caso o nome da tabela não esteja configurada na entidade.
	 */
	protected abstract String getInsertSQL() throws WithoutTableName;
	
	/**
	 * Método abstrado responsável por formatar a consulta SQL de inserção.
	 */
	protected abstract void preparedInsertSQL(PreparedStatement stm, T entity) throws SQLException;

	/**
	 * Método abstrato responsável por obter a consulta SQL de seleção de um unico registro.
	 * @return SQL de select não formatado.
	 * @throws WithoutTableName Exception lançada caso o nome da tabela não esteja configurada na entidade.
	 */
	protected abstract String getSelectSQL() throws WithoutTableName;

	/**
	 * Método abstrato responsável por formatar a consulta SQL de seleção de um unico registro.
	 * @param stm PreparedStatement a ser formatado.
	 * @param id ID do registro a ser buscado.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 */
	protected abstract void preparedSelectSQL(PreparedStatement stm, Long id) throws SQLException;
	
	/**
	 * Método abstrato responsável por obter a consulta SQL de seleção de todos os registros.
	 * @return SQL de select não formatado.
	 * @throws WithoutTableName Exception lançada caso o nome da tabela não esteja configurada na entidade.
	 */
	protected abstract String getIndexSQL() throws WithoutTableName;
	
	/**
	 * Método abstrato resposável por obter a consulta SQL de exclusão.
	 * @return SQL de exclusão não formatada.
	 * @throws WithoutTableName Exception lançada caso o nome da tabela não esteja configurada na entidade.
	 */
	protected abstract String getDeleteSQL() throws WithoutTableName;
	
	/**
	 * Método abstrato responsável por formatar a consulta SQL de exclusão.
	 * @param stm PreparedStatement a ser formatado.
	 * @param id ID do registro a ser excluido.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 */
	protected abstract void preparedDeleteSQL(PreparedStatement stm, Long id) throws SQLException;
	
	/**
	 * Método responsável por definir os valores no objeto com os valores que são retornado do banco de dados.
	 * @param rs ResultSet com as informações.
	 * @return Objeto com as informações do registro.
	 * @throws SQLException Falha na conexão ou manipulação no banco de dados.
	 */
	protected abstract T setAttributes(ResultSet rs) throws SQLException;
	
	protected void closeConnection(Connection conn, PreparedStatement stm, ResultSet rs) throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
}