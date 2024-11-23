package br.com.csouza;

import br.com.csouza.dao.StatusDAO;
import br.com.csouza.entities.Status;
import br.com.csouza.interfaces.dao.IStatusDAO;
import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;

public class StatusDAOTest {
    private IStatusDAO dao;

    public StatusDAOTest() {
        this.dao = new StatusDAO();
    }

    @Test
    public void show() throws Exception {
        final Status status = this.dao.show(1L);

        Assert.assertNotNull(status);
        Assert.assertEquals("EM ABERTO", status.getName());
    }

    @Test
    public void index() throws Exception {
        final Collection<Status> statuses = this.dao.index();

        Assert.assertEquals(3, statuses.size());
    }
}
