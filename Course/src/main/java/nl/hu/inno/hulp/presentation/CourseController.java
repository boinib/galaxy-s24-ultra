package nl.hu.inno.hulp.presentation;

import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.exceptions.CourseNotFoundException;
import nl.hu.inno.hulp.exceptions.StudentNotFoundException;
import nl.hu.inno.hulp.application.CourseService;
import nl.hu.inno.hulp.presentation.DTO.CourseDTO;
import nl.hu.inno.hulp.presentation.DTO.RegistrationDTO;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final StudentRepository studentRepository;
    @Autowired
    public CourseController(CourseService courseService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseDTO courseDTO) {
        try {
            return courseService.createCourse(courseDTO);
        } catch (CourseNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/register")
    public Course registerStudent(@RequestBody RegistrationDTO request) {
        Long studentId = request.getStudent().getStudentId();

        Student student = studentRepository.findById(studentId).orElse(null);

        assert student != null;
        StudentDTO studentDTO = new StudentDTO(student);

        try {
            return courseService.registerStudent(request.getCourse(), studentDTO);
        } catch (CourseNotFoundException | StudentNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @GetMapping("/registrations")
    public int getNumberOfRegistrations(@RequestBody CourseDTO courseDTO) {
        try {
            return courseService.getNumberOfRegistrations(courseDTO);
        } catch (CourseNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
