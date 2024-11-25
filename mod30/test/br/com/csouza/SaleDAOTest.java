package br.com.csouza;

import br.com.csouza.dao.*;
import br.com.csouza.entities.Client;
import br.com.csouza.entities.Product;
import br.com.csouza.entities.Sale;
import br.com.csouza.entities.Status;
import br.com.csouza.interfaces.dao.*;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;

public class SaleDAOTest {
    private final ISaleDAO dao;
    private final IClientDAO clientDAO;
    private final IProductDAO productDAO;
    private final IStatusDAO statusDAO;
    private final IStorageDAO storageDAO;

    public SaleDAOTest() {
        this.dao = new SaleDAO();
        this.clientDAO = new ClientDAO();
        this.productDAO = new ProductDAO();
        this.statusDAO = new StatusDAO();
        this.storageDAO = new StorageDAO();
    }

    @After
    public void deleteAllRegisters() throws Exception {
        final Collection<Sale> sales = this.dao.index();
        final Collection<Client> clients = this.clientDAO.index();
        final Collection<Product> products = this.productDAO.index();

        for (Sale s : sales) {
            this.dao.destroy(s);
        }

        for (Client c : clients) {
            this.clientDAO.destroy(c);
        }

        for (Product p : products) {
            this.productDAO.destroy(p);
        }
    }

    @Test
    public void store() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        this.clientDAO.store(c1);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(1L);

        // Registrando a venda
        final Sale s1 = new Sale(c1, status);

        int rs = this.dao.store(s1);

        Assert.assertEquals(1, rs);
    }

    @Test
    public void show() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(1L);

        // Registrando as venda
        final Sale s1 = new Sale(c1, status);
        final Sale s2 = new Sale(c2, status);
        final Sale s3 = new Sale(c3, status);

        this.dao.store(s1);
        this.dao.store(s2);
        this.dao.store(s3);

        final Sale sale = this.dao.show(s2.getId());

        Assert.assertNotNull(sale);
        Assert.assertEquals(s2.getClient().getName(), sale.getClient().getName());
    }

    @Test
    public void index() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(1L);

        // Registrando as venda
        final Sale s1 = new Sale(c1, status);
        final Sale s2 = new Sale(c2, status);
        final Sale s3 = new Sale(c3, status);

        this.dao.store(s1);
        this.dao.store(s2);
        this.dao.store(s3);

        final Collection<Sale> sales = this.dao.index();

        Assert.assertEquals(3, sales.size());
    }

    @Test
    public void update() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(1L);

        // Obtendo o status "FINALIZADA"
        final Status status2 = this.statusDAO.show(2L);

        // Registrando as venda
        final Sale s1 = new Sale(c1, status);
        final Sale s2 = new Sale(c2, status);
        final Sale s3 = new Sale(c3, status);

        this.dao.store(s1);
        this.dao.store(s2);
        this.dao.store(s3);

        s2.setStatus(status2);

        final int rs = this.dao.update(s2);

        final Sale sale = this.dao.show(s2.getId());

        Assert.assertEquals(1, rs);
        Assert.assertEquals(s2.getStatus().getName(), sale.getStatus().getName());

    }

    @Test
    public void destroy() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(1L);

        // Registrando as venda
        final Sale s1 = new Sale(c1, status);
        final Sale s2 = new Sale(c2, status);
        final Sale s3 = new Sale(c3, status);

        this.dao.store(s1);
        this.dao.store(s2);
        this.dao.store(s3);

        final Collection<Sale> sales = this.dao.index();

        int amount = 0;

        for (Sale s : sales) {
            this.dao.destroy(s);
            amount++;
        }

        Assert.assertEquals(3, amount);
    }

    @Test
    public void open() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(2L);

        // Registrando as venda
        final Sale s1 = new Sale(c1, status);
        final Sale s2 = new Sale(c2, status);
        final Sale s3 = new Sale(c3, status);

        this.dao.store(s1);
        this.dao.store(s2);
        this.dao.store(s3);

        final int rs = this.dao.open(s3);
        final Sale sale = this.dao.show(s3.getId());

        Assert.assertEquals(1, rs);
        Assert.assertEquals("EM ABERTO", sale.getStatus().getName());
        Assert.assertTrue(sale.isOpen());
    }

    @Test
    public void finish() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(1L);

        // Registrando as venda
        final Sale s1 = new Sale(c1, status);
        final Sale s2 = new Sale(c2, status);
        final Sale s3 = new Sale(c3, status);

        this.dao.store(s1);
        this.dao.store(s2);
        this.dao.store(s3);

        final int rs = this.dao.finish(s3);
        final Sale sale = this.dao.show(s3.getId());

        Assert.assertEquals(1, rs);
        Assert.assertEquals("FINALIZADA", sale.getStatus().getName());
        Assert.assertTrue(sale.isFinished());
    }

    @Test
    public void cancel() throws Exception {
        // Registrando o usuário e obtendo seu ID de registro.
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);

        this.clientDAO.store(c1);
        this.clientDAO.store(c2);
        this.clientDAO.store(c3);

        // Obtendo o status "EM ABERTO"
        final Status status = this.statusDAO.show(1L);

        // Registrando as venda
        final Sale s1 = new Sale(c1, status);
        final Sale s2 = new Sale(c2, status);
        final Sale s3 = new Sale(c3, status);

        this.dao.store(s1);
        this.dao.store(s2);
        this.dao.store(s3);

        final int rs = this.dao.cancel(s3);
        final Sale sale = this.dao.show(s3.getId());

        Assert.assertEquals(1, rs);
        Assert.assertEquals("CANCELADA", sale.getStatus().getName());
        Assert.assertTrue(sale.isClosed());
    }

    @Test
    public void buy() throws Exception {
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        this.clientDAO.store(c1);

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


        this.storageDAO.setAmount(p1.getCode(), 435);
        this.storageDAO.setAmount(p2.getCode(), 12);
        this.storageDAO.setAmount(p3.getCode(), 981);
        this.storageDAO.setAmount(p4.getCode(), 43);
        this.storageDAO.setAmount(p5.getCode(), 64);
        this.storageDAO.setAmount(p6.getCode(), 12);

        final Status status = this.statusDAO.show(1L);

        final Sale sale = new Sale(c1, status);

        this.dao.store(sale);

        final int rs = this.dao.addProducts(sale, p1, 43);

        Assert.assertEquals(1, rs);
    }
}
