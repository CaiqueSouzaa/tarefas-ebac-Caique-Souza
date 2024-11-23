package br.com.csouza.dao.mocks;

import br.com.csouza.annotations.use.TableUse;
import br.com.csouza.entities.Product;
import br.com.csouza.entities.Storage;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.factories.BuildProduct;
import br.com.csouza.factories.BuildStorage;
import br.com.csouza.interfaces.dao.IProductDAO;
import br.com.csouza.interfaces.dao.IStorageDAO;
import br.com.csouza.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StorageDAOMock extends GenericDAOMock<Storage> implements IStorageDAO {

    @Override
    protected Class<Storage> getEntityClass() {
        return null;
    }

    @Override
    protected String getInsertSQL() throws WithoutTableNameException {
        return "";
    }

    @Override
    protected void preparedInsertSQL(PreparedStatement stm, Storage entity) throws SQLException {

    }

    @Override
    protected String getUpdateSQL() throws WithoutTableNameException {
        return "";
    }

    @Override
    protected void prepareUpdateSQL(PreparedStatement stm, Storage entity) throws SQLException {

    }

    @Override
    public Storage getByCode(String code) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Storage storage = null;

        try (Connection conn = this.getDbConnection()) {
            stm = conn.prepareStatement(this.getSelectByCodeSQL());
            this.prepareSelectByCodeSQL(stm, code);

            rs = stm.executeQuery();

            if (rs.next()) {
                final Product product = BuildProduct.build(rs);
                storage = BuildStorage.build(rs, product);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(null, stm, rs);
        }

        return storage;
    }

    @Override
    public Integer setAmount(String code, int amount) throws SQLException {

        PreparedStatement stm = null;

        try (Connection conn = this.getDbConnection()) {
            stm = conn.prepareStatement(this.getUpdateAmountSQL());

            this.prepareUpdateAmountSQL(stm, code, amount);

            return stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(null, stm, null);
        }
    }

    @Override
    public Integer add(String code, int amount) throws SQLException {

        PreparedStatement stm = null;

        try (Connection conn = this.getDbConnection()) {
            stm = conn.prepareStatement(this.getIncrementAmountSQL());

            this.prepareIncrementAmountSQL(stm, code, amount);

            return stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(null, stm, null);
        }
    }

    @Override
    public Integer sub(String code, int amount) throws SQLException {

        PreparedStatement stm = null;


        try (Connection conn = this.getDbConnection()) {
            stm = conn.prepareStatement(this.getSubtractAmountSQL());

            this.prepareSubtractAmountSQL(stm, code, amount);

            return stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(null, stm, null);
        }
    }

    @Override
    public Collection<Storage> index() throws SQLException {

        PreparedStatement stm = null;
        final List<Storage> list = new ArrayList<>();

        try (Connection conn = this.getDbConnection()) {
            stm = conn.prepareStatement(this.getSelectSQL());

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    final Product product = BuildProduct.build(rs);
                    final Storage storage = BuildStorage.build(rs, product);
                    list.add(storage);
                }
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection(null, stm, null);
        }

        return list;
    }

    /**
     * Método responsável por obter a query de seleção.
     * @return Query de seleção não formata.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "WithoutTableNameException".
     */
    @Override
    protected String getSelectSQL() throws WithoutTableNameException {
        return "SELECT" +
                " p.id AS product_id," +
                " p.code AS code," +
                " p.name AS name," +
                " p.description AS description," +
                " p.price AS price," +
                " p.created_at AS product_created_at," +
                " s.id AS storage_id," +
                " s.amount AS amount," +
                " s.created_at AS storage_created_at" +
                " FROM " + TableUse.getTableNameClass(Storage.class) + " AS s" +
                " LEFT JOIN tb_products AS p" +
                " ON s.product_id = p.id";
    }

    /**
     * Método responsável por obter a query de seleção pelo código.
     * @return Query de seleção não formata.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "WithoutTableNameException".
     */
    private String getSelectByCodeSQL() throws WithoutTableNameException {
        return this.getSelectSQL() + " WHERE p.code = ?";
    }

    /**
     * Método responsável por formatar a query de seleção de um item em estoque.
     * @param stm PreparedStatement contendo a query de seleção a ser formatada.
     * @param code Código do produto a ser buscado.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    private void prepareSelectByCodeSQL(PreparedStatement stm, String code) throws SQLException {
        stm.setString(1, code);
    }

    /**
     * Método para obter a query para atualizar a quantia em estoque de um item com base em seu código.
     * @return Query não formatada.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "WithoutTableNameException".
     */
    private String getUpdateAmountSQL() throws WithoutTableNameException {
        return "UPDATE " + TableUse.getTableNameClass(Storage.class) + " AS s" +
                " SET amount = ?" +
                " FROM " + TableUse.getTableNameClass(Product.class) + " AS p" +
                " WHERE s.product_id = p.id" +
                " AND p.code = ?";
    }

    /**
     * Método para formatar a query de atualização de quantia em estoque de um item.
     * @param stm PreparedStatement com a query de atualização.
     * @param code Código do item a ter a quantia em estoque a atualizada.
     * @param amount Quantia ser definida no estoque.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    private void prepareUpdateAmountSQL(PreparedStatement stm, String code, int amount) throws SQLException {
        stm.setInt(1, amount);
        stm.setString(2, code);
    }

    /**
     * Método responsável por obter a query não formatada para incrementar a quantia em estoque de um produto com base em seu código.
     * @return Query de incrementação não formatada.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "WithoutTableNameException".
     */
    private String getIncrementAmountSQL() throws WithoutTableNameException {
        return "UPDATE " + TableUse.getTableNameClass(Storage.class) + " AS s" +
                " SET amount = s.amount + ?" +
                " FROM " + TableUse.getTableNameClass(Product.class) + " AS p" +
                " WHERE s.product_id = p.id" +
                " AND p.code = ?";
    }

    /**
     * Método para formatar a query de incrementação de quantia em estoque.
     * @param stm PreparedStatement contendo a query de incrementação.
     * @param code Código de produto a ter a quantia em estoque incrementada.
     * @param amount Quantia a ser incrementada.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    private void prepareIncrementAmountSQL(PreparedStatement stm, String code, int amount) throws SQLException {
        stm.setInt(1, amount);
        stm.setString(2, code);
    }

    /**
     * Método responsável por obter a query não formatada de subtração de quantias de um produto em estoque.
     * @return Query de subtração não formatada.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "WithoutTableNameException".
     */
    private String getSubtractAmountSQL() throws WithoutTableNameException {
        return "UPDATE " + TableUse.getTableNameClass(Storage.class) + "AS s" +
                " SET s.amount = ?" +
                " FROM " + TableUse.getTableNameClass(Product.class) + "AS p"+
                " WHERE s.product_id = p.id" +
                " AND p.code = ?";
    }

    /**
     * Método responsável por formatar a query de subtração de quantia de um produto no estoque com base em seu código.
     * @param stm PreparedStatement contendo a query de subtração.
     * @param code Código de produto a ter a quantia em estoque subtraida.
     * @param amount Quantia a ser subtraida.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    private void prepareSubtractAmountSQL(PreparedStatement stm, String code, int amount) throws SQLException {
        stm.setInt(1, amount);
        stm.setString(2, code);
    }
}




































