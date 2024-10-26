package ru.g_shamkhal.SpringBootEducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.g_shamkhal.SpringBootEducation.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
