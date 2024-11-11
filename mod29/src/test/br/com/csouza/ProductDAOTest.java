package br.com.csouza;

import org.junit.Test;

import br.com.csouza.dao.ProductDAO;
import br.com.csouza.entities.Product;
import br.com.csouza.exceptions.WithoutTableName;
import br.com.csouza.interfaces.IProductDAO;

import java.sql.SQLException;
import java.util.Collection;

import org.junit.Assert;

public class ProductDAOTest {
	private IProductDAO dao = new ProductDAO();
	
	@Test
	public void storeTest() throws SQLException, WithoutTableName {
		// Criação do produto.
		final Product p1 = new Product("ES-0001", "Mouse", "Mouse básico de escritório");
		
		// Registrando o produto.
		final int r1 = this.dao.store(p1);
		Assert.assertEquals(1, r1);
		
		// Buscando o registro pelo código de produto
		Product findedProduct = this.dao.findByCode(p1.getCode());
		Assert.assertEquals(p1, findedProduct);
		
		// Excluindo o registro
		final int r2 = this.dao.destroy(findedProduct.getId());
		Assert.assertEquals(1, r2);
	}
	
	@Test
	public void showTest() throws SQLException, WithoutTableName {
		// Criação de produtos.
		final Product p1 = new Product("ES-0001", "Mouse", "Mouse básico de escritório");
		final Product p2 = new Product("ES-0002", "Teclado", "Teclado básico de escritório");
		final Product p3 = new Product("ES-0003", "Monitor", "Monitor de 24 polegadas Full HD");
		final Product p4 = new Product("ES-0004", "Webcam", "Webcam HD para videoconferências");
		final Product p5 = new Product("ES-0005", "Headset", "Headset com microfone e cancelamento de ruído");
		final Product p6 = new Product("ES-0006", "Impressora", "Impressora multifuncional colorida");
		final Product p7 = new Product("ES-0007", "Cadeira", "Cadeira ergonômica para escritório");
		
		// Registro de produtos
		final int r1 = this.dao.store(p1);
		final int r2 = this.dao.store(p2);
		final int r3 = this.dao.store(p3);
		final int r4 = this.dao.store(p4);
		final int r5 = this.dao.store(p5);
		final int r6 = this.dao.store(p6);
		final int r7 = this.dao.store(p7);
		
		// Busca de um registro pelo código de produto.
		final Product findedProductByCode = this.dao.findByCode(p5.getCode());
		
		// Busca de um registro pelo ID.
		final Product findedProductByID = this.dao.show(findedProductByCode.getId());
		Assert.assertEquals(p5, findedProductByID);
		
		// Excluindo os registros.
		final Collection<Product> products = this.dao.index();
		int size = 0;
		for (Product p : products) {
			this.dao.destroy(p.getId());
			size++;
		}
		Assert.assertEquals(products.size(), size);
	}
	
	@Test
	public void indexTest() throws SQLException, WithoutTableName {
		// Criação de produtos.
		final Product p1 = new Product("ES-0001", "Mouse", "Mouse básico de escritório");
		final Product p2 = new Product("ES-0002", "Teclado", "Teclado básico de escritório");
		final Product p3 = new Product("ES-0003", "Monitor", "Monitor de 24 polegadas Full HD");
		final Product p4 = new Product("ES-0004", "Webcam", "Webcam HD para videoconferências");
		final Product p5 = new Product("ES-0005", "Headset", "Headset com microfone e cancelamento de ruído");
		final Product p6 = new Product("ES-0006", "Impressora", "Impressora multifuncional colorida");
		final Product p7 = new Product("ES-0007", "Cadeira", "Cadeira ergonômica para escritório");
		
		// Registro de produtos
		final int r1 = this.dao.store(p1);
		final int r2 = this.dao.store(p2);
		final int r3 = this.dao.store(p3);
		final int r4 = this.dao.store(p4);
		final int r5 = this.dao.store(p5);
		final int r6 = this.dao.store(p6);
		final int r7 = this.dao.store(p7);
		
		// Buscando por todos os registros.
		final Collection<Product> products = this.dao.index();
		Assert.assertEquals(7, products.size());

		// Excluindo os registros.
		int size = 0;
		for (Product p : products) {
			this.dao.destroy(p.getId());
			size++;
		}
		Assert.assertEquals(products.size(), size);
	}
	
	@Test
	public void updateTest() throws SQLException, WithoutTableName {
		// Criação de produtos.
		final Product p1 = new Product("ES-0001", "Mouse", "Mouse básico de escritório");
		final Product p2 = new Product("ES-0002", "Teclado", "Teclado básico de escritório");
		final Product p3 = new Product("ES-0003", "Monitor", "Monitor de 24 polegadas Full HD");
		final Product p4 = new Product("ES-0004", "Webcam", "Webcam HD para videoconferências");
		final Product p5 = new Product("ES-0005", "Headset", "Headset com microfone e cancelamento de ruído");
		final Product p6 = new Product("ES-0006", "Impressora", "Impressora multifuncional colorida");
		final Product p7 = new Product("ES-0007", "Cadeira", "Cadeira ergonômica para escritório");
		
		// Registro de produtos
		final int r1 = this.dao.store(p1);
		final int r2 = this.dao.store(p2);
		final int r3 = this.dao.store(p3);
		final int r4 = this.dao.store(p4);
		final int r5 = this.dao.store(p5);
		final int r6 = this.dao.store(p6);
		final int r7 = this.dao.store(p7);
		
		// Buscando um registro pelo código de produto.
		final Product findedProductByCode = this.dao.findByCode(p3.getCode());
		
		// Buscando um registro pelo ID.
		final Product findedProductByID = this.dao.show(findedProductByCode.getId());
		
		// Atualizando o registro
		findedProductByID.setName("Gabine");
		final int r8 = this.dao.update(findedProductByID);
		Assert.assertEquals(1, r8);
		
		// Buscando pelo registro atualizado.
		final Product actualizedProduct = this.dao.show(findedProductByCode.getId());
		
		Assert.assertEquals(p3.getCode(), actualizedProduct.getCode());
		Assert.assertNotEquals(p3.getName(), actualizedProduct.getName());
		Assert.assertEquals(p3.getDescription(), actualizedProduct.getDescription());
		
		// Buscando por todos os registros.
		final Collection<Product> products = this.dao.index();
		Assert.assertEquals(7, products.size());

		// Excluindo os registros.
		int size = 0;
		for (Product p : products) {
			this.dao.destroy(p.getId());
			size++;
		}
		Assert.assertEquals(products.size(), size);
	}
	
	@Test
	public void destroyTest() throws SQLException, WithoutTableName {
		// Criação de produtos.
		final Product p1 = new Product("ES-0001", "Mouse", "Mouse básico de escritório");
		final Product p2 = new Product("ES-0002", "Teclado", "Teclado básico de escritório");
		final Product p3 = new Product("ES-0003", "Monitor", "Monitor de 24 polegadas Full HD");
		final Product p4 = new Product("ES-0004", "Webcam", "Webcam HD para videoconferências");
		final Product p5 = new Product("ES-0005", "Headset", "Headset com microfone e cancelamento de ruído");
		final Product p6 = new Product("ES-0006", "Impressora", "Impressora multifuncional colorida");
		final Product p7 = new Product("ES-0007", "Cadeira", "Cadeira ergonômica para escritório");
		
		// Registro de produtos
		final int r1 = this.dao.store(p1);
		final int r2 = this.dao.store(p2);
		final int r3 = this.dao.store(p3);
		final int r4 = this.dao.store(p4);
		final int r5 = this.dao.store(p5);
		final int r6 = this.dao.store(p6);
		final int r7 = this.dao.store(p7);

		// Excluindo os registros.
		final Collection<Product> products = this.dao.index();
		int size = 0;
		for (Product p : products) {
			this.dao.destroy(p.getId());
			size++;
		}
		Assert.assertEquals(products.size(), size);
	}
}
