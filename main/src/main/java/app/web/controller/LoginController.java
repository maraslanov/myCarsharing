package app.web.controller;

import app.web.service.Role.RoleService;
import app.web.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login/", method = RequestMethod.GET)
    public ModelAndView login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ModelAndView("redirect:/cars");
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }
}
