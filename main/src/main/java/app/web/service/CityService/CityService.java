package app.web.service.CityService;

import app.persistance.entity.City;

import java.util.List;

public interface CityService {
    List<City> findAll();

    City getById(Long id);
}
