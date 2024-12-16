package br.com.csouza.dbs;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.csouza.dbs.dao.UserDAO;
import br.com.csouza.dbs.domain.User;
import br.com.csouza.dbs.interfaces.IUserDAO;

public class UserDAOTest {
    private final IUserDAO userDAO;

    public UserDAOTest() {
        this.userDAO = new UserDAO("PostgreSQL_DB");
    }

    private User createTestUser() {
        User user = new User();
        user.setName("Test");
        user.setSurname("User");
        user.setEmail("test.user@example.com");
        user.setAge(30);
        return user;
    }

    @After
    public void deleteAllRegisters() {
    	final Collection<User> users = this.userDAO.findAll();
    	
    	for (User user : users) {
    		this.userDAO.destroy(user);
    	}
    }

    @Test
    public void save() {
        User user = createTestUser();
        User savedUser = userDAO.save(user);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertTrue(savedUser.isActived());
        Assert.assertNotNull(savedUser.getCreatedAt());
    }

    @Test
    public void findById() {
        User user = createTestUser();
        User savedUser = userDAO.save(user);
        User foundUser = userDAO.findById(savedUser.getId());
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(savedUser.getId(), foundUser.getId());
        Assert.assertEquals("Test", foundUser.getName());
    }

    @Test
    public void findAll() {
        User user = new User();
        user.setName("Test");
        user.setSurname("User");
        user.setEmail("test.user@example.com2");
        user.setAge(30);
        
        userDAO.save(user);
        userDAO.save(createTestUser());
        Collection<User> users = userDAO.findAll();
        Assert.assertTrue(users.size() >= 2);
    }

    @Test
    public void update() {
        User user = createTestUser();
        User savedUser = userDAO.save(user);
        savedUser.setName("Updated");
        User updatedUser = userDAO.update(savedUser);
        Assert.assertEquals("Updated", updatedUser.getName());
    }

    @Test
    public void destroy() {
        User user = createTestUser();
        User savedUser = userDAO.save(user);
        userDAO.destroy(savedUser);
        User foundUser = userDAO.findById(savedUser.getId());
        Assert.assertNull(foundUser);
    }
}
