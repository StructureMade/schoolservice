package de.structuremade.ms.schoolservice.database.repo;

import de.structuremade.ms.schoolservice.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
