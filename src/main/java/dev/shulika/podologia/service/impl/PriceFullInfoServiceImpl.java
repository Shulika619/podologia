package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoRequestDTO;
import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.model.PriceFullInfo;
import dev.shulika.podologia.repository.PriceFullInfoRepository;
import dev.shulika.podologia.service.PriceFullInfoService;
import dev.shulika.podologia.util.PriceFullInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PriceFullInfoServiceImpl implements PriceFullInfoService {
    private final PriceFullInfoRepository priceFullInfoRepository;

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
        PriceFullInfo priceFullInfoReturned = priceFullInfoRepository.save(PriceFullInfoMapper.fromDTO(priceFullInfoRequestDTO));
        log.info("IN PriceFullInfoServiceImpl - created - FINISHED SUCCESSFULLY");
        return PriceFullInfoMapper.toDTO(priceFullInfoReturned);
    }

    @Override
    public PriceFullInfoResponseDTO update(Long id, PriceFullInfoRequestDTO priceFullInfoRequestDTO) {
        log.info("IN PriceFullInfoServiceImpl - update price by id: {} - STARTED", id);
        Optional<PriceFullInfo> existPrice = priceFullInfoRepository.findById(id);
        if (!existPrice.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Price not found with id: " + id);
        PriceFullInfo price = existPrice.get();
        price.setProcedure(priceFullInfoRequestDTO.getProcedure());
        price.setSpecialist(priceFullInfoRequestDTO.getSpecialist());
        price.setMinutes(priceFullInfoRequestDTO.getMinutes());
        price.setPrice(priceFullInfoRequestDTO.getPrice());
        price.setEnabled(priceFullInfoRequestDTO.getEnabled());
        PriceFullInfo priceFullInfoReturned = priceFullInfoRepository.save(price);
        log.info("IN PriceFullInfoServiceImpl - update price by id: {} - FINISHED SUCCESSFULLY", id);
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