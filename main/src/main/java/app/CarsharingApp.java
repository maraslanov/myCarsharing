package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "app")
public class CarsharingApp {


    public static void main(String[] args) {
        SpringApplication.run(CarsharingApp.class, args);
    }

//    @Bean
//    CommandLineRunner runner(UserRepo userRepo, ModelRepo modelRepo, BrandRepo brandRepo,
//                             CityRepo cityRepo, TypeRepo typeRepo, CarRepo carRepo, BookingRepo bookingRepo, RoleRepo roleRepo) {
//        return args -> {
//            Role admin = new Role();
//            admin.setName("admin");
//            roleRepo.save(admin);
//            Role userRole = new Role();
//            userRole.setName("user");
//            roleRepo.save(userRole);
//            User user1 = new User();
//            user1.setFullname("user1");
//            user1.setEmail("qwe@local");
//            user1.setPassword(new BCryptPasswordEncoder().encode("123456"));
//            user1.setAccess(UserAccess.Есть.name());
//            user1.setRole(userRole);
//            userRepo.save(user1);
//            User user2 = new User();
//            user2.setEmail("admin@local");
//            user2.setPassword(new BCryptPasswordEncoder().encode("admin1"));
//            user2.setFullname("user2");
//            user2.setRole(admin);
//            user2.setAccess(UserAccess.Есть.name());
//            userRepo.save(user2);
//
//            Model model1 = new Model();
//            model1.setName("Corolla");
//            modelRepo.save(model1);
//            Model model2 = new Model();
//            model2.setName("Газель");
//            modelRepo.save(model2);
//
//            Brand brand1 = new Brand();
//            brand1.setName("Toyota");
//            brandRepo.save(brand1);
//            Brand brand2 = new Brand();
//            brand2.setName("ГАЗ");
//            brandRepo.save(brand2);
//
//            Type type1 = new Type();
//            type1.setName("легковой");
//            typeRepo.save(type1);
//            Type type2 = new Type();
//            type2.setName("грузовой");
//            typeRepo.save(type2);
//
//            City city1 = new City();
//            city1.setName("Москва");
//            cityRepo.save(city1);
//            City city2 = new City();
//            city2.setName("Зеленоград");
//            cityRepo.save(city2);
//
//            Car car1 = new Car();
//            car1.setPrice(2500);
//            car1.setModel(model1);
//            car1.setBrand(brand1);
////            car1.setPrice(100);
//            car1.setCity(city1);
//            car1.setType(type1);
//            car1.setState(State.NEW);
//            car1.setRegnumber("А566ВА");
//            carRepo.save(car1);
//
//            Car car2 = new Car();
//            car2.setPrice(3000);
//            car2.setModel(model2);
//            car2.setBrand(brand2);
////            car2.setPrice(50);
//            car2.setCity(city2);
//            car2.setType(type2);
//            car2.setState(State.OLD);
//            car2.setRegnumber("Б233РО");
//            carRepo.save(car2);
//
//            Car car3 = new Car();
//            car3.setPrice(1800);
//            car3.setModel(model2);
//            car3.setBrand(brand2);
////            car3.setPrice(75);
//            car3.setCity(city2);
//            car3.setType(type2);
//            car3.setState(State.REPAIRING);
//            car3.setRegnumber("Л544МИ");
//            carRepo.save(car3);
//
//            Booking booking1 = new Booking();
//            booking1.setCarId(car1.getId());
//            booking1.setUserId(user1.getId());
//            booking1.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-10"));
//            booking1.setDateTo(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-15"));
//            booking1.setPrice(12500.0);
//            booking1.setStatus(Status.UNPAID);
//            bookingRepo.save(booking1);
//
//            Booking booking2 = new Booking();
//            booking2.setCarId(car2.getId());
//            booking2.setUserId(user2.getId());
//            booking2.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-18"));
//            booking2.setDateTo(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-25"));
//            booking2.setPrice(21000.0);
//            booking2.setStatus(Status.UNPAID);
//            bookingRepo.save(booking2);
//            Booking booking3 = new Booking();
//            booking3.setCarId(car3.getId());
//            booking3.setUserId(user2.getId());
//            booking3.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-10"));
//            booking3.setDateTo(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-14"));
//            booking3.setPrice(7200.0);
//            booking3.setStatus(Status.UNPAID);
//            bookingRepo.save(booking3);
//
//        };
//    }

}