package br.com.csouza.interfaces.dao;

import br.com.csouza.entities.Storage;
import br.com.csouza.interfaces.database.IIndex;
import br.com.csouza.interfaces.database.IShow;

import java.sql.SQLException;

public interface IStorageDAO extends IShow<Storage>, IIndex<Storage> {
    /**
     * Método para obter o estoque de um produto pelo seu código.
     * @param code Código de produto a ser buscado.
     * @return Estoque do produto buscado.
     */
    public Storage getByCode(String code) throws SQLException;

    /**
     * Método para definir a quantia em estoque de um item.
     * @param code Código de produto a ser definido o estoque.
     * @param amount Quantia ser definida.
     * @return Quantia de registros atualizados.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    public Integer setAmount(String code, int amount) throws SQLException;

    /**
     * Método para incrementar uma quantia sobre o estoque atual do item.
     * @param code Código de produto a ter o estoque incrementado.
     * @param amount Quantia a ser incrementada no estoque.
     * @return Quantia de linhas atualizadas.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    public Integer add(String code, int amount) throws SQLException;

    /**
     * Método para subtrair uma quantia sobre o estoque atual do item.
     * @param code Código de produto a ter o estoque subtraido.
     * @param amount Quantia a ser subtraida no estoque.
     * @return Quantia de linhas atualizadas.
     * @throws SQLException Exception lançada caso ocorra algum erro durante o processo.
     */
    public Integer sub(String code, int amount) throws SQLException;
}
