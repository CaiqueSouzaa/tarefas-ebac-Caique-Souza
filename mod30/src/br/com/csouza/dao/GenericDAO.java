package br.com.csouza.dao;

import br.com.csouza.annotations.use.TableUse;
import br.com.csouza.entities.DatabaseEntity;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.interfaces.dao.IGenericDAO;
import br.com.csouza.jdbc.ConnectionFactory;
import br.com.csouza.jdbc.PostgreSQLTestFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public abstract class GenericDAO<T extends DatabaseEntity> implements IGenericDAO<T> {

    /**
     * Método responsável por fornecer a conexão ao banco de dados.
     * @return Conexão ao bando de dados.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    protected Connection getDbConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    @Override
    public Integer store(T entity) throws Exception {
        Connection conn = this.getDbConnection();
        PreparedStatement stm = null;

        try {
            stm = conn.prepareStatement(this.getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
            this.preparedInsertSQL(stm, entity);

            int result = stm.executeUpdate();

            if (result > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()) {
                    if (rs.next()) {
                        entity.setId(rs.getLong(1));
                    }
                }
            }

            return result;

        } finally {
            this.closeConnection(conn, stm, null);
        }
    }

    @Override
    public T show(Long id) throws Exception {
        Connection conn = this.getDbConnection();
        PreparedStatement stm = null;
        T entity = null;

        try {
            stm = conn.prepareStatement(this.getSelectIDSQL());

            this.prepareSelectIDSQL(stm, id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    entity = this.setEntityAttributes(rs);
                }
            }
        } finally {
            this.closeConnection(conn, stm, null);
        }

        return entity;
    }

    @Override
    public Collection<T> index() throws Exception {
        Connection conn = this.getDbConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        final List<T> list = new ArrayList<>();

        try {
            stm = conn.prepareStatement(this.getSelectSQL());

            rs = stm.executeQuery();
            while(rs.next()) {
                final T entity = this.setEntityAttributes(rs);
                list.add(entity);
            }

        } finally {
            this.closeConnection(conn, stm, rs);
        }
        return list;
    }

    @Override
    public Integer update(T entity) throws Exception {
        Connection conn = this.getDbConnection();
        PreparedStatement stm = null;

        try {
            stm = conn.prepareStatement(this.getUpdateSQL());

            this.prepareUpdateSQL(stm, entity);

            return stm.executeUpdate();
        } finally {
            this.closeConnection(conn, stm, null);
        }
    }

    @Override
    public Integer destroy(T entity) throws Exception {
        Connection conn = this.getDbConnection();
        PreparedStatement stm = null;

        try {
            stm = conn.prepareStatement(this.getDeleteSQL());
            this.prepareDeleteSQL(stm, entity);

            return stm.executeUpdate();
        } finally {
            this.closeConnection(conn, stm, null);
        }
    }
    /**
     * Método para finalizar a conexão com o banco de dadados.
     * @param conn Connection
     * @param stm PreparedStatement
     * @param rs ResultSet
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    protected void closeConnection(Connection conn, PreparedStatement stm, ResultSet rs) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }

        if (stm != null && !stm.isClosed()) {
            stm.close();
        }

        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }

    /**
     * Método responsável por definir os valores aos atributos da entidade.
     * @param rs ResultSet contendo as informações do banco de dados.
     * @return Entidade configurada.
     */
    protected T setEntityAttributes(ResultSet rs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Class<T> entityClass = this.getEntityClass();
        final T entity = entityClass.getDeclaredConstructor().newInstance();

        final Stream<Field> fields = TableUse.getFilteredFieldsDatabaseTableColumn(entityClass);


        fields.forEach((Field f) -> {
            try {
                Method setMethod = entityClass.getMethod(TableUse.getTableColumnSetValue(f), TableUse.getTableColumnTypeClass(f));
                executeSetMethod(f, setMethod, entity, rs);
            } catch (NoSuchMethodException | SQLException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return entity;
    }

    /**
     * Método responsável por executar o "get" de "ResultSet" de acordo com o valor esperado.
     * @param f Atributo da entidade a ser obtido as informações.
     * @param setMethod Método "set" a ser executado.
     * @param entity Entidade a receber o valor.
     * @param rs ResultSet contendo as inforamações do banco de dados.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     * @throws InvocationTargetException Exception lançada caso ocorra algum erro durante o processo.
     * @throws IllegalAccessException Exception lançada caso ocorra algum erro durante o processo.
     */
    protected void executeSetMethod(Field f, Method setMethod, T entity, ResultSet rs) throws SQLException, InvocationTargetException, IllegalAccessException {
        switch (TableUse.getTableColumnType(f)) {
            case "String":
                setMethod.invoke(entity, rs.getString(TableUse.getTableColumnName(f)));
                break;
            case "int":
                setMethod.invoke(entity, rs.getInt(TableUse.getTableColumnName(f)));
                break;
            case "Long":
                setMethod.invoke(entity, rs.getLong(TableUse.getTableColumnName(f)));
                break;
            case "Timestamp":
                setMethod.invoke(entity, rs.getTimestamp(TableUse.getTableColumnName(f)));
                break;
            case "float":
                setMethod.invoke(entity, rs.getFloat(TableUse.getTableColumnName(f)));
                break;
            default:
                System.out.println("TIPO NÃO IDENTIFICADO");
                return;
        }
    }

    /**
     * Método abstrato responsável por obter a "Class" da entidade.
     * @return Class da entidade.
     */
    protected abstract Class<T> getEntityClass();

    // Inserção

    /**
     * Método abstrato responsável por obter a query de inserção.
     * @return SQL de inserção não formatada.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "TableName" configurada.
     */
    protected abstract String getInsertSQL() throws WithoutTableNameException;

    /**
     * Método abstrato responsável por formatar a query de inserção.
     * @param stm PrepareStatement contendo a query a ser formatada.
     * @param entity Entidade que fará parte da query.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    protected abstract void preparedInsertSQL(PreparedStatement stm, T entity) throws SQLException;

    /**
     * Método responsável por obter a query de busca com base no ID do registro.
     * @return SQL para busca de um único registro.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "TableName" configurada.
     */
    protected String getSelectIDSQL() throws WithoutTableNameException {
        return "SELECT * FROM " + TableUse.getTableNameClass(this.getEntityClass()) + " WHERE id = ?";
    }

    /**
     * Método responsável por formatar a query de exclusão.
     * @param stm PrepareStatement contendo a query a ser formatada.
     * @param id ID do registro a ser buscado.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    protected void prepareSelectIDSQL(PreparedStatement stm, Long id) throws SQLException {
        stm.setLong(1, id);
    }

    // Busca todos
    /**
     * Método responsável por obter a query de busca de todos os registros.
     * @return SQL para busca de registros.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "TableName" configurada.
     */
    protected String getSelectSQL() throws WithoutTableNameException {
        return "SELECT * FROM " + TableUse.getTableNameClass(this.getEntityClass());
    }

    /**
     * Método abstrato responsável por obter a query de atualizar de registro.
     * @return SQL de atualização de registro não formatada.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "TableName" configurada.
     */
    protected abstract String getUpdateSQL() throws WithoutTableNameException;

    /**
     * Método abstrato responsável por formatar a query de atualização.
     * @param stm PrepareStatement contendo a query a ser formatada.
     * @param entity Entidade que fará parte da query.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.s
     */
    protected abstract void prepareUpdateSQL(PreparedStatement stm, T entity) throws SQLException;

    // Exclusão
    /**
     * Método responsável por obter a query de exclusão de um registro com base em seu ID.
     * @return SQL de exclusão não formatada.
     * @throws WithoutTableNameException Exception lançada caso a entidade não possua a anotação "TableName" configurada.
     */
    protected String getDeleteSQL() throws WithoutTableNameException {
        return "DELETE FROM " + TableUse.getTableNameClass(this.getEntityClass()) + " WHERE id = ?";
    }

    /**
     * Método abstrato responsável por formatar a query de exclusão.
     * @param stm PrepareStatement contendo a query a ser formatada.
     * @param entity Entidade que fará parte da query.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    protected void prepareDeleteSQL(PreparedStatement stm, T entity) throws SQLException {
        stm.setLong(1, entity.getId());
    }
}
