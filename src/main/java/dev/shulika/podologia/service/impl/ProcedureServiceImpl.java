package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.repository.ProcedureRepository;
import dev.shulika.podologia.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    @Override
    public List<Procedure> findAll() {
        log.info("IN ProcedureServiceImpl - findAll - STARTED");
        List<Procedure> procedures = procedureRepository.findAll();
        if(procedures.isEmpty())
            return Collections.emptyList();

        log.info("IN ProcedureServiceImpl - findAll - FINISHED SUCCESSFULLY - ProcedureMapper::toDTO NOW");
        return procedures;
    }

    @Override
    public Procedure findById() {
        return null;
    }
}
