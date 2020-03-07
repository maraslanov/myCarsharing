package app.web.security;

import app.persistance.entity.User;
import app.web.service.Role.RoleService;
import app.web.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
public class AuthenticationProvider implements UserDetailsService {

    @Autowired
    private UserService userDAO;
    @Autowired
    private RoleService roleService;

    public AuthenticationProvider(UserService userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDAO.findUserByLoginOrEmail(username);

            if (user == null) {
                throw new UsernameNotFoundException("Can't find user with username: \"" + username + "\"");
            }

            List<GrantedAuthority> authorities = new ArrayList();
            if (user.getRole() != null)
                authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities
            );
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}