package ru.g_shamkhal.SpringBootEducation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.g_shamkhal.SpringBootEducation.entity.Discipline;
import ru.g_shamkhal.SpringBootEducation.service.ApiResponse;
import ru.g_shamkhal.SpringBootEducation.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public List<Discipline> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ApiResponse getDiscipline(@PathVariable int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline != null) {
            return new ApiResponse(true, "Discipline found: " + discipline.toString());
        } else {
            return new ApiResponse(false, "Discipline with ID " + id + " not found.");
        }
    }

    @PostMapping
    public ApiResponse addDiscipline(@RequestBody Discipline discipline) {
        Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
        if (savedDiscipline != null) {
            return new ApiResponse(true, "Discipline saved successfully: " + savedDiscipline.toString());
        } else {
            return new ApiResponse(false, "Failed to save discipline.");
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteDiscipline(@PathVariable int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline != null) {
            disciplineService.deleteDiscipline(id);
            return new ApiResponse(true, "Discipline with ID " + id + " deleted successfully.");
        } else {
            return new ApiResponse(false, "Discipline with ID " + id + " not found.");
        }
    }
}
