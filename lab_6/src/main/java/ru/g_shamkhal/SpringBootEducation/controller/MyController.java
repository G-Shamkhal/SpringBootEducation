package ru.g_shamkhal.SpringBootEducation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.g_shamkhal.SpringBootEducation.entity.Student;
import ru.g_shamkhal.SpringBootEducation.service.ApiResponse;
import ru.g_shamkhal.SpringBootEducation.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class MyController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> allStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ApiResponse getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return new ApiResponse(true, "Student found: " + student.toString());
        } else {
            return new ApiResponse(false, "Student with ID " + id + " not found.");
        }
    }

    @PostMapping
    public ApiResponse saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        if (savedStudent != null) {
            return new ApiResponse(true, "Student saved successfully: " + savedStudent.toString());
        } else {
            return new ApiResponse(false, "Failed to save student.");
        }
    }

    @PutMapping
    public ApiResponse updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.saveStudent(student);
        if (updatedStudent != null) {
            return new ApiResponse(true, "Student updated successfully: " + updatedStudent.toString());
        } else {
            return new ApiResponse(false, "Failed to update student.");
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            studentService.deleteStudent(id);
            return new ApiResponse(true, "Student with ID " + id + " deleted successfully.");
        } else {
            return new ApiResponse(false, "Student with ID " + id + " not found.");
        }
    }
}
