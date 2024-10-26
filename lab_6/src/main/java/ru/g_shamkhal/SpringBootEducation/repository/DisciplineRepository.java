package ru.g_shamkhal.SpringBootEducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.g_shamkhal.SpringBootEducation.entity.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
