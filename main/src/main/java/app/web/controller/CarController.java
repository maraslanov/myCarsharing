package app.web.controller;

import app.persistance.entity.*;
import app.utils.ImageUtil;
import app.web.pojo.Status;
import app.web.service.BookingService.BookingService;
import app.web.service.BrandService.BrandService;
import app.web.service.CarService.CarService;
import app.web.service.CategoryService.CategoryService;
import app.web.service.CityService.CityService;
import app.web.service.ModelService.ModelService;
import app.web.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CarController {
    private long dayInMilliseconds = 86400000;

    @Autowired
    private CarService carService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private UserService userService;

    @Value("${imagePath}")
    String imagePath;

    @Value("${imgURL}")
    String imgURL;

    @GetMapping(value = "/cars")
    public String getAllCars(Map<String, Object> model) {

        String brandId = "0";
        String priceFrom = "10";
        String priceTo = "50000";
        String dateFrom = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        String dateTo = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        Iterable<Car> cars = carService.findFilteredCars(brandId, priceFrom, priceTo, dateFrom, dateTo);


        model.put("brandId", brandId);
        model.put("prices", priceFrom + " - " + priceTo);
        model.put("dateFrom", dateFrom);
        model.put("dateTo", dateTo);
        model.put("cars", cars);
        Iterable<Brand> brands = brandService.getAll();
        model.put("brands", brands);
        model.put("notification", "");
        model.put("imagePath", imagePath);
        model.put("imgURL", imgURL);
        User currentUser = userService.getCurrentUser();
        Integer privilege = (currentUser!=null && "admin".equalsIgnoreCase(currentUser.getRole().getName()))? 1 : 0;
        model.put("privilege", privilege);
        return "cars";
    }

    @PostMapping(value = "/getFilteredCars")
    public String getCarList(@ModelAttribute("brandId") String brandId,
                             @ModelAttribute("prices") String prices,
                             @ModelAttribute("dateFrom") String dateFrom,
                             @ModelAttribute("dateTo") String dateTo,
                             Map<String, Object> model) {
        if (dateFrom.isEmpty() || dateTo.isEmpty()) {
            model.put("notification", "Укажите даты");
            return "cars";
        }
        List<String> priceList = getPrices(prices);
        String priceFrom = priceList.get(0);
        String priceTo = priceList.get(1);
        Iterable<Car> cars = carService.findFilteredCars(brandId, priceFrom, priceTo, dateFrom, dateTo);
        model.put("cars", cars);
        Iterable<Brand> brands = brandService.getAll();
        model.put("brands", brands);
        model.put("brandId", brandId);
        model.put("prices", priceFrom + " - " + priceTo);
        model.put("dateFrom", dateFrom);
        model.put("dateTo", dateTo);
        User currentUser = userService.getCurrentUser();
        Integer privilege = (currentUser!=null && "admin".equalsIgnoreCase(currentUser.getRole().getName()))? 1 : 0;
        model.put("privilege", privilege);
        model.put("notification", "");
        model.put("imgURL", imgURL);
        return "cars";
    }

    public List<String> getPrices(String str) {
        return Collections.list(new StringTokenizer(str, "- ")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/addcarpage", method = RequestMethod.POST)
    public String updateCar(@ModelAttribute Car car, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addcarpage");
        String imageName = UUID.randomUUID().toString() + ".png";
        if (!ImageUtil.upload(imageFile, imagePath + "/" + imageName)) {
            imageName = null;
        }
        car.setImage(imageName);
        carService.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/createcar")
    public ModelAndView createCar() {
        Car car = new Car();
        List<Brand> marks = brandService.getAll();
        List<Type> categories = categoryService.getCategories();
        List<City> cities = cityService.findAll();
        List<Model> models = modelService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", car);
        modelAndView.addObject("marks", marks);
        modelAndView.addObject("models", models);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("action", "/addcarpage");
        modelAndView.setViewName("car-edit");
        return modelAndView;
    }

    @GetMapping("/editcar/{id}")
    public ModelAndView editCar(@PathVariable Long id) {
        Car car = carService.findById(id);
        List<Brand> marks = brandService.getAll();
        List<Type> categories = categoryService.getCategories();
        List<City> cities = cityService.findAll();
        List<Model> models = modelService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", car);
        modelAndView.addObject("marks", marks);
        modelAndView.addObject("models", models);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("action", "/addcarpage");
        modelAndView.setViewName("car-edit");
        return modelAndView;
    }

    @GetMapping(value = "/cars/view/{id}")
    public String viewCarById(@PathVariable("id") Long id,
                              Map<String, Object> model) {
        Car car = carService.findById(id);
        String status = "";
        List<Booking> bookings = bookingService.findBookingByCarId(id);
        for (Booking booking : bookings) {
            if (booking.getDateTo().getTime() > new Date().getTime()) {
                status = "Забронирован до " + booking.getDateTo();
            }
        }
        City city = car.getCity();
        Brand brand = car.getBrand();
        Model modelCar = car.getModel();
        Type type = car.getType();
        String minDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String minDateTo = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() + dayInMilliseconds);
        model.put("car", car);
        model.put("city", city);
        model.put("brand", brand);
        model.put("modelCar", modelCar);
        model.put("type", type);
        model.put("minDate", minDate);
        model.put("minDateTo", minDateTo);
        model.put("status", status);
        model.put("action", "/cars/view/" + car.getId() + "/booking");
        model.put("imagePath", imagePath);
        model.put("imgURL", imgURL);
        return "view_car";
    }

    @PostMapping(value = "/cars/view/{id}/booking")
    public String bookingCar(@PathVariable("id") Long id,
                             @ModelAttribute("dateFrom") String dateFrom,
                             @ModelAttribute("dateTo") String dateTo,
                             Map<String, Object> model) throws ParseException {
        List<Booking> bookings = bookingService.findBookingByCarId(id);
        for (Booking booking : bookings) {
            if (booking.getDateTo().getTime() > new Date().getTime()) {
                return "redirect:/cars/view/" + id;
            }
        }
        Car car = carService.findById(id);
        User user = userService.getCurrentUser();
        Booking booking = new Booking();
        booking.setCarId(car.getId());
        booking.setUserId(user.getId());
        booking.setStatus(Status.UNPAID);
        booking.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom));
        booking.setDateTo(new SimpleDateFormat("yyyy-MM-dd").parse(dateTo));
        booking.setPrice(car.getPrice() * Math.ceil((double) (booking.getDateTo().getTime() - booking.getDateFrom().getTime()) / dayInMilliseconds));
        bookingService.save(booking);

        return "redirect:/my/booking";
    }

    @GetMapping("/admin/cars")
    public ModelAndView adminCarList() {
        List<Car> list = carService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("admin-carlist.html");

        return modelAndView;
    }
}