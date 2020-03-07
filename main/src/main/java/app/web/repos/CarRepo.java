package app.web.repos;

import app.persistance.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepo extends CrudRepository<Car, Long> {

    List<Car> findAll();
    Iterable<Car> findByBrandId(long brandId);

}
