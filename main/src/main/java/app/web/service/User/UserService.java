package app.web.service.User;


import app.persistance.entity.User;

import java.util.List;

public interface UserService {
    User findUserByUsernameAndPassword(String username, String givenPassword);

    User findUserByLoginOrEmail(String username);

    String encodePassword(String password);

    User getCurrentUser();

    List<User> findAll();

    User findById(Long id);

    User saveUser(User user);
}