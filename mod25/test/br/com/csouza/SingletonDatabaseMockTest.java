package br.com.csouza;

import br.com.csouza.dao.mocks.SingletonDatabaseMock;
import br.com.csouza.entities.DatabaseObject;
import br.com.csouza.entities.Product;
import br.com.csouza.exceptions.WithoutDatabaseMethodsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

public class SingletonDatabaseMockTest {
    @Before
    public void init() {
        SingletonDatabaseMock.init();
    }

    @Test
    public void createTableTest() {
        final String tn1 = "produtos";
        final String tn2 = "clientes";
        final String tn3 = "notas_fiscais";
        final boolean r1 = SingletonDatabaseMock.newTable(tn1);
        final boolean r2 = SingletonDatabaseMock.newTable(tn2);
        final boolean r3 = SingletonDatabaseMock.newTable(tn3);

        Assert.assertTrue(r1);
        Assert.assertTrue(r2);
        Assert.assertTrue(r3);
    }

    @Test
    public void getTablesTest() {
        Assert.assertNotNull(SingletonDatabaseMock.getTables());
    }

    @Test
    public void addItemTest() throws Exception {
        final String tn1 = "produtos";
        final String tn2 = "clientes";

        class Client extends DatabaseObject {
            @Override
            public String toString() {
                return "Client: {'id': " + this.getId() + "}";
            }
        }

        final Client c1 = new Client();
        final Client c2 = new Client();
        final Client c3 = new Client();

        final Product p1 = new Product("Mouse", 23.9f, 4);
        final Product p2 = new Product("Mouse", 23.9f, 4);
        final Product p3 = new Product("Mouse", 23.9f, 4);
        final Product p4 = new Product("Mouse", 23.9f, 4);


        SingletonDatabaseMock.newTable(tn1);
        SingletonDatabaseMock.newTable(tn2);

        final boolean r1 = SingletonDatabaseMock.add(tn1, p1);
        final boolean r2 = SingletonDatabaseMock.add(tn1, p2);
        final boolean r3 = SingletonDatabaseMock.add(tn1, p3);
        final boolean r4 = SingletonDatabaseMock.add(tn1, p4);

        final boolean r5 = SingletonDatabaseMock.add(tn2, c1);
        final boolean r6 = SingletonDatabaseMock.add(tn2, c2);
        final boolean r7 = SingletonDatabaseMock.add(tn2, c3);

        Assert.assertTrue(r1);
        Assert.assertTrue(r2);
        Assert.assertTrue(r3);
        Assert.assertTrue(r4);
        Assert.assertTrue(r5);
        Assert.assertTrue(r6);
        Assert.assertTrue(r7);

        Assert.assertEquals(4, SingletonDatabaseMock.getTable(tn1).size());
        Assert.assertEquals(3, SingletonDatabaseMock.getTable(tn2).size());
    }

    @Test(expected = WithoutDatabaseMethodsException.class)
    public void addItemTestException() throws Exception {
        final String tn2 = "clientes";

        class Client {}

        final Client c1 = new Client();

        SingletonDatabaseMock.newTable(tn2);
        SingletonDatabaseMock.add(tn2, c1);
    }

    @Test
    public void getItemTest() throws Exception {
        final String tn1 = "produtos";
        final String tn2 = "clientes";

        class Client extends DatabaseObject {
            @Override
            public String toString() {
                return "Client: {'id': " + this.getId() + "}";
            }
        }

        final Client c1 = new Client();
        final Client c2 = new Client();
        final Client c3 = new Client();

        final Product p1 = new Product("Mouse", 23.9f, 4);
        final Product p2 = new Product("Mouse", 23.9f, 4);
        final Product p3 = new Product("Mouse", 23.9f, 4);
        final Product p4 = new Product("Mouse", 23.9f, 4);


        SingletonDatabaseMock.newTable(tn1);
        SingletonDatabaseMock.newTable(tn2);

        SingletonDatabaseMock.add(tn1, p1);
        SingletonDatabaseMock.add(tn1, p2);
        SingletonDatabaseMock.add(tn1, p3);
        SingletonDatabaseMock.add(tn1, p4);

        SingletonDatabaseMock.add(tn2, c1);
        SingletonDatabaseMock.add(tn2, c2);
        SingletonDatabaseMock.add(tn2, c3);

        Optional<Object> item = SingletonDatabaseMock.getItem(tn1, 3);

        Product product = null;

        if (item.isPresent()) {
            product = (Product) item.get();
        }

        Assert.assertNotNull(product);
        Assert.assertEquals(p3.getId(), product.getId());
        Assert.assertEquals(p3.getName(), product.getName());
        Assert.assertEquals(String.valueOf(p3.getPrice()), String.valueOf(product.getPrice()));
        Assert.assertEquals(p3.getAmount(), product.getAmount());
    }

    @Test
    public void clearTest() throws Exception {
        final String tn1 = "produtos";
        final String tn2 = "clientes";

        class Client extends DatabaseObject {
            @Override
            public String toString() {
                return "Client: {'id': " + this.getId() + "}";
            }
        }

        final Client c1 = new Client();
        final Client c2 = new Client();
        final Client c3 = new Client();

        final Product p1 = new Product("Mouse", 41.5f, 2);
        final Product p2 = new Product("Monitor", 54.6f, 64);
        final Product p3 = new Product("Teclado", 32.5f, 2);
        final Product p4 = new Product("Suporte p monitor", 20.76f, 75);


        SingletonDatabaseMock.newTable(tn1);
        SingletonDatabaseMock.newTable(tn2);

        SingletonDatabaseMock.add(tn1, p1);
        SingletonDatabaseMock.add(tn1, p2);
        SingletonDatabaseMock.add(tn1, p3);
        SingletonDatabaseMock.add(tn1, p4);

        SingletonDatabaseMock.add(tn2, c1);
        SingletonDatabaseMock.add(tn2, c2);
        SingletonDatabaseMock.add(tn2, c3);

        final boolean result = SingletonDatabaseMock.clear(tn1);

        Assert.assertTrue(result);
        Assert.assertEquals(0, SingletonDatabaseMock.getTable(tn1).size());
    }

    @Test
    public void updateTest() throws Exception {
        final String tn1 = "produtos";
        final String tn2 = "clientes";

        class Client extends DatabaseObject {
            private String name;

            public Client(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Client: {'id': " + this.getId() + ", 'name': " + this.name + "}";
            }
        }

        final Client c1 = new Client("Caique");
        final Client c2 = new Client("Rodrigo");
        final Client c3 = new Client("Antonio Cardoso");

        final Product p1 = new Product("Mouse", 41.5f, 2);
        final Product p2 = new Product("Monitor", 54.6f, 64);
        final Product p3 = new Product("Teclado", 32.5f, 2);
        final Product p4 = new Product("Suporte p monitor", 20.76f, 75);


        SingletonDatabaseMock.newTable(tn1);
        SingletonDatabaseMock.newTable(tn2);

        SingletonDatabaseMock.add(tn1, p1);
        SingletonDatabaseMock.add(tn1, p2);
        SingletonDatabaseMock.add(tn1, p3);
        SingletonDatabaseMock.add(tn1, p4);

        SingletonDatabaseMock.add(tn2, c1);
        SingletonDatabaseMock.add(tn2, c2);
        SingletonDatabaseMock.add(tn2, c3);

        final boolean r1 = SingletonDatabaseMock.update(tn1, 1, new Product("Gabine", 87.0f, 421));
        final boolean r2 = SingletonDatabaseMock.update(tn2, 2, new Client("Júlia"));

        Assert.assertTrue(r1);
        Assert.assertTrue(r2);
    }

    @Test
    public void deleteTest() throws Exception {
        final String tn1 = "produtos";
        final String tn2 = "clientes";

        class Client extends DatabaseObject {
            private String name;

            public Client(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Client: {'id': " + this.getId() + ", 'name': " + this.name + "}";
            }
        }

        final Client c1 = new Client("Caique");
        final Client c2 = new Client("Rodrigo");
        final Client c3 = new Client("Antonio Cardoso");

        final Product p1 = new Product("Mouse", 41.5f, 2);
        final Product p2 = new Product("Monitor", 54.6f, 64);
        final Product p3 = new Product("Teclado", 32.5f, 2);
        final Product p4 = new Product("Suporte p monitor", 20.76f, 75);


        SingletonDatabaseMock.newTable(tn1);
        SingletonDatabaseMock.newTable(tn2);

        SingletonDatabaseMock.add(tn1, p1);
        SingletonDatabaseMock.add(tn1, p2);
        SingletonDatabaseMock.add(tn1, p3);
        SingletonDatabaseMock.add(tn1, p4);

        SingletonDatabaseMock.add(tn2, c1);
        SingletonDatabaseMock.add(tn2, c2);
        SingletonDatabaseMock.add(tn2, c3);

        final boolean r1 = SingletonDatabaseMock.delete(tn1, 2);

        Assert.assertTrue(r1);
        Assert.assertEquals(3, SingletonDatabaseMock.getTable(tn1).size());
    }
}
