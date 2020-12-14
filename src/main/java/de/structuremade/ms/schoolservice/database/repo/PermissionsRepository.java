package de.structuremade.ms.schoolservice.database.repo;

import de.structuremade.ms.schoolservice.database.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepository extends JpaRepository<Permissions, String> {
}
