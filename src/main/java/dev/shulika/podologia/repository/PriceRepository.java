package dev.shulika.podologia.repository;

import dev.shulika.podologia.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
