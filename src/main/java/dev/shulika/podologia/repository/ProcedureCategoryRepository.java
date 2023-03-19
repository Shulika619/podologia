package dev.shulika.podologia.repository;

import dev.shulika.podologia.model.ProcedureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureCategoryRepository extends JpaRepository<ProcedureCategory, Long> {
    Boolean existsByName(String name);
}
