package br.com.csouza;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.csouza.interfaces.IClientDAO;
import br.com.csouza.dao.ClientDAO;
import br.com.csouza.entities.Client;
import br.com.csouza.exceptions.WithoutTableName;

public class ClientDAOTest {
	private IClientDAO dao = new ClientDAO();
	
	@Test
	public void storeTest() throws SQLException, WithoutTableName {
		// Registrando o cliente.
		final Client c1 = new Client("Caique", "Souza", "12332123189", "9918473185", 21);
		final int rs = this.dao.store(c1);
		Assert.assertEquals(1, rs);
		
		// Buscando pelo cliente registrado.
		final Client c1DB = this.dao.findByCPF(c1.getCpf());
		Assert.assertEquals(c1.getCpf(), c1DB.getCpf());
		
		// Excluindo o registro do bando de dados
		final int rs2 = this.dao.destroy(c1DB.getId());
		Assert.assertEquals(1, rs2);
	}
	
	@Test
	public void showTest() throws SQLException, WithoutTableName {
		// Registrando o cliente.
		final Client c1 = new Client("Caique", "Souza", "12332123189", "9918473185", 21);
		final int rs = this.dao.store(c1);
		Assert.assertEquals(1, rs);
		
		// Buscando o cliente registrado pelo CPF.
		final Client c1DB = this.dao.findByCPF(c1.getCpf());
		Assert.assertEquals(c1.getCpf(), c1DB.getCpf());
		
		// Buscando o cliente registrado pelo ID.
		final Client c2DB = this.dao.show(c1DB.getId());
		Assert.assertEquals(c1DB, c2DB);
		
		// Excluindo o registro do bando de dados
		final int rs2 = this.dao.destroy(c1DB.getId());
		Assert.assertEquals(1, rs2);
	}
	
	@Test
	public void indexTest() throws SQLException, WithoutTableName {
		// Registrando o cliente.
		final Client c1 = new Client("Caique", "Souza", "12332123189", "9918473185", 21);
		final Client c2 = new Client("Ana", "Silva", "45665478901", "9876543210", 27);
		final Client c3 = new Client("Lucas", "Pereira", "78912345609", "9987123456", 35);
		final Client c4 = new Client("Mariana", "Ferreira", "12398745632", "9821345678", 29);
		final Client c5 = new Client("Rafael", "Santos", "32165498701", "9912345678", 41);
		final Client c6 = new Client("Bruna", "Oliveira", "45612378965", "9871234567", 23);
		final Client c7 = new Client("Gabriel", "Costa", "78945612378", "9923456789", 30);

		final int rs1 = this.dao.store(c1);
		final int rs2 = this.dao.store(c2);
		final int rs3 = this.dao.store(c3);
		final int rs4 = this.dao.store(c4);
		final int rs5 = this.dao.store(c5);
		final int rs6 = this.dao.store(c6);
		final int rs7 = this.dao.store(c7);
		Assert.assertEquals(1, rs1);
		Assert.assertEquals(1, rs2);
		Assert.assertEquals(1, rs3);
		Assert.assertEquals(1, rs4);
		Assert.assertEquals(1, rs5);
		Assert.assertEquals(1, rs6);
		Assert.assertEquals(1, rs7);
		
		final Collection<Client> clients = this.dao.index();
		
		int collectionSize = 0;
		for (Client c : clients) {
			this.dao.destroy(c.getId());
			collectionSize++;
		}
		Assert.assertEquals(clients.size(), collectionSize);
	}
	
	@Test
	public void updateTest() throws SQLException, WithoutTableName {}
	
	@Test
	public void destroyTest() throws SQLException, WithoutTableName {
		// Registrando o cliente.
		final Client c1 = new Client("Caique", "Souza", "12332123189", "9918473185", 21);
		final int rs = this.dao.store(c1);
		Assert.assertEquals(1, rs);
		
		// Buscando pelo cliente registrado.
		final Client c1DB = this.dao.findByCPF(c1.getCpf());
		Assert.assertEquals(c1.getCpf(), c1DB.getCpf());
		
		// Excluindo o registro do bando de dados
		final int rs2 = this.dao.destroy(c1DB.getId());
		Assert.assertEquals(1, rs2);}
	
}
