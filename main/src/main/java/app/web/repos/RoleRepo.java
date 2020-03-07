package app.web.repos;

import app.persistance.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepo extends CrudRepository<Role, Long> {
    Role findByName(String name);

    List<Role> findAll();
}
