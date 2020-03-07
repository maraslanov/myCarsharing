package app.web.controller;

import app.persistance.entity.Type;
import app.web.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@PreAuthorize("hasAuthority('Admin')")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String getAllCategories(Map<String, Object> model) {
        Iterable<Type> types = categoryService.getCategories();
        model.put("types", types);
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategories(Map<String, Object> model) {
        Type type = new Type();
        model.put("type", type);
        model.put("title", "Добавление категории");
        model.put("action", "/admin/categories/add");
        model.put("btn_action", "Добавить");
        return "add_edit_categories";
    }


    @PostMapping("/admin/categories/add")
    public String addCategoriesPost(@ModelAttribute("type") Type type) {
        categoryService.insertCategories(type);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/edit/{id}")
    public String editCategories(@PathVariable Long id,
                                 Map<String, Object> model) {
        Type type = categoryService.getCategoryById(id);
        model.put("type", type);
        model.put("title", "Редактирование категории");
        model.put("action", "/admin/categories/edit/" + type.getId());
        model.put("btn_action", "Сохранить");
        return "add_edit_categories";
    }

    @PostMapping("/admin/categories/edit/{id}")
    public String editCategoriesPost(@ModelAttribute("type") Type type) {
        categoryService.updateCategories(type);
        return "redirect:/admin/categories";
    }
}