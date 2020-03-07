package app.web.service.BrandService;

import app.persistance.entity.Brand;

import java.util.List;

public interface BrandService {

    Brand save(Brand brand); //добавить марку в БД

    void delete(Long brandId); //удалить марку из БД

    Brand edit(Brand brand); //редактировать имя марки

    List<Brand> getAll(); //добавить список всех марок из БД в list

    Brand getById(Long id);
}
