package br.com.csouza.dao.mocks;

import br.com.csouza.entities.Status;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.interfaces.dao.IStatusDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusDAOMock extends GenericDAOMock<Status> implements IStatusDAO {
    @Override
    protected Class<Status> getEntityClass() {
        return Status.class;
    }

    @Override
    protected String getInsertSQL() throws WithoutTableNameException {
        return "";
    }

    @Override
    protected void preparedInsertSQL(PreparedStatement stm, Status entity) throws SQLException {

    }

    @Override
    protected String getUpdateSQL() throws WithoutTableNameException {
        return "";
    }

    @Override
    protected void prepareUpdateSQL(PreparedStatement stm, Status entity) throws SQLException {

    }
}
