package br.com.csouza;

import br.com.csouza.dao.mocks.ClientDAOMock;
import br.com.csouza.dao.mocks.SingletonDatabaseMock;
import br.com.csouza.entities.Address;
import br.com.csouza.entities.City;
import br.com.csouza.entities.Client;
import br.com.csouza.interfaces.IClientDAO;
import br.com.csouza.utils.GetTableName;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

public class ClientDAOTest {
    private final IClientDAO dao;

    public ClientDAOTest() {
        this.dao = new ClientDAOMock();
    }

    @After
    public void clear() throws Exception {
        SingletonDatabaseMock.clear(GetTableName.getTableNameClass(Client.class));
    }

    @Test
    public void storeTest() throws Exception {
        final Client c1 = new Client("Caique", "Souza", "123.321.123.53", "987654321", 21);
        final Address c1Address = new Address("R. Dos Alecrins", "13");
        final City saoPaulo = new City("São Paulo", "São Paulo", "SP");

        c1Address.setCity(saoPaulo);
        c1.setAddress(c1Address);

        boolean r1 = this.dao.store(c1);

        Assert.assertTrue(r1);
    }

    @Test
    public void showTest() throws Exception {
        // Clientes
        final Client c1 = new Client("Caique", "Souza", "123.321.123.53", "987654321", 21);
        final Client c2 = new Client("Mariana", "Alves", "321.123.321.12", "123456789", 25);
        final Client c3 = new Client("João", "Silva", "456.789.456.23", "987654321", 30);
        final Client c4 = new Client("Fernanda", "Costa", "789.456.789.34", "456789123", 28);
        final Client c5 = new Client("Lucas", "Pereira", "159.753.159.46", "654321987", 22);
        final Client c6 = new Client("Aline", "Ferreira", "951.357.951.78", "321987654", 26);

        // Cidades
        final City cabreuva = new City("Cabreúva", "São Paulo", "SP");
        final City campinas = new City("Campinas", "São Paulo", "SP");
        final City salvador = new City("Salvador", "Bahia", "BA");
        final City curitiba = new City("Curitiba", "Paraná", "PR");

        // Endereços
        final Address c1Address = new Address("R. Dos Alecrins", "13");
        final Address c2Address = new Address("Av. Paulista", "1578");
        final Address c3Address = new Address("R. das Flores", "45");
        final Address c4Address = new Address("R. dos Lírios", "22B");
        final Address c5Address = new Address("Av. Rio Branco", "102");
        final Address c6Address = new Address("R. Dom Pedro II", "350");

        // Atribuindo as cidades aos endereços
        c1Address.setCity(cabreuva);
        c2Address.setCity(campinas);
        c3Address.setCity(salvador);
        c4Address.setCity(curitiba);
        c5Address.setCity(campinas);
        c6Address.setCity(curitiba);

        // Atribuindo os endereços aos clientes
        c1.setAddress(c1Address);
        c2.setAddress(c2Address);
        c3.setAddress(c3Address);
        c4.setAddress(c4Address);
        c5.setAddress(c5Address);
        c6.setAddress(c6Address);

        // Registrando os clientes
        this.dao.store(c1);
        this.dao.store(c2);
        this.dao.store(c3);
        this.dao.store(c4);
        this.dao.store(c5);
        this.dao.store(c6);

        // Buscando por um único cliente
        final Client client1 = this.dao.show(4L);
        final Client client2 = this.dao.show(2L);

        // Checando o "client1"
        Assert.assertNotNull(client1);
        Assert.assertEquals(c4, client1);

        // Checando o "client2"
        Assert.assertNotNull(client2);
        Assert.assertEquals(c2, client2);
    }

    @Test
    public void indexTest() throws Exception {
        // Clientes
        final Client c1 = new Client("Caique", "Souza", "123.321.123.53", "987654321", 21);
        final Client c2 = new Client("Mariana", "Alves", "321.123.321.12", "123456789", 25);
        final Client c3 = new Client("João", "Silva", "456.789.456.23", "987654321", 30);
        final Client c4 = new Client("Fernanda", "Costa", "789.456.789.34", "456789123", 28);
        final Client c5 = new Client("Lucas", "Pereira", "159.753.159.46", "654321987", 22);
        final Client c6 = new Client("Aline", "Ferreira", "951.357.951.78", "321987654", 26);

        // Cidades
        final City cabreuva = new City("Cabreúva", "São Paulo", "SP");
        final City campinas = new City("Campinas", "São Paulo", "SP");
        final City salvador = new City("Salvador", "Bahia", "BA");
        final City curitiba = new City("Curitiba", "Paraná", "PR");

        // Endereços
        final Address c1Address = new Address("R. Dos Alecrins", "13");
        final Address c2Address = new Address("Av. Paulista", "1578");
        final Address c3Address = new Address("R. das Flores", "45");
        final Address c4Address = new Address("R. dos Lírios", "22B");
        final Address c5Address = new Address("Av. Rio Branco", "102");
        final Address c6Address = new Address("R. Dom Pedro II", "350");

        // Atribuindo as cidades aos endereços
        c1Address.setCity(cabreuva);
        c2Address.setCity(campinas);
        c3Address.setCity(salvador);
        c4Address.setCity(curitiba);
        c5Address.setCity(campinas);
        c6Address.setCity(curitiba);

        // Atribuindo os endereços aos clientes
        c1.setAddress(c1Address);
        c2.setAddress(c2Address);
        c3.setAddress(c3Address);
        c4.setAddress(c4Address);
        c5.setAddress(c5Address);
        c6.setAddress(c6Address);

        // Registrando os clientes
        this.dao.store(c1);
        this.dao.store(c2);
        this.dao.store(c3);
        this.dao.store(c4);
        this.dao.store(c5);
        this.dao.store(c6);

        Assert.assertEquals(6, this.dao.index().size());
    }

    @Test
    public void updateTest() throws Exception {
        // Clientes
        final Client c1 = new Client("Caique", "Souza", "123.321.123.53", "987654321", 21);
        final Client c2 = new Client("Mariana", "Alves", "321.123.321.12", "123456789", 25);
        final Client c3 = new Client("João", "Silva", "456.789.456.23", "987654321", 30);
        final Client c4 = new Client("Fernanda", "Costa", "789.456.789.34", "456789123", 28);
        final Client c5 = new Client("Lucas", "Pereira", "159.753.159.46", "654321987", 22);
        final Client c6 = new Client("Aline", "Ferreira", "951.357.951.78", "321987654", 26);

        // Cidades
        final City cabreuva = new City("Cabreúva", "São Paulo", "SP");
        final City campinas = new City("Campinas", "São Paulo", "SP");
        final City salvador = new City("Salvador", "Bahia", "BA");
        final City curitiba = new City("Curitiba", "Paraná", "PR");

        // Endereços
        final Address c1Address = new Address("R. Dos Alecrins", "13");
        final Address c2Address = new Address("Av. Paulista", "1578");
        final Address c3Address = new Address("R. das Flores", "45");
        final Address c4Address = new Address("R. dos Lírios", "22B");
        final Address c5Address = new Address("Av. Rio Branco", "102");
        final Address c6Address = new Address("R. Dom Pedro II", "350");

        // Atribuindo as cidades aos endereços
        c1Address.setCity(cabreuva);
        c2Address.setCity(campinas);
        c3Address.setCity(salvador);
        c4Address.setCity(curitiba);
        c5Address.setCity(campinas);
        c6Address.setCity(curitiba);

        // Atribuindo os endereços aos clientes
        c1.setAddress(c1Address);
        c2.setAddress(c2Address);
        c3.setAddress(c3Address);
        c4.setAddress(c4Address);
        c5.setAddress(c5Address);
        c6.setAddress(c6Address);

        // Registrando os clientes
        this.dao.store(c1);
        this.dao.store(c2);
        this.dao.store(c3);
        this.dao.store(c4);
        this.dao.store(c5);
        this.dao.store(c6);

        // Atualizando o cliente "c5"
        final Client newData = new Client("Leonardo", "Alves", "5324523", "827384981", 53);

        final boolean r1 = this.dao.update(5L, newData);
        final Client client = this.dao.show(5L);

        Assert.assertTrue(r1);
        Assert.assertEquals(newData, client);
    }

    @Test
    public void destroyTest() throws Exception {
        // Clientes
        final Client c1 = new Client("Caique", "Souza", "123.321.123.53", "987654321", 21);
        final Client c2 = new Client("Mariana", "Alves", "321.123.321.12", "123456789", 25);
        final Client c3 = new Client("João", "Silva", "456.789.456.23", "987654321", 30);
        final Client c4 = new Client("Fernanda", "Costa", "789.456.789.34", "456789123", 28);
        final Client c5 = new Client("Lucas", "Pereira", "159.753.159.46", "654321987", 22);
        final Client c6 = new Client("Aline", "Ferreira", "951.357.951.78", "321987654", 26);

        // Cidades
        final City cabreuva = new City("Cabreúva", "São Paulo", "SP");
        final City campinas = new City("Campinas", "São Paulo", "SP");
        final City salvador = new City("Salvador", "Bahia", "BA");
        final City curitiba = new City("Curitiba", "Paraná", "PR");

        // Endereços
        final Address c1Address = new Address("R. Dos Alecrins", "13");
        final Address c2Address = new Address("Av. Paulista", "1578");
        final Address c3Address = new Address("R. das Flores", "45");
        final Address c4Address = new Address("R. dos Lírios", "22B");
        final Address c5Address = new Address("Av. Rio Branco", "102");
        final Address c6Address = new Address("R. Dom Pedro II", "350");

        // Atribuindo as cidades aos endereços
        c1Address.setCity(cabreuva);
        c2Address.setCity(campinas);
        c3Address.setCity(salvador);
        c4Address.setCity(curitiba);
        c5Address.setCity(campinas);
        c6Address.setCity(curitiba);

        // Atribuindo os endereços aos clientes
        c1.setAddress(c1Address);
        c2.setAddress(c2Address);
        c3.setAddress(c3Address);
        c4.setAddress(c4Address);
        c5.setAddress(c5Address);
        c6.setAddress(c6Address);

        // Registrando os clientes
        this.dao.store(c1);
        this.dao.store(c2);
        this.dao.store(c3);
        this.dao.store(c4);
        this.dao.store(c5);
        this.dao.store(c6);

        // Excluindo um registro
        final boolean r1 = this.dao.destroy(3L);
        final boolean r2 = this.dao.destroy(3L);

        Assert.assertTrue(r1);
        Assert.assertFalse(r2);
        Assert.assertEquals(5, this.dao.index().size());
    }
}
