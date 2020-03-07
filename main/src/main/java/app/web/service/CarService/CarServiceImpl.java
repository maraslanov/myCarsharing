package app.web.service.CarService;

import app.persistance.entity.Car;
import app.web.repos.AdvancedCarRepo;
import app.web.repos.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private AdvancedCarRepo advancedCarRepo;

    @Autowired
    private CarRepo carRepo;

    @Override
    public List<Car> findAll() {
        return carRepo.findAll();
    }


    public List<app.persistance.entity.Car> findFilteredCars(String brandId,
                                                             String priceFrom,
                                                             String priceTo,
                                                             String dateFrom,
                                                             String dateTo) {
        Long i = brandId.equals("0") ? null : Long.parseLong(brandId);
        Float pf = Float.parseFloat(priceFrom);
        Float pt = Float.parseFloat(priceTo);
        java.sql.Date df;
        java.sql.Date dt;
        try {
            df = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateFrom).getTime());
            dt = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateTo).getTime());
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Something wrong with dates");
        }
        return advancedCarRepo.findFilteredCars(i, pf, pt, df, dt);
    }


    @Override
    public Car findById(Long id) {
        return carRepo.findById(id).orElse(null);
    }

    @Override
    public Car addCar(Car car) {
        return carRepo.save(car);
    }


}
