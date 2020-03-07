package app.web.controller;

import app.persistance.entity.Role;
import app.persistance.entity.User;
import app.persistance.enums.UserAccess;
import app.web.service.Role.RoleService;
import app.web.service.User.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/my")
    public String getMy(Map<String, Object> model) {
        User user = userService.getCurrentUser();
        if (user.getRole() != null && "admin".equalsIgnoreCase(user.getRole().getName())) {
            return "/admin";
        }
        return "my";
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/admin")
    public String getAdmin(Map<String, Object> model) {
        return "admin";
    }

    @GetMapping(value = "/admin/users")
    @PreAuthorize("hasAuthority('Admin')")
    public String getUserList(Map<String, Object> model) {
        List<User> users = userService.findAll();
        for (User user : users) {
            if (StringUtils.isBlank(user.getAccess())) {
                user.setAccess(UserAccess.Есть.name());
            }
        }
        model.put("users", users);
        return "admin-users";
    }

    @GetMapping("/admin/user-edit/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public String editUserByAdmin(@PathVariable Long id, Map<String, Object> model) {
        User user = userService.findById(id);
        user.setEmail("");
        user.setPassword("");
        List<Role> roles = roleService.findAll();
        model.put("user", user);
        model.put("roles", roles);
        model.put("action", "/admin/user-edit/" + id);
        return "admin-useredit";
    }

    @PostMapping("/admin/user-edit/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public String saveUserByAdmin(@Valid @ModelAttribute("user") User user,
                                  Map<String, Object> model,
                                  BindingResult bindingResult,
                                  @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "admin-useredit";
        }
        if (userService.findUserByLoginOrEmail(user.getEmail()) != null) {
            model.put("emailError", "такой логин уже используется");
            List<Role> roles = roleService.findAll();
            model.put("roles", roles);
            model.put("action", "/admin/user-edit/" + id);
            return "admin-useredit";
        }
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/my/edit")
    public String editUser(Map<String, Object> model) {
        User user = userService.getCurrentUser();
        user.setEmail("");
        user.setPassword("");
        model.put("user", user);
        model.put("action", "/my/edit/" + user.getId());
        return "useredit";
    }

    @PostMapping("/my/edit/{id}")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           Map<String, Object> model,
                           BindingResult bindingResult,
                           @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "useredit";
        }
        if (userService.findUserByLoginOrEmail(user.getEmail()) != null) {
            model.put("emailError", "такой логин уже используется");
            List<Role> roles = roleService.findAll();
            model.put("action", "/my/edit/" + id);
            return "useredit";
        }
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/my";
    }
}