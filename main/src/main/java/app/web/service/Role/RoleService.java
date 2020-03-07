package app.web.service.Role;

import app.persistance.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByName(String name);

    Role findRoleById(Long name);

    List<Role> findAll();
}
