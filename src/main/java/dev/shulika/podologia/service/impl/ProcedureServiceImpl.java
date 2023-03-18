package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.procedure.ProcedureRequestDTO;
import dev.shulika.podologia.dto.procedure.ProcedureResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.repository.CategoryRepository;
import dev.shulika.podologia.repository.ProcedureRepository;
import dev.shulika.podologia.service.ProcedureService;
import dev.shulika.podologia.util.ProcedureMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProcedureResponseDTO> findAll(Pageable pageable) {
        log.info("IN ProcedureServiceImpl - findAll - STARTED");
        Page<Procedure> procedures = procedureRepository.findAll(pageable);
        log.info("IN ProcedureServiceImpl - findAll - FINISHED SUCCESSFULLY - ProcedureMapper::toDTO NOW");
        return procedures.map(ProcedureMapper::toDTO);
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
    public ProcedureResponseDTO create(ProcedureRequestDTO procedureRequestDTO) {
        log.info("IN ProcedureServiceImpl - create: STARTED");
        if (procedureRepository.existsByName(procedureRequestDTO.getName()))
            throw new ServiceBusinessException(procedureRequestDTO.getName(), "A procedure with this name already exists");
        if (!categoryRepository.existsById(procedureRequestDTO.getCategoryId()))
            throw new ObjectNotFoundException(procedureRequestDTO.getCategoryId().toString(), "Category id does not exist");
        Procedure procedureReturned = procedureRepository.save(ProcedureMapper.fromDTO(procedureRequestDTO));
        log.info("IN ProcedureServiceImpl - created - FINISHED SUCCESSFULLY");
        return ProcedureMapper.toDTO(procedureReturned);
    }

    @Override
    public ProcedureResponseDTO update(Long id, ProcedureRequestDTO procedureRequestDTO) {
        log.info("IN ProcedureServiceImpl - update procedure by id: {} - STARTED", id);
        Optional<Procedure> existingProcedure = procedureRepository.findById(id);
        if (!existingProcedure.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id);
        Procedure procedure = existingProcedure.get();
        procedure.setCategoryId(procedureRequestDTO.getCategoryId());
        procedure.setName(procedureRequestDTO.getName());
        procedure.setEnabled(procedureRequestDTO.getEnabled());
        Procedure procedureReturned = procedureRepository.save(procedure);
        log.info("IN ProcedureServiceImpl - update procedure by id: {} - FINISHED SUCCESSFULLY", id);
        return ProcedureMapper.toDTO(procedureReturned);
    }

    @Override
    public ProcedureResponseDTO updateProcedureFields(Long id, Map<String, Object> fields) {
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
        Procedure procedureReturned = procedureRepository.save(existingProcedure.get());
        log.info("IN ProcedureServiceImpl - updateProcedureFields - FINISHED SUCCESSFULLY");
        return ProcedureMapper.toDTO(procedureReturned);
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
