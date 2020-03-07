package app.web.service.Role;

import app.persistance.entity.Role;
import app.web.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findRoleByName(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public Role findRoleById(Long name) {
        return roleRepo.findById(name).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }
}

