package dev.shulika.podologia.repository;

import dev.shulika.podologia.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
}
