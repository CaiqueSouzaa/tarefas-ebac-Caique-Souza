import br.com.csouza.entity.Client;
import br.com.csouza.interfaces.IClientDAO;
import org.junit.Test;
import org.junit.Assert;

import br.com.csouza.dao.ClientDAOMock;

public class ClientDAOTest {
    private final IClientDAO clientDAO = new ClientDAOMock();

    @Test
    public void hasClientTest() {
        final Client c1 = new Client("Caique", "Souza", 21);

        clientDAO.store(c1);

        final boolean r1 = clientDAO.hasRegister("CL-1");
        final boolean r2 = clientDAO.hasRegister("CL-423");

        Assert.assertTrue(r1);
        Assert.assertFalse(r2);
    }

    @Test
    public void clientIdAlreadyRegisteredTest() {
        final Client c1 = new Client("Caique", "Souza", 21);
        final Client c2 = new Client("Caique", "Souza", 21);

        c2.setId("CL-1");

        final boolean resultSucess = clientDAO.store(c1);
        final boolean resultNoSucess = clientDAO.store(c2);

        Assert.assertTrue(resultSucess);
        Assert.assertFalse(resultNoSucess);
    }

    @Test
    public void storeTest() {
        final Client c1 = new Client("Caique", "Souza", 21);

        final boolean result = clientDAO.store(c1);

        Assert.assertTrue(result);
    }

    @Test
    public void showTest() {
        final Client c1 = new Client("Caique", "Souza", 21);
        final Client c2 = new Client("Mariana", "Ferreira", 30);
        final Client c3 = new Client("João", "Oliveira", 25);
        final Client c4 = new Client("Beatriz", "Santos", 28);
        final Client c5 = new Client("Lucas", "Silva", 22);
        final Client c6 = new Client("Ana", "Costa", 27);

        clientDAO.store(c1);
        clientDAO.store(c2);
        clientDAO.store(c3);
        clientDAO.store(c4);
        clientDAO.store(c5);
        clientDAO.store(c6);

        final Client client1 = clientDAO.show("CL-4");
        final Client client2 = clientDAO.show("CL-5342");

        Assert.assertEquals(c4.getId(), client1.getId());
        Assert.assertNull(client2);
    }

    @Test
    public void indexTest() {
        final Client c1 = new Client("Caique", "Souza", 21);
        final Client c2 = new Client("Mariana", "Ferreira", 30);
        final Client c3 = new Client("João", "Oliveira", 25);
        final Client c4 = new Client("Beatriz", "Santos", 28);
        final Client c5 = new Client("Lucas", "Silva", 22);
        final Client c6 = new Client("Ana", "Costa", 27);

        clientDAO.store(c1);
        clientDAO.store(c2);
        clientDAO.store(c3);
        clientDAO.store(c4);
        clientDAO.store(c5);
        clientDAO.store(c6);

        Assert.assertTrue(clientDAO.index().size() > 0);
        Assert.assertEquals(6, clientDAO.index().size());
    }

    @Test
    public void updateTest() {
        final String name = "Bernardo";
        final String surname = "Campos";
        final int age = 54;

        final Client c1 = new Client("Caique", "Souza", 21);
        final Client c2 = new Client("Mariana", "Ferreira", 30);
        final Client c3 = new Client("João", "Oliveira", 25);
        final Client c4 = new Client("Beatriz", "Santos", 28);
        final Client c5 = new Client("Lucas", "Silva", 22);
        final Client c6 = new Client("Ana", "Costa", 27);

        clientDAO.store(c1);
        clientDAO.store(c2);
        clientDAO.store(c3);
        clientDAO.store(c4);
        clientDAO.store(c5);
        clientDAO.store(c6);

        final boolean result1 = clientDAO.update(c5.getId(), new Client(name, surname, age));
        final boolean result2 = clientDAO.update("CL-2345", new Client(name, surname, age));

        final Client updatedClient = clientDAO.show(c5.getId());

        Assert.assertTrue(result1);
        Assert.assertEquals("CL-5", updatedClient.getId());
        Assert.assertEquals(name, updatedClient.getName());
        Assert.assertEquals(surname, updatedClient.getSurname());
        Assert.assertFalse(result2);
    }

    @Test
    public void destroyTest() {
        final Client c1 = new Client("Caique", "Souza", 21);
        final Client c2 = new Client("Mariana", "Ferreira", 30);
        final Client c3 = new Client("João", "Oliveira", 25);
        final Client c4 = new Client("Beatriz", "Santos", 28);
        final Client c5 = new Client("Lucas", "Silva", 22);
        final Client c6 = new Client("Ana", "Costa", 27);

        clientDAO.store(c1);
        clientDAO.store(c2);
        clientDAO.store(c3);
        clientDAO.store(c4);
        clientDAO.store(c5);
        clientDAO.store(c6);

        final boolean r1 = clientDAO.destroy(c2.getId());
        final boolean r2 = clientDAO.destroy(c2.getId());

        Assert.assertTrue(r1);
        Assert.assertFalse(r2);
        Assert.assertEquals(5, clientDAO.index().size());
    }
}
