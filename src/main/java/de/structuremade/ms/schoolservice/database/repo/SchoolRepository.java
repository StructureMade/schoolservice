package de.structuremade.ms.schoolservice.database.repo;

import de.structuremade.ms.schoolservice.database.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, String> {
    School findByEmail(String email);
}
