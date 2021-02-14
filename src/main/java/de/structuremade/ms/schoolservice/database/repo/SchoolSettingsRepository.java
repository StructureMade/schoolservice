package de.structuremade.ms.schoolservice.database.repo;

import de.structuremade.ms.schoolservice.database.entity.School;
import de.structuremade.ms.schoolservice.database.entity.Schoolsettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolSettingsRepository extends JpaRepository<Schoolsettings, String> {
    Schoolsettings findBySchool(School school);
}
