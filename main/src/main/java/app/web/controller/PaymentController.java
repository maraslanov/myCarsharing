package app.web.controller;

import app.persistance.entity.Booking;
import app.persistance.entity.PayCard;
import app.persistance.entity.User;
import app.web.pojo.Status;
import app.web.service.BookListService.BookListServiceImpl;
import app.web.service.BookingService.BookingServiceImpl;
import app.web.service.User.UserServiceImpl;
import dto.PayerDTO;
import dto.PaymentResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    BookListServiceImpl bookListService;
    @Autowired
    BookingServiceImpl bookingService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/pay-form/{id}")
    public ModelAndView payForm(@PathVariable long id) {

        Booking booking = bookListService.findBookingById(id);
        double price = booking.getPrice();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bookingId", id);
        modelAndView.addObject("price", price);
        modelAndView.addObject("card", new PayCard());
        modelAndView.setViewName("pay-form");

        return modelAndView;
    }

    @GetMapping("/pay-form/all")
    public ModelAndView payFormAll() {

        User user = userService.getCurrentUser();
        double price = bookListService.getCreditByUserId(user.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("price", price);
        modelAndView.addObject("card", new PayCard());
        modelAndView.setViewName("pay-form");

        return modelAndView;
    }

    @PostMapping("/doPay")
    public ModelAndView doPay(
            @ModelAttribute("bookingId") String bookingId,
            @ModelAttribute("price") Double price
    ) throws URISyntaxException {
        final String port = "8081";
        ModelAndView modelAndView = new ModelAndView();

        final String urlString = "http://localhost:" + port + "/paymentservice/";
        URI uri = new URI(urlString);
        RestTemplate restTemplate = new RestTemplate();
        PayerDTO payerDTO = new PayerDTO(userService.getCurrentUser().getId(), price);
        PaymentResultDTO result;
        try {
            result = restTemplate.postForObject(uri, payerDTO, PaymentResultDTO.class);
        } catch (ResourceAccessException e) {
            modelAndView.setViewName("500");
            return modelAndView;
        }
        User user = userService.getCurrentUser();
        if (result.getPaymentResult()) {
            List<Booking> bookings = new ArrayList<>();
            if (!bookingId.isEmpty()) {
                bookings.add(bookListService.findBookingById(Integer.parseInt(bookingId)));
            } else {
                bookings = bookListService.findBookingByUserId(user.getId());
            }
            for (Booking booking : bookings) {
                booking.setStatus(Status.PAID);
            }
            bookingService.saveAll(bookings);
            modelAndView.addObject("status", "Ваша бронь оплачена");
        } else {
            modelAndView.addObject("status", "Оплатить не удалось, попробуйте еще раз");
        }
        modelAndView.setViewName("bookingstatus");

        return modelAndView;
    }


}
