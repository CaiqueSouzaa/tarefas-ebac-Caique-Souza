package br.com.csouza;

import br.com.csouza.dao.ProductDAO;
import br.com.csouza.dao.StorageDAO;
import br.com.csouza.entities.Product;
import br.com.csouza.entities.Storage;
import br.com.csouza.interfaces.dao.IProductDAO;
import br.com.csouza.interfaces.dao.IStorageDAO;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;

public class StorageDAOTest {
    private IStorageDAO dao;
    private IProductDAO productDAO;

    public StorageDAOTest() {
        this.dao = new StorageDAO();
        this.productDAO = new ProductDAO();
    }

    @After
    public void deleteAfter() throws Exception {
        final Collection<Product> products = this.productDAO.index();

        for (Product p : products) {
            this.productDAO.destroy(p);
        }
    }

    @Test
    public void show() throws Exception {
        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);
        final Product p4 = new Product("ES-0004", "Headset", "Headset HyperX Cloud Stinger", 299.99f);
        final Product p5 = new Product("ES-0005", "Webcam", "Webcam Logitech C920 HD", 450.00f);
        final Product p6 = new Product("ES-0006", "Notebook", "Notebook Acer Aspire 5", 3499.90f);

        this.productDAO.store(p1);
        this.productDAO.store(p2);
        this.productDAO.store(p3);
        this.productDAO.store(p4);
        this.productDAO.store(p5);
        this.productDAO.store(p6);

    }

    @Test
    public void index() throws Exception {
        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);
        final Product p4 = new Product("ES-0004", "Headset", "Headset HyperX Cloud Stinger", 299.99f);
        final Product p5 = new Product("ES-0005", "Webcam", "Webcam Logitech C920 HD", 450.00f);
        final Product p6 = new Product("ES-0006", "Notebook", "Notebook Acer Aspire 5", 3499.90f);

        this.productDAO.store(p1);
        this.productDAO.store(p2);
        this.productDAO.store(p3);
        this.productDAO.store(p4);
        this.productDAO.store(p5);
        this.productDAO.store(p6);

        Collection<Storage> storages = this.dao.index();

        Assert.assertEquals(6, storages.size());
    }

    @Test
    public void getByCode() throws Exception {
        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);
        final Product p4 = new Product("ES-0004", "Headset", "Headset HyperX Cloud Stinger", 299.99f);
        final Product p5 = new Product("ES-0005", "Webcam", "Webcam Logitech C920 HD", 450.00f);
        final Product p6 = new Product("ES-0006", "Notebook", "Notebook Acer Aspire 5", 3499.90f);

        this.productDAO.store(p1);
        this.productDAO.store(p2);
        this.productDAO.store(p3);
        this.productDAO.store(p4);
        this.productDAO.store(p5);
        this.productDAO.store(p6);

        final Storage storage = this.dao.getByCode(p2.getCode());

        Assert.assertNotNull(storage);
    }

    @Test
    public void setAmount() throws Exception {
        final int amount = 64;
        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);
        final Product p4 = new Product("ES-0004", "Headset", "Headset HyperX Cloud Stinger", 299.99f);
        final Product p5 = new Product("ES-0005", "Webcam", "Webcam Logitech C920 HD", 450.00f);
        final Product p6 = new Product("ES-0006", "Notebook", "Notebook Acer Aspire 5", 3499.90f);

        this.productDAO.store(p1);
        this.productDAO.store(p2);
        this.productDAO.store(p3);
        this.productDAO.store(p4);
        this.productDAO.store(p5);
        this.productDAO.store(p6);

        final int rs = this.dao.setAmount(p2.getCode(), amount);

        final Storage storage = this.dao.getByCode(p2.getCode());

        Assert.assertEquals(1, rs);
        Assert.assertEquals(amount, storage.getAmount());
    }

    @Test
    public void add() throws Exception {
        final int amount = 30;
        final int increment = 10;

        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);
        final Product p4 = new Product("ES-0004", "Headset", "Headset HyperX Cloud Stinger", 299.99f);
        final Product p5 = new Product("ES-0005", "Webcam", "Webcam Logitech C920 HD", 450.00f);
        final Product p6 = new Product("ES-0006", "Notebook", "Notebook Acer Aspire 5", 3499.90f);

        this.productDAO.store(p1);
        this.productDAO.store(p2);
        this.productDAO.store(p3);
        this.productDAO.store(p4);
        this.productDAO.store(p5);
        this.productDAO.store(p6);

        this.dao.setAmount(p5.getCode(), amount);

        int rs = this.dao.add(p5.getCode(), increment);

        final Storage storage = this.dao.getByCode(p5.getCode());

        Assert.assertEquals(1, rs);
        Assert.assertEquals(amount + increment, storage.getAmount());
    }

    @Test
    public void sub() throws Exception {
        final int amount = 30;
        final int subtract = 10;

        final Product p1 = new Product("ES-0001", "Mouse", "Mouse Logitech", 150.99f);
        final Product p2 = new Product("ES-0002", "Keyboard", "Teclado Mecânico RGB Corsair", 349.90f);
        final Product p3 = new Product("ES-0003", "Monitor", "Monitor Gamer 24'' Full HD Dell", 1299.00f);
        final Product p4 = new Product("ES-0004", "Headset", "Headset HyperX Cloud Stinger", 299.99f);
        final Product p5 = new Product("ES-0005", "Webcam", "Webcam Logitech C920 HD", 450.00f);
        final Product p6 = new Product("ES-0006", "Notebook", "Notebook Acer Aspire 5", 3499.90f);

        this.productDAO.store(p1);
        this.productDAO.store(p2);
        this.productDAO.store(p3);
        this.productDAO.store(p4);
        this.productDAO.store(p5);
        this.productDAO.store(p6);

        this.dao.setAmount(p5.getCode(), amount);

        int rs = this.dao.add(p5.getCode(), subtract);

        final Storage storage = this.dao.getByCode(p5.getCode());

        Assert.assertEquals(1, rs);
        Assert.assertEquals(amount + subtract, storage.getAmount());
        Assert.assertNotEquals(amount + subtract + 1, storage.getAmount());
    }
}
