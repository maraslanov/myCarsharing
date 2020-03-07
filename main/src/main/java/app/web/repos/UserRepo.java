package app.web.repos;

import app.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String login, String password);

    User findUserByEmail(String login);
}
