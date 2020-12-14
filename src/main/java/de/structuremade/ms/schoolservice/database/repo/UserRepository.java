package de.structuremade.ms.schoolservice.database.repo;

import de.structuremade.ms.schoolservice.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
