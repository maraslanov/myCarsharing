package app.web.repos;

import app.persistance.entity.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepo extends CrudRepository<Brand, Long> {
    List<Brand> findAll();
}
