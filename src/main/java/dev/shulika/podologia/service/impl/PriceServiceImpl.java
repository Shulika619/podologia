package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.price.PriceRequestDTO;
import dev.shulika.podologia.dto.price.PriceResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.model.Price;
import dev.shulika.podologia.repository.PriceRepository;
import dev.shulika.podologia.service.PriceService;
import dev.shulika.podologia.util.PriceMapper;
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
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    @Override
    public Page<PriceResponseDTO> findAllByPage(Pageable pageable) {
        log.info("IN PriceServiceImpl - findAll - STARTED");
        Page<Price> pricePages = priceRepository.findAll(pageable);
        log.info("IN PriceServiceImpl - findAll - FINISHED SUCCESSFULLY - PriceMapper::toDTO NOW");
        return pricePages.map(PriceMapper::toDTO);
    }

    @Override
    public PriceResponseDTO findById(Long id) {
        log.info("IN PriceServiceImpl - findById: {} - STARTED", id);
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "Price not found with id: " + id));
        log.info("IN PriceServiceImpl - findById: {} - FINISHED SUCCESSFULLY - PriceMapper.toDTO NOW", id);
        return PriceMapper.toDTO(price);
    }

    @Override
    public PriceResponseDTO create(PriceRequestDTO priceRequestDTO) {
        log.info("IN PriceServiceImpl - create - STARTED");
        Price priceReturned = priceRepository.save(PriceMapper.fromDTO(priceRequestDTO));
        log.info("IN PriceServiceImpl - created - FINISHED SUCCESSFULLY");
        return PriceMapper.toDTO(priceReturned);
    }

    @Override
    public PriceResponseDTO update(Long id, PriceRequestDTO priceRequestDTO) {
        log.info("IN PriceServiceImpl - update price by id: {} - STARTED", id);
        Optional<Price> existPrice = priceRepository.findById(id);
        if (!existPrice.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Price not found with id: " + id);
        Price price = existPrice.get();
        price.setProcedureId(priceRequestDTO.getProcedureId());
        price.setSpecialistId(priceRequestDTO.getSpecialistId());
        price.setMinutes(priceRequestDTO.getMinutes());
        price.setPrice(priceRequestDTO.getPrice());
        price.setEnabled(priceRequestDTO.getEnabled());
        Price priceReturned = priceRepository.save(price);
        log.info("IN PriceServiceImpl - update price by id: {} - FINISHED SUCCESSFULLY", id);
        return PriceMapper.toDTO(priceReturned);
    }

    @Override
    public PriceResponseDTO updateFields(Long id, Map<String, Object> fields) {
        log.info("IN PriceServiceImpl - updateFields: STARTED");
        Optional<Price> existingPrice = priceRepository.findById(id);
        if (!existingPrice.isPresent()) {
            throw new ObjectNotFoundException(id.toString(), "Price not found with id: " + id);
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Price.class, key);
            field.setAccessible(true);
            if (key.equals("procedureId") || key.equals("specialistId"))
                ReflectionUtils.setField(field, existingPrice.get(), Long.valueOf((Integer) value));
            else
                ReflectionUtils.setField(field, existingPrice.get(), value);
        });
        Price priceReturned = priceRepository.save(existingPrice.get());
        log.info("IN PriceServiceImpl - updateFields - FINISHED SUCCESSFULLY");
        return PriceMapper.toDTO(priceReturned);
    }

    @Override
    public void delete(Long id) {
        log.info("IN PriceServiceImpl - delete by id: {} - STARTED", id);
        if (!priceRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "Price not found with id: " + id);
        priceRepository.deleteById(id);
        log.info("IN PriceServiceImpl - delete by id: {} - FINISHED SUCCESSFULLY", id);
    }
}