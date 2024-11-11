package br.com.csouza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.csouza.entities.Client;
import br.com.csouza.exceptions.WithoutTableName;
import br.com.csouza.interfaces.IClientDAO;
import br.com.csouza.jdbc.ConnectionFactory;
import br.com.csouza.utils.TableNameUtil;

public class ClientDAO extends GenericDAO<Client> implements IClientDAO {

	@Override
	protected String getInsertSQL() throws WithoutTableName {
		return "INSERT INTO " + TableNameUtil.getTableNameClass(Client.class) + "(id, name, surname, cpf, telephone, age) "
				+ "VALUES (NEXTVAL('sq_id_clients'), ?, ?, ?, ?, ?)";
	}

	@Override
	protected void preparedInsertSQL(PreparedStatement stm, Client entity) throws SQLException {
		stm.setString(1, entity.getName());
		stm.setString(2, entity.getSurname());
		stm.setString(3, entity.getCpf());
		stm.setString(4, entity.getTelephone());
		stm.setInt(5, entity.getAge());
	}

	@Override
	public Client findByCPF(String cpf) throws SQLException, WithoutTableName {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stm = conn.prepareStatement(this.getFindByCpfSQL());
			this.prepareFindByCpfSQL(stm, cpf);
			
			rs = stm.executeQuery();
			
			if (rs.next()) {
				return new Client(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getString("cpf"), rs.getString("telephone"), rs.getInt("age"), rs.getTimestamp("created_at"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeConnection(conn, stm, rs);
		}
	}

	@Override
	protected String getDeleteSQL() throws WithoutTableName {
		return "DELETE FROM " + TableNameUtil.getTableNameClass(Client.class) + " WHERE id = ?";
	}

	@Override
	protected void preparedDeleteSQL(PreparedStatement stm, Long id) throws SQLException {
		stm.setLong(1, id);
	}
	
	private String getFindByCpfSQL() throws WithoutTableName {
		return "SELECT * FROM " + TableNameUtil.getTableNameClass(Client.class) + " WHERE cpf = ?";
	}
	
	private void prepareFindByCpfSQL(PreparedStatement stm, String cpf) throws SQLException {
		stm.setString(1, cpf);
	}

	@Override
	protected String getSelectSQL() throws WithoutTableName {
		return "SELECT * FROM " + TableNameUtil.getTableNameClass(Client.class) + " WHERE id = ?";
	}

	@Override
	protected void preparedSelectSQL(PreparedStatement stm, Long id) throws SQLException {
		stm.setLong(1, id);
		
	}

	@Override
	protected Client setAttributes(ResultSet rs) throws SQLException {
		return new Client(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getString("cpf"), rs.getString("telephone"), rs.getInt("age"), rs.getTimestamp("created_at"));
	}

	@Override
	protected String getIndexSQL() throws WithoutTableName {
		return "SELECT * FROM " + TableNameUtil.getTableNameClass(Client.class);
	}

	@Override
	protected String getUpdateSQL() throws WithoutTableName {
		return "UPDATE " + TableNameUtil.getTableNameClass(Client.class) + " SET name = ?, surname = ?, cpf = ?, telephone = ?, age = ? WHERE id = ?";
	}

	@Override
	protected void preparedUpdateSQL(PreparedStatement stm, Client entity) throws SQLException {
		stm.setString(1, entity.getName());
		stm.setString(2, entity.getSurname());
		stm.setString(3, entity.getCpf());
		stm.setString(4, entity.getTelephone());
		stm.setInt(5, entity.getAge());
		stm.setLong(6, entity.getId());
	}
}
