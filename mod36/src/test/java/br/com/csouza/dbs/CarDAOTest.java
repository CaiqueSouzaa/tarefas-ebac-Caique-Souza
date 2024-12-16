package br.com.csouza.dbs;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.csouza.dbs.dao.CarDAO;
import br.com.csouza.dbs.dao.UserDAO;
import br.com.csouza.dbs.domain.Car;
import br.com.csouza.dbs.domain.User;
import br.com.csouza.dbs.interfaces.ICarDAO;
import br.com.csouza.dbs.interfaces.IUserDAO;

public class CarDAOTest {
    private final ICarDAO carDAO;
    private final IUserDAO userDAO;

    public CarDAOTest() {
        this.carDAO = new CarDAO("MySQL_DB");
        this.userDAO = new UserDAO("MySQL_DB");
    }

    private User createTestUser() {
        User user = new User();
        user.setName("CarOwner");
        user.setSurname("User");
        user.setEmail("car.owner@example.com");
        user.setAge(35);
        return userDAO.save(user);
    }

    private Car createTestCar(User user) {
        Car car = new Car();
        car.setModel("Model S");
        car.setManufacturer("Tesla");
        car.setUser(user);
        return car;
    }


    @After
    public void deleteAllRegisters() {
    	final Collection<Car> cars = this.carDAO.findAll();
    	final Collection<User> users = this.userDAO.findAll();
    	
    	for (Car car : cars) {
    		this.carDAO.destroy(car);
    	}
    	
    	for (User user : users) {
    		this.userDAO.destroy(user);
    	}
    }

    @Test
    public void save() {
        User user = createTestUser();
        Car car = createTestCar(user);
        Car savedCar = carDAO.save(car);
        Assert.assertNotNull(savedCar.getId());
        Assert.assertTrue(savedCar.isActived());
        Assert.assertNotNull(savedCar.getCreatedAt());
    }

    @Test
    public void findById() {
        User user = createTestUser();
        Car car = createTestCar(user);
        Car savedCar = carDAO.save(car);
        Car foundCar = carDAO.findById(savedCar.getId());
        Assert.assertNotNull(foundCar);
        Assert.assertEquals(savedCar.getId(), foundCar.getId());
        Assert.assertEquals("Model S", foundCar.getModel());
    }

    @Test
    public void findAll() {
        User user = createTestUser();
        carDAO.save(createTestCar(user));
        carDAO.save(createTestCar(user));
        Collection<Car> cars = carDAO.findAll();
        Assert.assertTrue(cars.size() >= 2);
    }

    @Test
    public void update() {
        User user = createTestUser();
        Car car = createTestCar(user);
        Car savedCar = carDAO.save(car);
        savedCar.setModel("Updated Model");
        Car updatedCar = carDAO.update(savedCar);
        Assert.assertEquals("Updated Model", updatedCar.getModel());
    }

    @Test
    public void destroy() {
        User user = createTestUser();
        Car car = createTestCar(user);
        Car savedCar = carDAO.save(car);
        carDAO.destroy(savedCar);
        Car foundCar = carDAO.findById(savedCar.getId());
        Assert.assertNull(foundCar);
    }
}
