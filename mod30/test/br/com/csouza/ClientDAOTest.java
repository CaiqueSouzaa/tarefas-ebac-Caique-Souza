package br.com.csouza;

import br.com.csouza.dao.ClientDAO;
import br.com.csouza.entities.Client;
import br.com.csouza.interfaces.dao.IClientDAO;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;

public class ClientDAOTest {
    private final IClientDAO dao;

    public ClientDAOTest() {
        this.dao = new ClientDAO();
    }

    @After
    public void deleteData() throws Exception {
        final Collection<Client> clients = this.dao.index();
        for (Client c : clients) {
            this.dao.destroy(c);
        }
    }

    @Test
    public void store() throws Exception {
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);

        int result = this.dao.store(c1);

        Assert.assertEquals(1, result);
    }

    @Test
    public void show() throws Exception {
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);
        final Client c4 = new Client("Ana", "Santos", "37485930493", "11934567890", "ana.santos@gmail.com", 28);
        final Client c5 = new Client("Carlos", "Oliveira", "48593028475", "11954321987", "carlos.oliveira@gmail.com", 35);
        final Client c6 = new Client("Beatriz", "Costa", "58392048321", "11943215678", "beatriz.costa@gmail.com", 22);

        this.dao.store(c1);
        this.dao.store(c2);
        this.dao.store(c3);
        this.dao.store(c4);
        this.dao.store(c5);
        this.dao.store(c6);

        Client client = this.dao.show(c5.getId());

        Assert.assertEquals(c5.getId(), client.getId());
    }

    @Test
    public void index() throws Exception {
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);
        final Client c4 = new Client("Ana", "Santos", "37485930493", "11934567890", "ana.santos@gmail.com", 28);
        final Client c5 = new Client("Carlos", "Oliveira", "48593028475", "11954321987", "carlos.oliveira@gmail.com", 35);
        final Client c6 = new Client("Beatriz", "Costa", "58392048321", "11943215678", "beatriz.costa@gmail.com", 22);

        this.dao.store(c1);
        this.dao.store(c2);
        this.dao.store(c3);
        this.dao.store(c4);
        this.dao.store(c5);
        this.dao.store(c6);

        final Collection<Client> clients = this.dao.index();

        Assert.assertEquals(6, clients.size());
    }

    @Test
    public void update() throws Exception {
        final Client c1 = new Client("Caique", "Souza", "48574829584", "11947395753", "caique@gmail.com", 21);
        final Client c2 = new Client("Maria", "Silva", "52849584329", "11987654321", "maria.silva@gmail.com", 30);
        final Client c3 = new Client("João", "Pereira", "74839254832", "11965437821", "joao.pereira@gmail.com", 25);
        final Client c4 = new Client("Ana", "Santos", "37485930493", "11934567890", "ana.santos@gmail.com", 28);
        final Client c5 = new Client("Carlos", "Oliveira", "48593028475", "11954321987", "carlos.oliveira@gmail.com", 35);
        final Client c6 = new Client("Beatriz", "Costa", "58392048321", "11943215678", "beatriz.costa@gmail.com", 22);

        this.dao.store(c1);
        this.dao.store(c2);
        this.dao.store(c3);
        this.dao.store(c4);
        this.dao.store(c5);
        this.dao.store(c6);

        final Client newData = c4;

        newData.setName("Ana Paula");
        newData.setSurname("De Camargo");
        newData.setEmail("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        final int result = this.dao.update(newData);

        Client client = this.dao.show(c4.getId());

        Assert.assertEquals(1, result);
        Assert.assertEquals(c4.getId(), client.getId());
    }

    @Test
    public void destroy() throws Exception {
        final Client c1 = new Client("Caique", "Souza", "485d48s2984", "11947395753", "caiquce@gmail.com", 21);


        this.dao.store(c1);

        int result = this.dao.destroy(c1);

        Assert.assertEquals(1, result);
    }
}