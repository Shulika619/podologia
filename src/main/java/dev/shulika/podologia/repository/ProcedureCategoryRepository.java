package dev.shulika.podologia.repository;

import dev.shulika.podologia.model.ProcedureCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProcedureCategoryRepository extends JpaRepository<ProcedureCategory, Long> {
    Boolean existsByName(String name);
    @Query(
            value = "select p from ProcedureCategory p join fetch p.category",
            countQuery="SELECT count(p) from ProcedureCategory p"
    )
    Page<ProcedureCategory> findAllWithoutNPlusOne(Pageable pageable);
}
