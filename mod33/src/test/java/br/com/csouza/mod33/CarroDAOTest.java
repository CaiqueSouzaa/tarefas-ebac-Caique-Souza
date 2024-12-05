package br.com.csouza.mod33;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.csouza.mod33.dao.CarroDAO;
import br.com.csouza.mod33.domain.Carro;
import br.com.csouza.mod33.interfaces.dao.ICarroDAO;
import br.com.csouza.mod33.utils.Randomico;

public class CarroDAOTest {
    private final ICarroDAO carroDAO;

    public CarroDAOTest() {
        this.carroDAO = new CarroDAO();
    }
    
    @AfterEach
    public void deleteRegisters() {
        final Collection<Carro> carros = this.carroDAO.getAll();
        carros.forEach(m -> this.carroDAO.delete(m.getId()));
    }

    public static Carro createCarro() {
        final Carro c = new Carro();
        final int size = 8;
        final AcessorioDAOTest acessorioDAOTest = new AcessorioDAOTest();

        c.setModel(Randomico.text(10));
        c.setDescription(Randomico.text(20));

        for (int i = 0; i < size; i++) {
            c.addAcessorio(acessorioDAOTest.register());
        }

        return c;
    }

    private Carro register() {
        Carro c = createCarro();

        c = this.carroDAO.create(c);

        return c;
    }

    @Test
    public void create() {
        Carro c1 = createCarro();

        c1 = this.carroDAO.create(c1);

        Assertions.assertNotNull(c1.getId());
        Assertions.assertEquals(8, c1.getAcessorios().size());
    }

    @Test
    public void getById() {
        final List<Carro> carros = new ArrayList<>();
        final int size = 10;

        for (int i = 0; i < size; i++) {
            carros.add(this.register());
        }

        final Carro c1 = carros.get(7);

        final Carro findedCarro = this.carroDAO.getById(c1.getId());

        Assertions.assertEquals(c1.getId(), findedCarro.getId());
        Assertions.assertEquals(c1.getModel(), findedCarro.getModel());
    }

    @Test
    public void getAll() {
        final int size = 15;

        for (int i = 0; i < size; i++) {
            this.register();
        }

        final Collection<Carro> carros = this.carroDAO.getAll();

        Assertions.assertEquals(size, carros.size());
    }

    @Test
    public void update() {
        final List<Carro> carros = new ArrayList<>();
        final int size = 15;

        for (int i = 0; i < size; i++) {
            carros.add(this.register());
        }

        Carro carro = carros.get(4);

        carro.setModel("HB20");
        carro.setDescription("Um bom carro.");

        carro = this.carroDAO.update(carro);

        final Carro findedCarro = this.carroDAO.getById(carro.getId());

        Assertions.assertEquals(carro.getId(), findedCarro.getId());
        Assertions.assertEquals(carro.getModel(), findedCarro.getModel());
        Assertions.assertEquals(carro.getDescription(), findedCarro.getDescription());
    }

    @Test
    public void delete() {
        final List<Carro> carros = new ArrayList<>();
        final int size = 10;

        for (int i = 0; i < size; i++) {
            carros.add(this.register());
        }

        final boolean result = this.carroDAO.delete(carros.get(9).getId());

        Assertions.assertTrue(result);
    }
}
