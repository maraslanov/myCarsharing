package app.web.controller;

import app.persistance.entity.User;
import app.web.service.BookListService.BookListServiceImpl;
import app.web.service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookListController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    BookListServiceImpl bookListService = new BookListServiceImpl();
    List<Object[]> list = new ArrayList<>();

    @GetMapping("/my/booking")
    public ModelAndView listBook() {

        User user = userService.getCurrentUser();
        list = bookListService.findAllByUserId(user.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("booking");

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/admin/booking")
    public ModelAndView listAdminBook(@ModelAttribute("dateFrom") String dateFrom,
                                      @ModelAttribute("dateTo") String dateTo) throws ParseException {

        if (dateFrom.isEmpty() || dateTo.isEmpty()) {
            Date dateMonthBefore = new Date();
            dateMonthBefore.setTime(dateMonthBefore.getTime() - 2592000000L); //установка даты с разницей в месяц от текущей
            dateFrom = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
                    .format(dateMonthBefore);
            dateTo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date());
        }
        list = bookListService.findAllByDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateFrom),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateTo));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.addObject("dateFrom", dateFrom);
        modelAndView.addObject("dateTo", dateTo);
        modelAndView.setViewName("admin-booking");

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping(value = "/admin/booking")
    public ModelAndView updateListAdminBook(@ModelAttribute("dateFrom") String dateFrom,
                                            @ModelAttribute("dateTo") String dateTo) throws ParseException {

        return listAdminBook(dateFrom, dateTo);
    }
}