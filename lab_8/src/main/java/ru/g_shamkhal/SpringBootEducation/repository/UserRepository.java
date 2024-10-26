package ru.g_shamkhal.SpringBootEducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.g_shamkhal.SpringBootEducation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}