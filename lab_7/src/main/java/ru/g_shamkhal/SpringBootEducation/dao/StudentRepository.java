package ru.g_shamkhal.SpringBootEducation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.g_shamkhal.SpringBootEducation.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}