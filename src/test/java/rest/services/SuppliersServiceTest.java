package rest.services;

import org.junit.jupiter.api.Test;
import rest.dto.SellersDto;
import rest.dto.SuppliersDto;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuppliersServiceTest {
    SuppliersService suppliersService = new SuppliersService();

    @Test
    public void findTest() throws IOException {
        assertEquals((suppliersService.find(1).get(0)).getName(), "big");
        assertEquals((suppliersService.find(1).get(0)).getId(), 1);
    }

    @Test
    public void saveTest() throws IOException {
        assertEquals(Long.class, ((Long) suppliersService.save(new SuppliersDto("old"))).getClass());
    }

    @Test
    public void updateTest() throws SQLException, IOException {
        assertEquals(suppliersService.update(new SuppliersDto("new", "fedor")), -1);
    }

    @Test
    public void deleteTest() throws IOException {
        suppliersService.delete(new SuppliersDto(2));
    }
}
