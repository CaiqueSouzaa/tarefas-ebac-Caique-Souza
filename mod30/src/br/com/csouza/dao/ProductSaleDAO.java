package br.com.csouza.dao;

import br.com.csouza.annotations.use.TableUse;
import br.com.csouza.entities.ProductSale;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.factories.BuilderProductSale;
import br.com.csouza.interfaces.dao.IProductSaleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSaleDAO extends GenericDAO<ProductSale> implements IProductSaleDAO {
    @Override
    protected Class<ProductSale> getEntityClass() {
        return ProductSale.class;
    }

    @Override
    protected String getInsertSQL() throws WithoutTableNameException {
        return "INSERT INTO " + TableUse.getTableNameClass(this.getEntityClass()) +
                " (id, product_id, amount, sale_id)" +
                " VALUES(NEXTVAL('sq_id_products_sales'), ?, ?, ?)";
    }

    @Override
    protected void preparedInsertSQL(PreparedStatement stm, ProductSale entity) throws SQLException {
        stm.setLong(1, entity.getProduct().getId());
        stm.setInt(2, entity.getAmount());
        stm.setLong(3, entity.getSale().getId());
    }

    @Override
    protected String getUpdateSQL() throws WithoutTableNameException {
        return "";
    }

    @Override
    protected void prepareUpdateSQL(PreparedStatement stm, ProductSale entity) throws SQLException {

    }

    @Override
    public ProductSale show(Long id) throws Exception {
        final Connection conn = this.getDbConnection();
        PreparedStatement stm = null;

        ResultSet rs = null;

        ProductSale productSale = null;

        try {
            stm = conn.prepareStatement(this.getSelectIDSQL());

            rs = stm.executeQuery();

            if (rs.next()) {
                productSale = BuilderProductSale.build(rs);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(conn, stm, rs);
        }

        return productSale;
    }

    /**
     * Método responsável por obter a query de busca com base no ID do registro.
     * @return SQL para busca de um único registro.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "TableName" configurada.
     */
    protected String getSelectIDSQL() throws WithoutTableNameException {
        return "SELECT" +
                " (ps.amount * p.price) AS product_sale_price," +
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
                " st.created_at AS status_created_at," +
                " p.id AS product_id," +
                " p.code AS product_code," +
                " p.name AS product_name," +
                " p.description AS product_description," +
                " p.price AS product_price," +
                " p.created_at AS product_created_at," +
                " ps.id AS product_sale_id," +
                " ps.amount AS product_sale_amount," +
                " ps.created_at AS product_sale_created_at" +
                "FROM tb_products_sales AS ps" +
                "LEFT JOIN tb_sales AS sa" +
                "ON ps.sale_id = sa.id" +
                "LEFT JOIN tb_clients AS c" +
                "ON sa.client_id = c.id" +
                "LEFT JOIN tb_status AS st" +
                "ON sa.status_id = st.id" +
                "LEFT JOIN tb_products AS p" +
                "ON ps.product_id = p.id" +
                "WHERE ps.id = 11;";
    }
}
