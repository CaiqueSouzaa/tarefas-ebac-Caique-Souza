package br.com.csouza;

import br.com.csouza.dao.*;
import br.com.csouza.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;
import java.util.stream.Stream;

public class ProductSaleDAOTest {
    private final ProductSaleDAO dao;
    private final SaleDAO saleDAO;
    private final StatusDAO statusDAO;
    private final ClientDAO clientDAO;
    private final ProductDAO productDAO;
    private final StorageDAO storageDAO;

    public ProductSaleDAOTest() {
        this.dao = new ProductSaleDAO();
        this.saleDAO = new SaleDAO();
        this.statusDAO = new StatusDAO();
        this.clientDAO = new ClientDAO();
        this.productDAO = new ProductDAO();
        this.storageDAO = new StorageDAO();
    }

    @After
    public void deleteAfter() throws Exception {
        final Collection<ProductSale> productSales = this.dao.index();
        final Collection<Sale> sales = this.saleDAO.index();
        final Collection<Client> clients = this.clientDAO.index();
        final Collection<Product> products = this.productDAO.index();

        for (ProductSale p : productSales) {
            this.dao.destroy(p);
        }

        for (Product p : products) {
            this.productDAO.destroy(p);
        }

        for (Client c : clients) {
            this.clientDAO.destroy(c);
        }

        for (Sale s : sales) {
            this.saleDAO.destroy(s);
        }

    }

    @Test
    public void store() throws Exception {
        // Produtos
        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);

        this.productDAO.store(p1);
        this.productDAO.store(p2);
        this.productDAO.store(p3);

        // Clientes
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Estoque
        this.storageDAO.setAmount(p1.getCode(), 435);
        this.storageDAO.setAmount(p2.getCode(), 12);
        this.storageDAO.setAmount(p3.getCode(), 981);

        // Status
        final Status sAberto = this.statusDAO.show(1L);

        // Venda
        final Sale sale = new Sale(c2, sAberto);

        this.saleDAO.store(sale);

        // Venda de produto
        final ProductSale productSale = new ProductSale(p2, 3, sale);

        final int rs = this.dao.store(productSale);

        Assert.assertEquals(1, rs);
    }
}
