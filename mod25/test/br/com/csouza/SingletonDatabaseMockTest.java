package br.com.csouza;

import br.com.csouza.dao.mocks.SingletonDatabaseMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class SingletonDatabaseMockTest {
    @Before
    public void init() {
        SingletonDatabaseMock.init();
    }

    @Test
    public void crateTableTest() {
        final String tn1 = "produtos";
        final String tn2 = "clientes";
        final String tn3 = "notas_fiscais";
        final boolean r1 = SingletonDatabaseMock.newTable(tn1);
        final boolean r2 = SingletonDatabaseMock.newTable(tn2);
        final boolean r3 = SingletonDatabaseMock.newTable(tn3);

        System.out.println(SingletonDatabaseMock.getTables());

        Assert.assertTrue(r1);
        Assert.assertTrue(r2);
        Assert.assertTrue(r3);
    }

    @Test
    public void getTablesTest() {
        Assert.assertNotNull(SingletonDatabaseMock.getTables());
    }
}
