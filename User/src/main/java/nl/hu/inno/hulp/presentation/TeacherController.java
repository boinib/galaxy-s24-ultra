package nl.hu.inno.hulp.presentation;


import nl.hu.inno.hulp.application.TeacherService;
import nl.hu.inno.hulp.domain.person.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getTeachers() {return teacherService.getAllTeachers();}

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Long id) {return teacherService.getTeacher(id);}

    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {return teacherService.addTeacher(teacher);}
}
