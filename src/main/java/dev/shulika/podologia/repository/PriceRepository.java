package dev.shulika.podologia.repository;

import dev.shulika.podologia.model.Price;
import dev.shulika.podologia.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
//    Boolean existsByName(String name);
}
