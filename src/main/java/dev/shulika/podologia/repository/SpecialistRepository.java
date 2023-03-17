package dev.shulika.podologia.repository;

import dev.shulika.podologia.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Boolean existsByName(String name);
}
