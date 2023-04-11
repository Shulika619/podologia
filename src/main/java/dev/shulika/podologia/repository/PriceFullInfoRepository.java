package dev.shulika.podologia.repository;

import dev.shulika.podologia.model.PriceFullInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceFullInfoRepository extends JpaRepository<PriceFullInfo, Long> {
    @Query(
            value = "select p from PriceFullInfo p join fetch p.procedure join fetch p.specialist",
            countQuery="SELECT count(p) from PriceFullInfo p"
    )
    Page<PriceFullInfo> findAllWithoutNPlusOne(Pageable pageable);
}
