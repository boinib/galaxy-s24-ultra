package nl.hu.inno.hulp.presentation;


import nl.hu.inno.hulp.application.StudentService;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping
    public Student getStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.getStudent(studentDTO);
    }

    @PostMapping
    public Student addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }
    @GetMapping("/{studentId}")
    public Student getStudentbyID(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

}
