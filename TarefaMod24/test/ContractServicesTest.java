import br.com.csouza.dao.ClientDAOMock;
import br.com.csouza.dao.ContractDAOMock;
import br.com.csouza.entity.Client;
import br.com.csouza.entity.Contract;
import br.com.csouza.services.ClientService;
import br.com.csouza.services.ContractService;
import org.junit.Assert;
import org.junit.Test;

public class ContractServicesTest {
    private final ClientService clientService = new ClientService(new ClientDAOMock());
    private final ContractService contractService = new ContractService(new ContractDAOMock());

    @Test
    public void hasContractTest() {
        final Client cl1 = new Client("Caique", "Souza", 21);

        clientService.store(cl1);

        final Contract c1 = new Contract("Contrato para locação de casa", this.clientService.show("CL-1"));

        this.contractService.store(c1);

        final boolean r1 = this.contractService.hasRegister("CO-1");
        final boolean r2 = this.contractService.hasRegister("CO-423");

        Assert.assertTrue(r1);
        Assert.assertFalse(r2);
    }

    @Test
    public void contractIdAlreadyRegisteredTest() {
        final Client cl1 = new Client("Caique", "Souza", 21);
        final Client cl2 = new Client("Caique", "Souza", 21);

        cl2.setId("CL-1");

        clientService.store(cl1);
        clientService.store(cl2);

        final Contract c1 = new Contract("Contrato para locação de casa", this.clientService.show("CL-1"));
        final Contract c2 = new Contract("Contrato para venda de carro", this.clientService.show("CL-2"));

        c2.setId("CO-1");

        final boolean resultSucess = this.contractService.store(c1);
        final boolean resultNoSucess = this.contractService.store(c2);

        Assert.assertTrue(resultSucess);
        Assert.assertFalse(resultNoSucess);
    }

    @Test
    public void storeTest() {
        final Client client = new Client("Caique", "Souza", 21);

        clientService.store(client);

        final Contract contract = new Contract("Contrato para locação de casa", clientService.show("CL-1"));

        final boolean result = this.contractService.store(contract);

        Assert.assertTrue(result);
    }

    @Test
    public void showTest() {
        final Client cl1 = new Client("Caique", "Souza", 21);
        final Client cl2 = new Client("Mariana", "Ferreira", 30);
        final Client cl3 = new Client("João", "Oliveira", 25);
        final Client cl4 = new Client("Beatriz", "Santos", 28);

        this.clientService.store(cl1);
        this.clientService.store(cl2);
        this.clientService.store(cl3);
        this.clientService.store(cl4);

        final Contract c1 = new Contract("Contrato para locação de casa", this.clientService.show("CL-1"));
        final Contract c2 = new Contract("Contrato para venda de carro", this.clientService.show("CL-2"));
        final Contract c3 = new Contract("Contrato para venda de viagem", this.clientService.show("CL-3"));
        final Contract c4 = new Contract("Contrato para apresentar ao cliente", this.clientService.show("CL-4"));

        this.contractService.store(c1);
        this.contractService.store(c2);
        this.contractService.store(c3);
        this.contractService.store(c4);

        final Contract contract1 = this.contractService.show("CO-3");
        final Contract contract2 = this.contractService.show("CO-4235");

        Assert.assertEquals("CO-3", contract1.getId());
        Assert.assertNull(contract2);
    }

    @Test
    public void indexTest() {
        final Client cl1 = new Client("Caique", "Souza", 21);
        final Client cl2 = new Client("Mariana", "Ferreira", 30);
        final Client cl3 = new Client("João", "Oliveira", 25);
        final Client cl4 = new Client("Beatriz", "Santos", 28);

        this.clientService.store(cl1);
        this.clientService.store(cl2);
        this.clientService.store(cl3);
        this.clientService.store(cl4);

        final Contract c1 = new Contract("Contrato para locação de casa", this.clientService.show("CL-1"));
        final Contract c2 = new Contract("Contrato para venda de carro", this.clientService.show("CL-2"));
        final Contract c3 = new Contract("Contrato para venda de viagem", this.clientService.show("CL-3"));
        final Contract c4 = new Contract("Contrato para apresentar ao cliente", this.clientService.show("CL-4"));

        this.contractService.store(c1);
        this.contractService.store(c2);
        this.contractService.store(c3);
        this.contractService.store(c4);

        Assert.assertTrue(this.contractService.index().size() > 0);
        Assert.assertEquals(4, this.clientService.index().size());
    }

    @Test
    public void updateTest() {
        final String newContractName = "Contrato atualizado";
        final String clientName = "Jerson";
        final String clientSurname = "Oliveira";
        final int clientAge = 42;

        final Client cl1 = new Client("Caique", "Souza", 21);
        final Client cl2 = new Client("Mariana", "Ferreira", 30);
        final Client cl3 = new Client("João", "Oliveira", 25);
        final Client cl4 = new Client("Beatriz", "Santos", 28);

        this.clientService.store(cl1);
        this.clientService.store(cl2);
        this.clientService.store(cl3);
        this.clientService.store(cl4);

        final Contract c1 = new Contract("Contrato para locação de casa", this.clientService.show("CL-1"));
        final Contract c2 = new Contract("Contrato para venda de carro", this.clientService.show("CL-2"));
        final Contract c3 = new Contract("Contrato para venda de viagem", this.clientService.show("CL-3"));
        final Contract c4 = new Contract("Contrato para apresentar ao cliente", this.clientService.show("CL-4"));

        this.contractService.store(c1);
        this.contractService.store(c2);
        this.contractService.store(c3);
        this.contractService.store(c4);

        final Client newClient = new Client(clientName, clientSurname, clientAge);
        final Contract newContract = new Contract(newContractName, newClient);

        final boolean result1 = this.contractService.update(c2.getId(), newContract);
        final boolean result2 = this.contractService.update("CO-5234", newContract);

        final Contract updatedContract = this.contractService.show(c2.getId());

        Assert.assertTrue(result1);
        Assert.assertFalse(result2);

        Assert.assertEquals(newContractName, updatedContract.getName());
        Assert.assertEquals(clientName, updatedContract.getClient().getName());
        Assert.assertEquals(clientSurname, updatedContract.getClient().getSurname());
        Assert.assertEquals(clientAge, updatedContract.getClient().getAge());
    }

    @Test
    public void destroyTest() {
        final Client cl1 = new Client("Caique", "Souza", 21);
        final Client cl2 = new Client("Mariana", "Ferreira", 30);
        final Client cl3 = new Client("João", "Oliveira", 25);
        final Client cl4 = new Client("Beatriz", "Santos", 28);

        this.clientService.store(cl1);
        this.clientService.store(cl2);
        this.clientService.store(cl3);
        this.clientService.store(cl4);

        final Contract c1 = new Contract("Contrato para locação de casa", this.clientService.show("CL-1"));
        final Contract c2 = new Contract("Contrato para venda de carro", this.clientService.show("CL-2"));
        final Contract c3 = new Contract("Contrato para venda de viagem", this.clientService.show("CL-3"));
        final Contract c4 = new Contract("Contrato para apresentar ao cliente", this.clientService.show("CL-4"));

        this.contractService.store(c1);
        this.contractService.store(c2);
        this.contractService.store(c3);
        this.contractService.store(c4);

        final boolean result1 = this.contractService.destroy(c2.getId());
        final boolean result2 = this.contractService.destroy(c2.getId());

        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
        Assert.assertEquals(3, this.contractService.index().size());
    }
}
