package nl.hu.inno.hulp.application;


import nl.hu.inno.hulp.data.CourseRepository;
import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.CourseRegistration;
import nl.hu.inno.hulp.domain.value.DateRange;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.domain.value.Grade;
import nl.hu.inno.hulp.exceptions.CourseNotFoundException;
import nl.hu.inno.hulp.exceptions.StudentNotFoundException;
import nl.hu.inno.hulp.presentation.DTO.CourseDTO;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import nl.hu.inno.hulp.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, RabbitTemplate rabbitTemplate) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(CourseDTO courseDTO) {
        return courseRepository.findById(courseDTO.getId())
                .orElse(null);
    }

    public Course createCourse(CourseDTO courseDTO) {
        Course course = new Course(
                courseDTO.getName(),
                new DateRange(courseDTO.getDateRange().getStartDate(), courseDTO.getDateRange().getEndDate()),
                new ArrayList<>(),
                courseDTO.getMaximumStudents(),
                new Grade(courseDTO.getCesuur().getValue()),
                courseDTO.getMinimumEC(),
                courseDTO.isPropedeuseRequired()
        );
        courseRepository.save(course);
        return course;
    }

    public Course registerStudent(CourseDTO courseDTO, StudentDTO studentDTO) throws CourseNotFoundException, StudentNotFoundException {
        Course course = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        Student student = studentRepository.findById(studentDTO.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found in the database"));

        CourseRegistration.validateRegistration(course, student);
        CourseRegistration registration = new CourseRegistration(student.getEmail().getValue(), course.getName());
        course.addRegistration(registration);

        String message = "Student " + student.getFirstName() + " " + student.getLastName() +
                " is registered for the course: " + course.getName();
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);

        return courseRepository.save(course);
    }



    public int getNumberOfRegistrations(CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return course.getRegistrations().size();
    }
    public static Student StudentDtoToStudent(StudentDTO studentDTO) {
        Email email = new Email(studentDTO.getEmail().getValue());
        return new Student(
                email,
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getBirthDate(),
                studentDTO.getEC(),
                studentDTO.isPropedeuseGehaald());
    }
}