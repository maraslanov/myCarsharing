package app.web.repos;

import app.persistance.entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepo extends CrudRepository<Type, Long> {
    List<Type> findAll();
}
