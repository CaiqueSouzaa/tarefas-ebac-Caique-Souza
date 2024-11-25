package br.com.csouza.interfaces.dao;

import br.com.csouza.entities.Product;
import br.com.csouza.entities.Sale;
import br.com.csouza.interfaces.database.*;

import java.util.Collection;
import java.util.Map;

public interface ISaleDAO extends IShow<Sale>, IStore<Sale>, IIndex<Sale>, IUpdate<Sale>, IDestroy<Sale> {
    /**
     * Método responsável por abrir uma venda.
     * @param sale Venda ser aberta.
     * @return Quantia de vendas abertas.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    public Integer open(Sale sale) throws Exception;

    /**
     * Método responsável por finalizar uma venda.
     * @param sale Venda a ser finalizada.
     * @return Quantia de vendas finalizadas.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    public Integer finish(Sale sale) throws Exception;

    /**
     * Método responsável por cancelar uma venda.
     * @param sale Venda a ser cancelada.
     * @return Quantia de vendas canceladas.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    public Integer cancel(Sale sale) throws Exception;

    /**
     * Método responśavel por adicionar um produto a lista de compras.
     * @param sale Venda a receber a lista de produtos.
     * @param product Produto a ser adicionado a lista de compras.
     * @return Quantia de linhas registradas.
     * @throws Exception Exception lançada caso ocorra algum erro.
     */
    public Integer addProducts(Sale sale, Product product, int amount) throws Exception;
}
