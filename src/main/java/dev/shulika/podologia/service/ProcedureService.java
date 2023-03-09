package dev.shulika.podologia.service;

import dev.shulika.podologia.model.Procedure;

import java.util.List;

public interface ProcedureService {
    List<Procedure> findAll();
    Procedure findById();
}
