package application;


import nl.hu.inno.hulp.application.CourseService;
import nl.hu.inno.hulp.data.CourseRepository;
import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.CourseRegistration;
import nl.hu.inno.hulp.domain.value.DateRange;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.domain.value.Grade;
import nl.hu.inno.hulp.presentation.DTO.CourseDTO;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
//
public class CourseServiceIntegrationTest {

}
//
//    @Mock
//    private CourseRepository courseRepository;
//
//    @Mock
//    private StudentRepository studentRepository;
//
//    @Mock
//    private RabbitTemplate rabbitTemplate;
//
//    @InjectMocks
//    private CourseService courseService;
//
//    private Course course;
//    private Student student;
//
//    private CourseDTO courseDTO;
//    private StudentDTO studentDTO;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        DateRange dateRange = new DateRange(LocalDate.now(), LocalDate.now().plusDays(1));
//        course = new Course("Test Course", dateRange, new ArrayList<>(), 30, new Grade(5.5), 5, false);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        student = new Student(new Email("student@example.com"), "FirstName", "LastName", LocalDate.parse("24-02-2004", formatter), 20, false);
//        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
//        when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));
//        courseDTO = new CourseDTO(course);
//        studentDTO = new StudentDTO(student);
//    }
//
//    @Test
//    @DisplayName("Test register student")
//    public void testRegisterStudent() {
//        when(courseRepository.save(any(Course.class))).thenReturn(course);
//
//        Course registeredCourse = courseService.registerStudent(courseDTO, studentDTO);
//
//        assertEquals(1, registeredCourse.getRegistrations().size());
//        assertEquals(student.getEmail().getValue(), registeredCourse.getRegistrations().get(0).getStudentEmail());
//    }
//
//    @Test
//    @DisplayName("Test register student that is already registered")
//    public void testRegisterStudentAlreadyRegistered() {
//        course.addRegistration(new CourseRegistration(student.getEmail().getValue(), course.getName()));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            courseService.registerStudent(courseDTO, studentDTO);
//        });
//
//        assertEquals("Student is already registered for this course", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Test register student in a full course")
//    public void testRegisterStudentCourseFull() {
//        for (int i = 0; i < course.getMaximumStudents(); i++) {
//            course.addRegistration(new CourseRegistration("student" + i + "@example.com", course.getName()));
//        }
//
//        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            courseService.registerStudent(courseDTO, studentDTO);
//        });
//
//        assertEquals("Course is full", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Test register student with insufficient EC")
//    public void testRegisterStudentInsufficientEC() {
//        Student student = new Student(new Email("student@example.com"), "FirstName", "LastName", LocalDate.parse("24-02-2004", DateTimeFormatter.ofPattern("dd-MM-yyyy")), course.getMinimumEC() - 1, false);
//        when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));
//        StudentDTO studentDTO = new StudentDTO(student);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            courseService.registerStudent(courseDTO, studentDTO);
//        });
//
//        assertEquals("Student does not have enough EC", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Test register student with propedeuse requirement")
//    public void testRegisterStudentPropedeuseNotMet() {
//        Student student = new Student(new Email("student@example.com"), "FirstName", "LastName", LocalDate.parse("24-02-2004", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 20, false);
//        Course course = new Course("Test Course", new DateRange(LocalDate.now(), LocalDate.now().plusDays(1)), new ArrayList<>(), 30, new Grade(5.5), 5, true);
//        when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));
//        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
//        CourseDTO courseDTO = new CourseDTO(course);
//        StudentDTO studentDTO = new StudentDTO(student);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            courseService.registerStudent(courseDTO, studentDTO);
//        });
//
//        assertEquals("Student has not met the propedeuse requirement", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Test register student in an non-existing course")
//    public void testRegisterStudentNonExistentCourse() {
//        when(courseRepository.findById(courseDTO.getId())).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            courseService.registerStudent(courseDTO, studentDTO);
//        });
//
//        assertEquals("Course not found", exception.getMessage());
//    }
//
//}