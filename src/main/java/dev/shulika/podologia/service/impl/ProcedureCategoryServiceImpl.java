package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryRequestDTO;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.Category;
import dev.shulika.podologia.model.ProcedureCategory;
import dev.shulika.podologia.repository.CategoryRepository;
import dev.shulika.podologia.repository.ProcedureCategoryRepository;
import dev.shulika.podologia.service.ProcedureCategoryService;
import dev.shulika.podologia.util.ProcedureCategoryMapper;
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
public class ProcedureCategoryServiceImpl implements ProcedureCategoryService {

    private final ProcedureCategoryRepository procedureCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProcedureCategoryResponseDTO> findAll(Pageable pageable) {
        log.info("IN ProcedureCategoryServiceImpl - findAll - STARTED");
        Page<ProcedureCategory> procedureCategoryPages = procedureCategoryRepository.findAll(pageable);
        log.info("IN ProcedureCategoryServiceImpl - findAll - FINISHED SUCCESSFULLY - ProcedureCategoryMapper::toDTO NOW");
        return procedureCategoryPages.map(ProcedureCategoryMapper::toDTO);
    }

    @Override
    public ProcedureCategoryResponseDTO findById(Long id) {
        log.info("IN ProcedureCategoryServiceImpl - findById: {} - STARTED", id);
        ProcedureCategory procedureCategory = procedureCategoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id));
        log.info("IN ProcedureCategoryServiceImpl - findById: {} - FINISHED SUCCESSFULLY - ProcedureCategoryMapper.toDTO NOW", id);
        return ProcedureCategoryMapper.toDTO(procedureCategory);
    }

    @Override
    public ProcedureCategoryResponseDTO create(ProcedureCategoryRequestDTO procedureCategoryRequestDTO) {
        log.info("IN ProcedureCategoryServiceImpl - create: STARTED");
        if (procedureCategoryRepository.existsByName(procedureCategoryRequestDTO.getName()))
            throw new ServiceBusinessException(procedureCategoryRequestDTO.getName(), "A procedure with this name already exists");
        if (categoryRepository.existsByName(procedureCategoryRequestDTO.getCategory().getName()))
            throw new ServiceBusinessException(procedureCategoryRequestDTO.getCategory().getName(), "A category with this name already exists");
        Category categoryReturned = categoryRepository.save(procedureCategoryRequestDTO.getCategory());
        log.info("IN ProcedureCategoryServiceImpl - create: Category - SUCCESSFULLY");
        ProcedureCategoryRequestDTO proCatReqDtoTemp = ProcedureCategoryRequestDTO.builder()
                .name(procedureCategoryRequestDTO.getName())
                .enabled(procedureCategoryRequestDTO.getEnabled())
                .category(categoryReturned)
                .build();
        ProcedureCategory procedureCategoryReturned = procedureCategoryRepository.save(ProcedureCategoryMapper.fromDTO(proCatReqDtoTemp));
        log.info("IN ProcedureCategoryServiceImpl - created: ProcedureCategory - FINISHED SUCCESSFULLY");
        return ProcedureCategoryMapper.toDTO(procedureCategoryReturned);
    }

    @Override
    public ProcedureCategoryResponseDTO update(Long id, ProcedureCategoryRequestDTO procedureCategoryRequestDTO) {
        log.info("IN ProcedureCategoryServiceImpl - update procedure by id: {} - STARTED", id);
        if (procedureCategoryRequestDTO.getCategory().getName().isBlank())
            throw new ServiceBusinessException("name", "Category name must not be empty or null");
        Optional<ProcedureCategory> existingProcedureCategory = procedureCategoryRepository.findById(id);
        if (!existingProcedureCategory.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id);
        ProcedureCategory procedureCategory = existingProcedureCategory.get();

        final Optional<Category> existingCategory = categoryRepository.findById(procedureCategory.getCategory().getId());
        if (!existingCategory.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Category not found");
        Category category = existingCategory.get();

        category.setName(procedureCategoryRequestDTO.getCategory().getName());
        category.setDescription(procedureCategoryRequestDTO.getCategory().getDescription());
        category.setEnabled(procedureCategoryRequestDTO.getCategory().getEnabled());
        categoryRepository.save(category);
        log.info("IN ProcedureCategoryServiceImpl - update: Category - Updated");

        procedureCategory.setName(procedureCategoryRequestDTO.getName());
        procedureCategory.setEnabled(procedureCategoryRequestDTO.getEnabled());
        procedureCategory.setCategory(category);
        ProcedureCategory procedureCategoryReturned = procedureCategoryRepository.save(procedureCategory);
        log.info("IN ProcedureCategoryServiceImpl - update procedure by id: {} - FINISHED SUCCESSFULLY", id);
        return ProcedureCategoryMapper.toDTO(procedureCategoryReturned);
    }

    @Override
    public ProcedureCategoryResponseDTO updateProcedureFields(Long id, Map<String, Object> fields) {
        log.info("IN ProcedureCategoryServiceImpl - updateProcedureFields: STARTED");
        Optional<ProcedureCategory> existingProcedureCategory = procedureCategoryRepository.findById(id);
        if (!existingProcedureCategory.isPresent()) {
            throw new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id);
        }
        ProcedureCategory procedureCategory = existingProcedureCategory.get();
        log.info("IN ProcedureCategoryServiceImpl - updateProcedureFields: Check fields NOW");

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ProcedureCategory.class, key);
            field.setAccessible(true);
            if (key.contains("category")) {
                Category category = categoryRepository.findById(procedureCategory.getCategory().getId())
                        .orElseThrow(() -> new ObjectNotFoundException("", "Category not found with id: "));
                Map<String, Object> categoryRequestMap = (Map<String, Object>) fields.get("category");
                categoryRequestMap.forEach((catKey, catValue) -> {
                    Field catField = ReflectionUtils.findField(Category.class, catKey);
                    catField.setAccessible(true);
                    ReflectionUtils.setField(catField, category, catValue);
                });
            } else
                ReflectionUtils.setField(field, procedureCategory, value);
        });

        ProcedureCategory procedureCategoryReturned = procedureCategoryRepository.save(procedureCategory);
        log.info("IN ProcedureCategoryServiceImpl - updateProcedureCategoryFields - FINISHED SUCCESSFULLY");
        return ProcedureCategoryMapper.toDTO(procedureCategoryReturned);
    }

    @Override
    public void delete(Long id) {
        log.info("IN ProcedureCategoryServiceImpl - delete by id: {} - STARTED", id);
        if (!procedureCategoryRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "Procedure not found with id: " + id);
        procedureCategoryRepository.deleteById(id);
        log.info("IN ProcedureCategoryServiceImpl - delete by id: {} - FINISHED SUCCESSFULLY", id);
    }
}
