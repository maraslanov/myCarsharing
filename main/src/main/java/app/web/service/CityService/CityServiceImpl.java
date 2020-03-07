package app.web.service.CityService;

import app.persistance.entity.City;
import app.web.repos.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    @Override
    public City getById(Long id) {
        return cityRepo.findById(id).orElse(null);
    }
}
