package br.com.csouza;

import br.com.csouza.dao.mocks.ProductDAOMock;
import br.com.csouza.dao.mocks.SingletonDatabaseMock;
import br.com.csouza.entities.Product;
import br.com.csouza.interfaces.IProductDAO;
import br.com.csouza.utils.GetTableName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class ProductDAOTest {
    private final IProductDAO dao;

    public ProductDAOTest() {
        this.dao = new ProductDAOMock();
    }

    @After
    public void databaseClear() throws Exception {
        SingletonDatabaseMock.clear(GetTableName.getTableNameClass(Product.class));
    }

    @Test
    public void storeTest() throws Exception {

        final Product p1 = new Product("Mouse", 41.5f, 2);

        final boolean result = dao.store(p1);

        Assert.assertTrue(result);
    }

    @Test
    public void showTest() throws Exception {
        final Product p1 = new Product("Mouse", 41.5f, 2);
        final Product p2 = new Product("Monitor", 54.6f, 64);
        final Product p3 = new Product("Teclado", 32.5f, 2);
        final Product p4 = new Product("Suporte p monitor", 20.76f, 75);

        dao.store(p1);
        dao.store(p2);
        dao.store(p3);
        dao.store(p4);

        final Product product = dao.show(3L);

        Assert.assertEquals(p3, product);
    }

    @Test
    public void indexTest() throws Exception {
        final Product p1 = new Product("Mouse", 41.5f, 2);
        final Product p2 = new Product("Monitor", 54.6f, 64);
        final Product p3 = new Product("Teclado", 32.5f, 2);
        final Product p4 = new Product("Suporte p monitor", 20.76f, 75);

        dao.store(p1);
        dao.store(p2);
        dao.store(p3);
        dao.store(p4);

        Collection<Product> productCollection = dao.index();

        Assert.assertEquals(4, productCollection.size());
    }

    @Test
    public void updateTest() throws Exception {
        final Product p1 = new Product("Mouse", 41.5f, 2);
        final Product p2 = new Product("Monitor", 54.6f, 64);
        final Product p3 = new Product("Teclado", 32.5f, 2);
        final Product p4 = new Product("Suporte p monitor", 20.76f, 75);

        dao.store(p1);
        dao.store(p2);
        dao.store(p3);
        dao.store(p4);

        final boolean r1 = dao.update(1L, new Product("Placa de vídeo", 512, 52));
        final boolean r2 = dao.update(3L, new Product("Memória RAM", 10, 38));

        Assert.assertTrue(r1);
        Assert.assertTrue(r2);
    }

    @Test
    public void destroyTest() throws Exception {
        final Product p1 = new Product("Mouse", 41.5f, 2);
        final Product p2 = new Product("Monitor", 54.6f, 64);
        final Product p3 = new Product("Teclado", 32.5f, 2);
        final Product p4 = new Product("Suporte p monitor", 20.76f, 75);

        dao.store(p1);
        dao.store(p2);
        dao.store(p3);
        dao.store(p4);

        final boolean r1 = dao.destroy(3L);

        Assert.assertTrue(r1);
        Assert.assertEquals(3, dao.index().size());
    }
}