package com.gameplay.app.Repos;

import com.gameplay.app.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
