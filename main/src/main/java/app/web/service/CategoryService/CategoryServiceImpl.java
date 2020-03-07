package app.web.service.CategoryService;

import app.persistance.entity.Type;
import app.web.repos.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private TypeRepo carTypeRepo;

    @Override
    public List<Type> getCategories() {
        return carTypeRepo.findAll();
    }

    @Override
    public Type insertCategories(Type carType) {
        return carTypeRepo.save(carType);
    }

    @Override
    public Type getCategoryById(Long id) {
        return carTypeRepo.findById(id).orElse(new Type());
    }

    @Override
    public Type updateCategories(Type carType) {
        return carTypeRepo.save(carType);
    }
}