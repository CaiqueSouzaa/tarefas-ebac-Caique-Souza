package br.com.csouza.factories;

import br.com.csouza.entities.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildClient {
    public static Client build(ResultSet rs) throws SQLException {
        return new Client(
                rs.getLong("client_id"),
                rs.getString("client_name"),
                rs.getString("client_surname"),
                rs.getString("client_cpf"),
                rs.getString("client_telephone"),
                rs.getString("client_email"),
                rs.getInt("client_age"),
                rs.getTimestamp("client_created_at")
        );
    }
}
