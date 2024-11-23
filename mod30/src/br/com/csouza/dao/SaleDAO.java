package br.com.csouza.dao;

import br.com.csouza.annotations.use.TableUse;
import br.com.csouza.entities.Client;
import br.com.csouza.entities.Sale;
import br.com.csouza.entities.Status;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.factories.BuildSale;
import br.com.csouza.interfaces.dao.ISaleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SaleDAO extends GenericDAO<Sale> implements ISaleDAO {
    @Override
    protected Class<Sale> getEntityClass() {
        return Sale.class;
    }

    @Override
    protected String getSelectIDSQL() throws WithoutTableNameException {
        return "SELECT" +
                " s.id AS sale_id," +
                " s.created_at AS sale_created_at," +
                " c.id AS client_id," +
                " c.name AS client_name," +
                " c.surname AS client_surname," +
                " c.cpf AS client_cpf," +
                " c.telephone AS client_telephone," +
                " c.email AS client_email," +
                " c.age AS client_age," +
                " c.created_at AS client_created_at," +
                " st.id AS status_id," +
                " st.name AS status_name," +
                " st.description AS status_description," +
                " st.created_at AS status_created_at" +
                " FROM " + TableUse.getTableNameClass(this.getEntityClass()) + " AS s" +
                " LEFT JOIN " + TableUse.getTableNameClass(Client.class) + " AS c" +
                " ON s.client_id = c.id" +
                " LEFT JOIN " + TableUse.getTableNameClass(Status.class) + " AS st" +
                " ON s.status_id = st.id" +
                " WHERE s.id = ?";
    }

    @Override
    protected String getSelectSQL() throws WithoutTableNameException {
        return "SELECT" +
                " s.id AS sale_id," +
                " s.created_at AS sale_created_at," +
                " c.id AS client_id," +
                " c.name AS client_name," +
                " c.surname AS client_surname," +
                " c.cpf AS client_cpf," +
                " c.telephone AS client_telephone," +
                " c.email AS client_email," +
                " c.age AS client_age," +
                " c.created_at AS client_created_at," +
                " st.id AS status_id," +
                " st.name AS status_name," +
                " st.description AS status_description," +
                " st.created_at AS status_created_at" +
                " FROM " + TableUse.getTableNameClass(this.getEntityClass()) + " AS s" +
                " LEFT JOIN " + TableUse.getTableNameClass(Client.class) + " AS c" +
                " ON s.client_id = c.id" +
                " LEFT JOIN " + TableUse.getTableNameClass(Status.class) + " AS st" +
                " ON s.status_id = st.id";
    }

    @Override
    protected String getInsertSQL() throws WithoutTableNameException {
        return "INSERT INTO " + TableUse.getTableNameClass(this.getEntityClass()) +
                " (id, client_id, status_id)" +
                " VALUES(NEXTVAL('sq_id_sales'), ?, ?)";
    }

    @Override
    protected void preparedInsertSQL(PreparedStatement stm, Sale entity) throws SQLException {
        stm.setLong(1, entity.getClient().getId());
        stm.setLong(2, entity.getStatus().getId());
    }

    @Override
    protected String getUpdateSQL() throws WithoutTableNameException {
        return "UPDATE " + TableUse.getTableNameClass(this.getEntityClass()) +
                " SET status_id = ? WHERE id = ?";
    }

    @Override
    protected void prepareUpdateSQL(PreparedStatement stm, Sale entity) throws SQLException {
        stm.setLong(1, entity.getStatus().getId());
        stm.setLong(2, entity.getId());
    }

    @Override
    public Sale show(Long id) throws Exception {
        final Connection conn = this.getDbConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;

        Sale sale = null;

        try {
            stm = conn.prepareStatement(this.getSelectIDSQL());

            this.prepareSelectIDSQL(stm, id);

            rs = stm.executeQuery();

            if (rs.next()) {
                sale = BuildSale.build(rs);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(conn, stm, rs);
        }

        return sale;
    }

    @Override
    public Collection<Sale> index() throws Exception {
        Connection conn = this.getDbConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;

        final List<Sale> sales = new ArrayList<>();

        try {
            stm = conn.prepareStatement(this.getSelectSQL());

            rs = stm.executeQuery();

            while(rs.next()) {
                sales.add(BuildSale.build(rs));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(conn, stm, rs);
        }

        return sales;
    }

    @Override
    public Integer open(Sale sale) throws Exception {
        Connection conn = this.getDbConnection();
        PreparedStatement stm = null;

        try {
            stm = conn.prepareStatement(this.getOpenSQL());

            this.prepareOpenSQL(stm, sale);

            return stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(conn, stm, null);
        }
    }

    @Override
    public Integer finish(Sale sale) throws Exception {
        return 0;
    }

    @Override
    public Integer cancel(Sale sale) throws Exception {
        return 0;
    }

    /**
     * Método responsável por obter a query de atualização de status não formatada para "EM ABERTO".
     * @return Query de atualização não formatada.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "WithoutTableNameException".
     */
    protected String getOpenSQL() throws WithoutTableNameException {
        return "UPDATE " + TableUse.getTableNameClass(Sale.class) +
                " SET status_id = ? WHERE id = ?";
    }

    /**
     * Método responsável por formatar a query de atualização de status para "EM ABERTO".
     * @param stm PreparedStatement contendo a query de atualização.
     * @param sale Venda a ter o status atualizado.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    protected  void prepareOpenSQL(PreparedStatement stm, Sale sale) throws SQLException {
        stm.setLong(1, 1L);
        stm.setLong(2, sale.getId());
    }
}
