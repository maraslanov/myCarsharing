package app.web.service.RegistrationService;

import app.persistance.entity.Role;
import app.persistance.entity.User;
import app.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = userRepo.findUserByEmail(user.getEmail());

        if (userFromDB != null) {
            return false;
        }

//        user.setRoleId(2L);
        Role role = new Role();
        role.setId(2L);
        role.setName("user");
        user.setRole(role);
        user.setAccess("true");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }
}
