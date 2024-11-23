package br.com.csouza.factories;

import br.com.csouza.entities.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildStatus {
    public static Status build(ResultSet rs) throws SQLException {
        return new Status(
                rs.getLong("status_id"),
                rs.getString("status_name"),
                rs.getString("status_description"),
                rs.getTimestamp("status_created_at")
        );
    }
}
