package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.specialist.SpecialistRequestDTO;
import dev.shulika.podologia.dto.specialist.SpecialistResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.Specialist;
import dev.shulika.podologia.repository.SpecialistRepository;
import dev.shulika.podologia.service.SpecialistService;
import dev.shulika.podologia.util.SpecialistMapper;
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
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;

    @Override
    public List<SpecialistResponseDTO> findAll() {
        log.info("IN SpecialistServiceImpl - findAll - STARTED");
        List<Specialist> specialists = specialistRepository.findAll();
        if (specialists.isEmpty())
            return Collections.emptyList();
        log.info("IN SpecialistServiceImpl - findAll - FINISHED SUCCESSFULLY - SpecialistMapper::toDTO NOW");
        return specialists.stream().map(SpecialistMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SpecialistResponseDTO findById(Long id) {
        log.info("IN SpecialistServiceImpl - findById: {} - STARTED", id);
        Specialist specialist = specialistRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "Specialist not found with id: " + id));
        log.info("IN SpecialistServiceImpl - findById: {} - FINISHED SUCCESSFULLY - SpecialistMapper.toDTO NOW", id);
        return SpecialistMapper.toDTO(specialist);
    }

    @Override
    public SpecialistResponseDTO create(SpecialistRequestDTO specialistRequestDTO) {
        log.info("IN SpecialistServiceImpl - create - STARTED");
        if (specialistRepository.existsByName(specialistRequestDTO.getName()))
            throw new ServiceBusinessException(specialistRequestDTO.getName(), "A specialist with this name already exists");
        Specialist specialistReturned = specialistRepository.save(SpecialistMapper.fromDTO(specialistRequestDTO));
        log.info("IN SpecialistServiceImpl - created - FINISHED SUCCESSFULLY");
        return SpecialistMapper.toDTO(specialistReturned);
    }

    @Override
    public SpecialistResponseDTO update(Long id, SpecialistRequestDTO specialistRequestDTO) {
        log.info("IN SpecialistServiceImpl - update specialist by id: {} - STARTED", id);
        Optional<Specialist> existingSpecialist = specialistRepository.findById(id);
        if (!existingSpecialist.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Specialist not found with id: " + id);
        Specialist specialist = existingSpecialist.get();
        specialist.setName(specialistRequestDTO.getName());
        specialist.setDescription(specialistRequestDTO.getDescription());
        specialist.setEnabled(specialistRequestDTO.getEnabled());
        Specialist specialistReturned = specialistRepository.save(specialist);
        log.info("IN SpecialistServiceImpl - update specialist by id: {} - FINISHED SUCCESSFULLY", id);
        return SpecialistMapper.toDTO(specialistReturned);
    }

    @Override
    public SpecialistResponseDTO updateSpecialistFields(Long id, Map<String, Object> fields) {
        log.info("IN SpecialistServiceImpl - updateSpecialistFields: STARTED");
        Optional<Specialist> existingSpecialist = specialistRepository.findById(id);
        if (!existingSpecialist.isPresent()) {
            throw new ObjectNotFoundException(id.toString(), "Specialist not found with id: " + id);
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Specialist.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingSpecialist.get(), value);
        });
        Specialist specialistReturned = specialistRepository.save(existingSpecialist.get());
        log.info("IN SpecialistServiceImpl - updateSpecialistFields - FINISHED SUCCESSFULLY");
        return SpecialistMapper.toDTO(specialistReturned);
    }

    @Override
    public void delete(Long id) {
        log.info("IN SpecialistServiceImpl - delete by id: {} - STARTED", id);
        if (!specialistRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "Specialist not found with id: " + id);
        specialistRepository.deleteById(id);
        log.info("IN SpecialistServiceImpl - delete by id: {} - FINISHED SUCCESSFULLY", id);
    }
}
