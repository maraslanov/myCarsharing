package app.web.repos;

import app.persistance.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepo extends CrudRepository<City, Long> {
    List<City> findAll();
}
