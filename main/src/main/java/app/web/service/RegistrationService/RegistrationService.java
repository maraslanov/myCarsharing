package app.web.service.RegistrationService;

import app.persistance.entity.User;

public interface RegistrationService {

    User save(User user);

    boolean saveUser(User user);
}
