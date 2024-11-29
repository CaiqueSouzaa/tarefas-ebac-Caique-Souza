package br.com.csouza.mod32;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import br.com.csouza.mod32.dao.ProductDAO;
import br.com.csouza.mod32.domain.Product;
import br.com.csouza.mod32.interfaces.dao.IProductDAO;

import java.util.Collection;
import java.util.List;

public class ProductDAOTest {
	private final IProductDAO productDAO;
	
	public ProductDAOTest() {
		this.productDAO = new ProductDAO();
	}

	@AfterEach
	public void deleteRegisters() {
		final Collection<Product> products = this.productDAO.buscarTodos();

		for (Product p : products) {
			this.productDAO.deletar(p.getId());
		}
	}

	@Test
	public void cadastrar() {
		Product p1 = new Product("ES-4573", "Mouse G305", "Mouse Logitech G305 Gamer", 230D);
		
		p1 = this.productDAO.cadastrar(p1);
		
		Assertions.assertNotNull(p1);
		Assertions.assertNotNull(p1.getId());
	}

	@Test
	public void buscar() {
		Product p1 = new Product("ES-4572", "Mouse G305", "Mouse Logitech G305 Gamer", 230D);

		p1 = this.productDAO.cadastrar(p1);

		final Product findedProduct = this.productDAO.buscar(p1.getId());

		Assertions.assertNotNull(findedProduct);
	}

	@Test
	public void buscarTodos() {
		Product p1 = new Product("ES-4575", "Monitor", null, 4234D);
		Product p2 = new Product("ES-1242", "Teclado", "Descrição do teclado", 123D);
		Product p3 = new Product("ES-6343", "Gabinete", "Descrição do gabinete", 495D);

		p1 = this.productDAO.cadastrar(p1);
		p2 = this.productDAO.cadastrar(p2);
		p3 = this.productDAO.cadastrar(p3);

		Collection<Product> products = this.productDAO.buscarTodos();

		Assertions.assertEquals(3, products.size());
	}

	@Test
	public void atualizar() {
		Product p1 = new Product("ES-5234", "Monitor", null, 4234D);

		p1 = this.productDAO.cadastrar(p1);

		Product newData = new Product(p1.getCode(), p1.getName(), p1.getDescription(), p1.getPrice());
		newData.setId(p1.getId());
		newData.setCreatedAt(p1.getCreatedAt());

		newData.setName("Memória RAM 16gb DDR4");

		final Product rs = this.productDAO.atualizar(newData);

		Assertions.assertEquals(p1.getId(), rs.getId());
		Assertions.assertNotEquals(p1.getName(), rs.getName());
		Assertions.assertEquals(newData.getName(), rs.getName());
	}

	@Test
	public void deletar() {
		Product p1 = new Product("ES-5234", "Monitor", null, 4234D);

		p1 = this.productDAO.cadastrar(p1);

		final int rs = this.productDAO.deletar(p1.getId());

		Assertions.assertEquals(1, rs);
	}
}
