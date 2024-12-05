package br.com.csouza.mod33;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.csouza.mod33.dao.AcessorioDAO;
import br.com.csouza.mod33.dao.MarcaDAO;
import br.com.csouza.mod33.domain.Acessorio;
import br.com.csouza.mod33.domain.Marca;
import br.com.csouza.mod33.interfaces.dao.IAcessorioDAO;
import br.com.csouza.mod33.interfaces.dao.IMarcaDAO;
import br.com.csouza.mod33.utils.Randomico;

public class MarcaDAOTest {
    private final IMarcaDAO marcaDAO;
    private final IAcessorioDAO acessorioDAO;

    public MarcaDAOTest() {
        this.marcaDAO = new MarcaDAO();
        this.acessorioDAO = new AcessorioDAO();
    }

    @AfterEach
    public void deleteRegisters() {
        final Collection<Marca> marcas = this.marcaDAO.getAll();
        final Collection<Acessorio> acessorios = this.acessorioDAO.getAll();

        acessorios.forEach(a -> this.acessorioDAO.delete(a.getId()));
        marcas.forEach(m -> this.marcaDAO.delete(m.getId()));
    }

    public static Marca createMarca() {
        final Marca m = new Marca();

        m.setName(Randomico.text(10));

        return m;
    }

    private Marca register() {
        Marca m = createMarca();

        m = this.marcaDAO.create(m);

        return m;
    }

    @Test
    public void create() {
        Marca m1 = createMarca();

        m1 = this.marcaDAO.create(m1);

        Assertions.assertNotNull(m1.getId());
    }

    @Test
    public void getById() {
        final List<Marca> marcas = new ArrayList<>();
        final int size = 10;

        for (int i = 0; i < size; i++) {
            marcas.add(this.register());
        }

        final Marca m1 = marcas.get(7);

        final Marca findedMarca = this.marcaDAO.getById(m1.getId());

        Assertions.assertEquals(m1.getId(), findedMarca.getId());
        Assertions.assertEquals(m1.getName(), findedMarca.getName());
    }

    @Test
    public void getAll() {
        final int size = 15;

        for (int i = 0; i < size; i++) {
            this.register();
        }

        final Collection<Marca> marcas = this.marcaDAO.getAll();

        Assertions.assertEquals(size, marcas.size());
    }

    @Test
    public void update() {
        final List<Marca> marcas = new ArrayList<>();
        final int size = 15;

        for (int i = 0; i < size; i++) {
            marcas.add(this.register());
        }

        Marca marca = marcas.get(4);

        marca.setName("Logitech");

        marca = this.marcaDAO.update(marca);

        final Marca findedMarca = this.marcaDAO.getById(marca.getId());

        Assertions.assertEquals(marca.getId(), findedMarca.getId());
        Assertions.assertEquals(marca.getName(), findedMarca.getName());
    }

    @Test
    public void delete() {
        final List<Marca> marcas = new ArrayList<>();
        final int size = 10;

        for (int i = 0; i < size; i++) {
            marcas.add(this.register());
        }

        final boolean result = this.marcaDAO.delete(marcas.get(9).getId());

        Assertions.assertTrue(result);
    }

    @Test
    public void createAcessorio() {
        Marca marca = new Marca();
        Acessorio a = new Acessorio();

        marca.setName("Logitech");

        a.setCode("ES-0042");
        a.setName("Mouse");
        a.setDescription("Mouse gamer Logitech");
        a.setPrice(323.95D);

        marca = this.marcaDAO.create(marca);

        a = this.marcaDAO.createAcessorio(marca, a);

        Assertions.assertNotNull(a.getId());
    }
}
