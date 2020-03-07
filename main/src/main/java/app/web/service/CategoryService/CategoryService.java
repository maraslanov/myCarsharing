package app.web.service.CategoryService;

import app.persistance.entity.Type;

import java.util.List;

public interface CategoryService {

    List<Type> getCategories();

    Type getCategoryById(Long id);

    Type insertCategories(Type carType);

    Type updateCategories(Type carType);
}

