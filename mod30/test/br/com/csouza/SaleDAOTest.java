package br.com.csouza;

import br.com.csouza.dao.ClientDAO;
import br.com.csouza.dao.SaleDAO;
import br.com.csouza.dao.StatusDAO;
import br.com.csouza.entities.Client;
import br.com.csouza.entities.Sale;
import br.com.csouza.entities.Status;
import br.com.csouza.interfaces.dao.IClientDAO;
import br.com.csouza.interfaces.dao.ISaleDAO;
import br.com.csouza.interfaces.dao.IStatusDAO;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;

public class SaleDAOTest {
    private ISaleDAO dao;
    private IClientDAO clientDAO;
    private IStatusDAO statusDAO;

    public SaleDAOTest() {
        this.dao = new SaleDAO();
        this.clientDAO = new ClientDAO();
        this.statusDAO = new StatusDAO();
    }

    @After
    public void deleteAllRegisters() throws Exception {
        final Collection<Sale> sales = this.dao.index();
        final Collection<Client> clients = this.clientDAO.index();

        for (Sale s : sales) {
            this.dao.destroy(s);
        }

        for (Client c : clients) {
            this.clientDAO.destroy(c);
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
    }
}
