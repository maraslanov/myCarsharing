package app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        return modelAndView;
    }

    @GetMapping("/")
    public String viewSlash() {

        return "redirect:/home";
    }

    @GetMapping("")
    public String viewEmpty() {

        return "redirect:/home";
    }
}
