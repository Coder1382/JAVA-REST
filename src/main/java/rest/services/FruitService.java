package rest.services;

import rest.dao.FruitDao;
import rest.dto.FruitDto;
import rest.model.Fruit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FruitService {
    private final FruitDao fruitDao = new FruitDao();

    public List<FruitDto> find(long id) throws IOException {
        return fruitDao.find(id);
    }

    public long save(FruitDto fruitDto) throws IOException {
        Fruit fruit=new Fruit(fruitDto.getName(),fruitDto.getPrice());
        return fruitDao.save(fruit);
    }

    public long update(FruitDto fruitDto) throws IOException, SQLException {
        Fruit fruit=new Fruit(fruitDto.getId(),fruitDto.getPrice());
        return fruitDao.update(fruit);
    }

    public long delete(FruitDto fruitDto) throws IOException {
        Fruit fruit=new Fruit(fruitDto.getId());
        return fruitDao.delete(fruit);
    }
}
