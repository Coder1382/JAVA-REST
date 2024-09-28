package rest.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.testcontainers.containers.PostgreSQLContainer;
import rest.dto.SellersDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellersDaoTest {
    private SellersDao sellersDao = new SellersDao();

    @BeforeEach
    public void before() {
        PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");
        postgres.start();
    }

    @Test
    public void findTest() {
        assertEquals((sellersDao.find(1).get(0)).getName(), "ignat");
        assertEquals((sellersDao.find(1).get(0)).getId(), 1);
    }

    @Test
    public void saveTest() {
        assertEquals(sellersDao.save(new SellersDto("akim", "big")), 4);
    }

    @Test
    public void updateTest() throws SQLException {
        assertEquals(sellersDao.update(new SellersDto(4, "mango")), 4);
    }

    @Test
    public void deleteTest() {
        assertEquals(sellersDao.delete(new SellersDto(4)), 4);
    }

    @AfterEach
    public void after() {
        PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");
        postgres.stop();
    }
}
