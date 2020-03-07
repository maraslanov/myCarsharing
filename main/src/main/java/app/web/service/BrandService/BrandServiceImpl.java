package app.web.service.BrandService;

import app.persistance.entity.Brand;
import app.web.repos.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public Brand save(Brand brand) {
        return brandRepo.save(brand);
    }

    @Override
    public void delete(Long brandId) {
        brandRepo.deleteById(brandId);
    }

    @Override
    public Brand edit(Brand brand) {
        return brandRepo.save(brand);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepo.findAll();
    }

    @Override
    public Brand getById(Long id) {
        return brandRepo.findById(id).orElse(null);
    }
}
