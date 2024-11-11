package br.com.csouza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.csouza.entities.Product;
import br.com.csouza.exceptions.WithoutTableName;
import br.com.csouza.interfaces.IProductDAO;
import br.com.csouza.jdbc.ConnectionFactory;
import br.com.csouza.utils.TableNameUtil;

public class ProductDAO extends GenericDAO<Product> implements IProductDAO {

	@Override
	public Product findByCode(String code) throws SQLException, WithoutTableName {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stm = conn.prepareStatement(this.getFindedByCode());
			this.preparedFindedByCode(stm, code);
			
			rs = stm.executeQuery();
			
			if (rs.next()) {
				return this.setAttributes(rs);
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
	protected String getInsertSQL() throws WithoutTableName {
		return "INSERT INTO " + TableNameUtil.getTableNameClass(Product.class)
			+ " (id, code, name, description)"
			+ " VALUES (NEXTVAL('sq_id_products'), ?, ?, ?)";
	}

	@Override
	protected void preparedInsertSQL(PreparedStatement stm, Product entity) throws SQLException {
		stm.setString(1, entity.getCode());
		stm.setString(2, entity.getName());
		stm.setString(3, entity.getDescription());
	}

	@Override
	protected String getSelectSQL() throws WithoutTableName {
		return "SELECT * FROM " + TableNameUtil.getTableNameClass(Product.class)
			+ " WHERE id = ?";
	}

	@Override
	protected void preparedSelectSQL(PreparedStatement stm, Long id) throws SQLException {
		stm.setLong(1, id);
	}

	@Override
	protected String getIndexSQL() throws WithoutTableName {
		return "SELECT * FROM " + TableNameUtil.getTableNameClass(Product.class);
	}

	@Override
	protected String getUpdateSQL() throws WithoutTableName {
		return "UPDATE " + TableNameUtil.getTableNameClass(Product.class)
			+ " SET code = ?, name = ?, description = ? WHERE id = ?";
	}

	@Override
	protected void preparedUpdateSQL(PreparedStatement stm, Product entity) throws SQLException {
		stm.setString(1, entity.getCode());
		stm.setString(2, entity.getName());
		stm.setString(3, entity.getDescription());
		stm.setLong(4, entity.getId());
	}

	@Override
	protected String getDeleteSQL() throws WithoutTableName {
		return "DELETE FROM " + TableNameUtil.getTableNameClass(Product.class)
			+ " WHERE id = ?";
	}

	@Override
	protected void preparedDeleteSQL(PreparedStatement stm, Long id) throws SQLException {
		stm.setLong(1, id);
	}

	@Override
	protected Product setAttributes(ResultSet rs) throws SQLException {
		return new Product(rs.getLong("id"), rs.getString("code"), rs.getString("name"), rs.getString("description"), rs.getTimestamp("created_at"));
	}
	
	private String getFindedByCode() throws WithoutTableName {
		return "SELECT * FROM " + TableNameUtil.getTableNameClass(Product.class) + " WHERE code = ?";
	}
	
	private void preparedFindedByCode(PreparedStatement stm, String code) throws SQLException {
		stm.setString(1, code);
	}

}
