package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.ProcedureRequestDTO;
import dev.shulika.podologia.dto.ProcedureResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.repository.ProcedureRepository;
import dev.shulika.podologia.service.ProcedureService;
import dev.shulika.podologia.util.ProcedureMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;

    @Override
    public List<ProcedureResponseDTO> findAll() {
        log.info("IN ProcedureServiceImpl - findAll - STARTED");
        List<Procedure> procedures = procedureRepository.findAll();
        if (procedures.isEmpty())
            return Collections.emptyList();
        log.info("IN ProcedureServiceImpl - findAll - FINISHED SUCCESSFULLY - ProcedureMapper::toDTO NOW");
        return procedures.stream().map(ProcedureMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProcedureResponseDTO findById(Long id) {
        log.info("IN ProcedureServiceImpl - findById: {} - STARTED", id);
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id));
        log.info("IN ProcedureServiceImpl - findById: {} - FINISHED SUCCESSFULLY - ProcedureMapper.toDTO NOW", id);
        return ProcedureMapper.toDTO(procedure);
    }

    @Override
    public void create(ProcedureRequestDTO procedureRequestDTO) {
        log.info("IN ProcedureServiceImpl - create: STARTED");
        if (procedureRepository.existsByName(procedureRequestDTO.getName()))
            throw new ServiceBusinessException(procedureRequestDTO.getName(), "A procedure with this name already exists");
        procedureRepository.save(ProcedureMapper.fromDTO(procedureRequestDTO));
        log.info("IN ProcedureServiceImpl - created - FINISHED SUCCESSFULLY");
    }

    @Override
    public void update(Long id, ProcedureRequestDTO procedureRequestDTO) {
        log.info("IN ProcedureServiceImpl - update procedure by id: {} - STARTED", id);
        Optional<Procedure> existingProcedure = procedureRepository.findById(id);
        if (!existingProcedure.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id);
        Procedure procedure = existingProcedure.get();
        procedure.setCategoryId(procedureRequestDTO.getCategoryId());
        procedure.setName(procedureRequestDTO.getName());
        procedure.setPodologExpertMinutes(procedureRequestDTO.getPodologExpertMinutes());
        procedure.setPodologExpertPrice(procedureRequestDTO.getPodologExpertPrice());
        procedure.setPodologMinutes(procedureRequestDTO.getPodologMinutes());
        procedure.setPodologPrice(procedureRequestDTO.getPodologPrice());
        procedure.setEnabled(procedureRequestDTO.getEnabled());
        procedureRepository.save(procedure);
        log.info("IN ProcedureServiceImpl - update procedure by id: {} - FINISHED SUCCESSFULLY", id);
    }

    @Override
    public void updateProcedureFields(Long id, Map<String, Object> fields) {
        log.info("IN ProcedureServiceImpl - updateProcedureFields: STARTED");
        Optional<Procedure> existingProcedure = procedureRepository.findById(id);
        if (!existingProcedure.isPresent()) {
            throw new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id);
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Procedure.class, key);
            field.setAccessible(true);
            if (key.equals("categoryId"))
                ReflectionUtils.setField(field, existingProcedure.get(), Long.valueOf((Integer) value));
            else
                ReflectionUtils.setField(field, existingProcedure.get(), value);
        });
        procedureRepository.save(existingProcedure.get());
        log.info("IN ProcedureServiceImpl - updateProcedureFields - FINISHED SUCCESSFULLY");
    }

    @Override
    public void delete(Long id) {
        log.info("IN ProcedureServiceImpl - delete by id: {} - STARTED", id);
        if (!procedureRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id);
        procedureRepository.deleteById(id);
        log.info("IN ProcedureServiceImpl - delete by id: {} - FINISHED SUCCESSFULLY", id);
    }
}
