package app.web.service.CarService;


import app.persistance.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findById(Long id);

    Car addCar(Car car);

    List<app.persistance.entity.Car> findFilteredCars(String brandId,
                                                      String priceFrom,
                                                      String priceTo,
                                                      String dateFrom,
                                                      String dateTo);

}
