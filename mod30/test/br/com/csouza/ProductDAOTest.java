package br.com.csouza;

import br.com.csouza.dao.ProductDAO;
import br.com.csouza.entities.Product;
import br.com.csouza.interfaces.dao.IProductDAO;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;

public class ProductDAOTest {
    private IProductDAO dao;

    @After
    public void deleteData() throws Exception {
        final Collection<Product> products = this.dao.index();
        for (Product p : products) {
            this.dao.destroy(p);
        }
    }

    public ProductDAOTest() {
        this.dao = new ProductDAO();
    }

    @Test
    public void store() throws Exception {
        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);
        final Product p4 = new Product("ES-0004", "Headset", "Headset HyperX Cloud Stinger", 299.99f);
        final Product p5 = new Product("ES-0005", "Webcam", "Webcam Logitech C920 HD", 450.00f);
        final Product p6 = new Product("ES-0006", "Notebook", "Notebook Acer Aspire 5", 3499.90f);


        this.dao.store(p1);
        this.dao.store(p2);
        this.dao.store(p3);
        this.dao.store(p4);
        this.dao.store(p5);
        this.dao.store(p6);

        
    }
}
