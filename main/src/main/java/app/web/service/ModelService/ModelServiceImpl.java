package app.web.service.ModelService;

import app.persistance.entity.Model;
import app.web.repos.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepo modelRepo;

    @Override
    public List<Model> findAll() {
        return modelRepo.findAll();
    }

    @Override
    public Model getById(Long id) {
        return modelRepo.findById(id).orElse(null);
    }
}
