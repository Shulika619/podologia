package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoRequestDTO;
import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.PriceFullInfo;
import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.model.Specialist;
import dev.shulika.podologia.repository.PriceFullInfoRepository;
import dev.shulika.podologia.repository.ProcedureRepository;
import dev.shulika.podologia.repository.SpecialistRepository;
import dev.shulika.podologia.service.PriceFullInfoService;
import dev.shulika.podologia.util.PriceFullInfoMapper;
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
public class PriceFullInfoServiceImpl implements PriceFullInfoService {
    private final PriceFullInfoRepository priceFullInfoRepository;
    private final ProcedureRepository procedureRepository;
    private final SpecialistRepository specialistRepository;

    @Override
    public Page<PriceFullInfoResponseDTO> findAllByPage(Pageable pageable) {
        log.info("IN PriceFullInfoServiceImpl - findAll - STARTED");
        Page<PriceFullInfo> pricePages = priceFullInfoRepository.findAll(pageable);
        log.info("IN PriceFullInfoServiceImpl - findAll - FINISHED SUCCESSFULLY - PriceFullInfoMapper::toDTO NOW");
        return pricePages.map(PriceFullInfoMapper::toDTO);
    }

    @Override
    public PriceFullInfoResponseDTO findById(Long id) {
        log.info("IN PriceFullInfoServiceImpl - findById: {} - STARTED", id);
        PriceFullInfo price = priceFullInfoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "Price not found with id: " + id));
        log.info("IN PriceFullInfoServiceImpl - findById: {} - FINISHED SUCCESSFULLY - PriceFullInfoMapper.toDTO NOW", id);
        return PriceFullInfoMapper.toDTO(price);
    }

    @Override
    public PriceFullInfoResponseDTO create(PriceFullInfoRequestDTO priceFullInfoRequestDTO) {
        log.info("IN PriceFullInfoServiceImpl - create - STARTED");
        if (procedureRepository.existsByName(priceFullInfoRequestDTO.getProcedure().getName()))
            throw new ServiceBusinessException(priceFullInfoRequestDTO.getProcedure().getName(), "A procedure with this name already exists");
        if (specialistRepository.existsByName(priceFullInfoRequestDTO.getSpecialist().getName()))
            throw new ServiceBusinessException(priceFullInfoRequestDTO.getSpecialist().getName(), "A specialist with this name already exists");
        Procedure procedureReturned = procedureRepository.save(priceFullInfoRequestDTO.getProcedure());
        log.info("IN ProcedureCategoryServiceImpl - create: Procedure - SUCCESSFULLY");
        Specialist specialistReturned = specialistRepository.save(priceFullInfoRequestDTO.getSpecialist());
        log.info("IN ProcedureCategoryServiceImpl - create: Specialist - SUCCESSFULLY");
        PriceFullInfoRequestDTO priceFullInfoRequestDTOTemp = PriceFullInfoRequestDTO.builder()
                .procedure(procedureReturned)
                .specialist(specialistReturned)
                .minutes(priceFullInfoRequestDTO.getMinutes())
                .price(priceFullInfoRequestDTO.getPrice())
                .enabled(priceFullInfoRequestDTO.getEnabled())
                .build();
        PriceFullInfo priceFullInfoReturned = priceFullInfoRepository.save(PriceFullInfoMapper.fromDTO(priceFullInfoRequestDTOTemp));
        log.info("IN PriceFullInfoServiceImpl - created - FINISHED SUCCESSFULLY");
        return PriceFullInfoMapper.toDTO(priceFullInfoReturned);
    }

    @Override
    public PriceFullInfoResponseDTO update(Long id, PriceFullInfoRequestDTO priceFullInfoRequestDTO) {
        log.info("IN PriceFullInfoServiceImpl - update price by id: {} - STARTED", id);
        if (priceFullInfoRequestDTO.getProcedure().getName().isBlank())
            throw new ServiceBusinessException("name", "Procedure name must not be empty or null");
        if (priceFullInfoRequestDTO.getSpecialist().getName().isBlank())
            throw new ServiceBusinessException("name", "Specialist name must not be empty or null");
        Optional<PriceFullInfo> existPrice = priceFullInfoRepository.findById(id);
        if (!existPrice.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Price not found with id: " + id);
        PriceFullInfo price = existPrice.get();

        final Optional<Procedure> existingProcedure = procedureRepository.findById(price.getProcedure().getId());
        if (!existingProcedure.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Procedure not found");
        Procedure procedure = existingProcedure.get();
        procedure.setName(priceFullInfoRequestDTO.getProcedure().getName());
        procedure.setEnabled(priceFullInfoRequestDTO.getProcedure().getEnabled());
        procedure.setCategoryId(priceFullInfoRequestDTO.getProcedure().getCategoryId());

        final Optional<Specialist> existingSpecialist = specialistRepository.findById(price.getSpecialist().getId());
        if (!existingSpecialist.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Specialist not found");
        Specialist specialist = existingSpecialist.get();
        specialist.setName(priceFullInfoRequestDTO.getSpecialist().getName());
        specialist.setDescription(priceFullInfoRequestDTO.getSpecialist().getDescription());
        specialist.setEnabled(priceFullInfoRequestDTO.getSpecialist().getEnabled());

        price.setProcedure(procedure);
        price.setSpecialist(specialist);
        price.setMinutes(priceFullInfoRequestDTO.getMinutes());
        price.setPrice(priceFullInfoRequestDTO.getPrice());
        price.setEnabled(priceFullInfoRequestDTO.getEnabled());
        PriceFullInfo priceFullInfoReturned = priceFullInfoRepository.save(price);
        log.info("IN PriceFullInfoServiceImpl - update price by id: {} - FINISHED SUCCESSFULLY", id);
        return PriceFullInfoMapper.toDTO(priceFullInfoReturned);
    }

    @Override
    public PriceFullInfoResponseDTO updateFields(Long id, Map<String, Object> fields) {
        log.info("IN PriceFullInfoServiceImpl - updateFields: STARTED");
        Optional<PriceFullInfo> existPrice = priceFullInfoRepository.findById(id);
        if (!existPrice.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Price not found with id: " + id);
        PriceFullInfo price = existPrice.get();
        log.info("IN PriceFullInfoServiceImpl - updateFields: Check fields NOW");
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(PriceFullInfo.class, key);
            field.setAccessible(true);
            if (key.contains("procedure")) {
                Procedure procedure = procedureRepository.findById(price.getProcedure().getId())
                        .orElseThrow(() -> new ObjectNotFoundException("", "Procedure not found"));
                Map<String, Object> procedureRequestMap = (Map<String, Object>) fields.get("procedure");
                procedureRequestMap.forEach((procedureKey, procedureValue) -> {
                    Field procField = ReflectionUtils.findField(Procedure.class, procedureKey);
                    procField.setAccessible(true);
                    if (procedureKey.equals("categoryId"))
                        ReflectionUtils.setField(procField, procedure, Long.valueOf((Integer) procedureValue));
                    else
                        ReflectionUtils.setField(procField, procedure, procedureValue);
                });
            } else if (key.contains("specialist")) {
                Specialist specialist = specialistRepository.findById(price.getSpecialist().getId())
                        .orElseThrow(() -> new ObjectNotFoundException("", "Specialist not found"));
                Map<String, Object> specialistRequestMap = (Map<String, Object>) fields.get("specialist");
                specialistRequestMap.forEach((specialistKey, specialistValue) -> {
                    Field procField = ReflectionUtils.findField(Specialist.class, specialistKey);
                    procField.setAccessible(true);
                    ReflectionUtils.setField(procField, specialist, specialistValue);
                });
            } else
                ReflectionUtils.setField(field, price, value);
        });

        PriceFullInfo priceFullInfoReturned = priceFullInfoRepository.save(price);
        log.info("IN PriceFullInfoServiceImpl - updateFields - FINISHED SUCCESSFULLY");
        return PriceFullInfoMapper.toDTO(priceFullInfoReturned);
    }

    @Override
    public void delete(Long id) {
        log.info("IN PriceFullInfoServiceImpl - delete by id: {} - STARTED", id);
        if (!priceFullInfoRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "Price not found with id: " + id);
        priceFullInfoRepository.deleteById(id);
        log.info("IN PriceFullInfoServiceImpl - delete by id: {} - FINISHED SUCCESSFULLY", id);
    }
}