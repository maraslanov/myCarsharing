package app.web.repos;

import app.persistance.entity.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepo extends CrudRepository<Model, Long> {
    List<Model> findAll();
}
