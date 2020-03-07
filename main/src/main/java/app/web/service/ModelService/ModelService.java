package app.web.service.ModelService;

import app.persistance.entity.Model;

import java.util.List;

public interface ModelService {
    List<Model> findAll();

    Model getById(Long id);
}
