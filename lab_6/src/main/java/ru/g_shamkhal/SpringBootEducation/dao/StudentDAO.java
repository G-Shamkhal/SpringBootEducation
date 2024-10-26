package ru.g_shamkhal.SpringBootEducation.dao;

import org.springframework.stereotype.Repository;
import ru.g_shamkhal.SpringBootEducation.entity.Student;

import java.util.List;

@Repository
public interface StudentDAO {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);
}