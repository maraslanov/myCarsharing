package app.web.service.User;

import app.persistance.entity.User;
import app.persistance.enums.UserAccess;
import app.web.repos.UserRepo;
import app.web.service.Role.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User findUserByUsernameAndPassword(String username, String givenPassword) {
        return userRepository.findUserByEmailAndPassword(username, givenPassword);
    }

    @Override
    public User findUserByLoginOrEmail(String username) {
        return userRepository.findUserByEmail(username);
    }

    @Override
    public String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = "";
        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }
        User user = findUserByLoginOrEmail(username);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public User saveUser(User user) {
        User newUser = userRepository.findById(user.getId()).orElse(new User());
        if (user.getRole() != null) {
            newUser.setRole(user.getRole());
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            newUser.setEmail(user.getEmail());
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            newUser.setPassword(encodePassword(user.getPassword()));
        }
        if (StringUtils.isNotBlank(user.getFullname())) {
            newUser.setFullname(user.getFullname());
        }
        if (StringUtils.isNotBlank(user.getAccess())) {
            if (UserAccess.Заблокирован.name().equalsIgnoreCase(user.getAccess())) {
                newUser.setAccess(UserAccess.Заблокирован.getDisplayValue());
                newUser.setBlockDate(new Date());
                newUser.setBlockInfo(user.getBlockInfo());
            }
        }
        return userRepository.save(newUser);
    }
}