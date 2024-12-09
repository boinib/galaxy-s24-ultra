package nl.hu.inno.hulp.presentation;

import jakarta.validation.Valid;
import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.exceptions.CourseNotFoundException;
import nl.hu.inno.hulp.domain.exceptions.StudentNotFoundException;
import nl.hu.inno.hulp.application.CourseService;
import nl.hu.inno.hulp.presentation.DTO.CourseDTO;
import nl.hu.inno.hulp.presentation.DTO.RegistrationDTO;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
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
    public ResponseEntity<Course> createCourse(@RequestBody @Valid CourseDTO courseDTO) {
        try {
            Course createdCourse = courseService.createCourse(courseDTO);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (CourseNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Course> registerStudent(@RequestBody @Valid RegistrationDTO request) {

        try {
            Student student = studentRepository.findByStudentId(request.getStudent().getStudentId())
                    .orElseThrow(() -> new StudentNotFoundException("Student not found"));
            Course course = courseService.registerStudent(request.getCourse(), new StudentDTO(student));
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (CourseNotFoundException | StudentNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/registrations")
    public ResponseEntity<Integer> getNumberOfRegistrations(@RequestBody @Valid CourseDTO courseDTO) {
        try {
            int registrations = courseService.getNumberOfRegistrations(courseDTO);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } catch (CourseNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
