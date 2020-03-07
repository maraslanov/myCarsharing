package app.web.controller;

import app.persistance.entity.Brand;
import app.web.service.BrandService.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("hasAuthority('Admin')")
@Controller
public class BrandController {

    @Autowired
    BrandServiceImpl brandService;

    @GetMapping("/admin/brand-list/") //страница вывода списка марок авто
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", brandService.getAll());
        modelAndView.setViewName("brand-list");

        return modelAndView;
    }

    @GetMapping("/admin/add-brand/") //страница добавления марки авто
    public ModelAndView add() {
        Brand brand = new Brand();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("brand", brand);
        modelAndView.setViewName("add-brand");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/add-brand/", method = RequestMethod.POST) //добавление марки авто и возврат к списку
    public String addPBrand(@ModelAttribute Brand brand) {

        brandService.save(brand);

        return "redirect:/admin/brand-list/";
    }

    @RequestMapping(value="/admin/remove-brand/{id}", method=RequestMethod.GET) //удаление марки авто
    public String deleteItem(@PathVariable Long id) {

        brandService.delete(id);

        return "redirect:/admin/brand-list/";
    }

    @GetMapping("/admin/edit-brand/{id}") //страница редактирования имени марки авто
    public ModelAndView edit(@PathVariable Long id) {

        Brand brand = brandService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("brand", brand);
        modelAndView.setViewName("edit-brand");

        return modelAndView;
    }


    @RequestMapping(value = "/admin/edit-brand/", method = RequestMethod.POST) //редактирование имени марки авто
    public String editBrand(@ModelAttribute Brand brand) {

        brandService.edit(brand);

        return "redirect:/admin/brand-list/";
    }
}