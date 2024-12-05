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

public class AcessorioDAOTest {
    private final IAcessorioDAO acessorioDAO;
    private final IMarcaDAO marcaDAO;

    public AcessorioDAOTest() {
        this.acessorioDAO = new AcessorioDAO();
        this.marcaDAO = new MarcaDAO();
    }
    
    @AfterEach
    public void deleteRegisters() {
        final Collection<Acessorio> acessorios= this.acessorioDAO.getAll();
        final Collection<Marca> marcas = this.marcaDAO.getAll();

        acessorios.forEach(a -> this.acessorioDAO.delete(a.getId()));
        marcas.forEach(m -> this.marcaDAO.delete(m.getId()));
    }

    private Marca registerMarca() {
        Marca m = MarcaDAOTest.createMarca();

        m = this.marcaDAO.create(m);

        return m;
    }

    private Acessorio createAcessorio() {
        final Acessorio a = new Acessorio();

        a.setCode(Randomico.text(6));
        a.setName(Randomico.text(10));
        a.setDescription(Randomico.text(30));
        a.setPrice(Randomico.rDouble());
        a.setMarca(this.registerMarca());

        return a;
    }

    public Acessorio register() {
        Acessorio a = this.createAcessorio();

        a = this.acessorioDAO.create(a);

        return a;
    }

    @Test
    public void create() {
        Acessorio a1 = createAcessorio();

        a1 = this.acessorioDAO.create(a1);

        Assertions.assertNotNull(a1.getId());
    }

    @Test
    public void getById() {
        final List<Acessorio> acessorios = new ArrayList<>();
        final int size = 10;

        for (int i = 0; i < size; i++) {
            acessorios.add(this.register());
        }

        final Acessorio a1 = acessorios.get(7);

        final Acessorio findedAcessorio = this.acessorioDAO.getById(a1.getId());

        Assertions.assertEquals(a1.getId(), findedAcessorio.getId());
        Assertions.assertEquals(a1.getName(), findedAcessorio.getName());
    }

    @Test
    public void getAll() {
        final int size = 15;

        for (int i = 0; i < size; i++) {
            this.register();
        }

        final Collection<Acessorio> acessorios = this.acessorioDAO.getAll();

        Assertions.assertEquals(size, acessorios.size());
    }

    @Test
    public void update() {
        final List<Acessorio> acessorios = new ArrayList<>();
        final int size = 15;

        for (int i = 0; i < size; i++) {
            acessorios.add(this.register());
        }

        Acessorio acessorio = acessorios.get(4);

        acessorio.setCode("AC-0001");
        acessorio.setName("Carregador");
        acessorio.setDescription("Carregador compativel com carros.");
        acessorio.setPrice(30.0D);

        acessorio = this.acessorioDAO.update(acessorio);

        final Acessorio findedAcessorio = this.acessorioDAO.getById(acessorio.getId());

        Assertions.assertEquals(acessorio.getId(), findedAcessorio.getId());
        Assertions.assertEquals(acessorio.getCode(), findedAcessorio.getCode());
        Assertions.assertEquals(acessorio.getName(), findedAcessorio.getName());
        Assertions.assertEquals(acessorio.getDescription(), findedAcessorio.getDescription());
        Assertions.assertEquals(acessorio.getPrice(), findedAcessorio.getPrice());
    }

    @Test
    public void delete() {
        final List<Acessorio> acessorios = new ArrayList<>();
        final int size = 10;

        for (int i = 0; i < size; i++) {
            acessorios.add(this.register());
        }

        final boolean result = this.acessorioDAO.delete(acessorios.get(9).getId());

        Assertions.assertTrue(result);
    }

    @Test
    public void getByCode() {
        Acessorio a1 = new Acessorio();
        final int size = 12;

        a1.setCode("AC-0004");
        a1.setName("Monitor");
        a1.setDescription("Monitor para ajudar no uso do Maps");
        a1.setMarca(this.registerMarca());
        a1.setPrice(300.54D);

        a1 = this.acessorioDAO.create(a1);

        for (int i = 0; i < size; i++) {
            this.register();
        }

        final Acessorio acessorio = this.acessorioDAO.getByCode(a1.getCode());

        Assertions.assertNotNull(acessorio);
        Assertions.assertEquals(a1.getId(), acessorio.getId());
        Assertions.assertEquals(a1.getCode(), acessorio.getCode());
    }
}
