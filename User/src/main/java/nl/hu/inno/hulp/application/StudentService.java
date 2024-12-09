package nl.hu.inno.hulp.application;

import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import nl.hu.inno.hulp.rabbitmq.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public StudentService(StudentRepository studentRepository, RabbitMQProducer rabbitMQProducer) {
        this.studentRepository = studentRepository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(StudentDTO studentDTO) {
        return studentRepository.findById(UUID.fromString(studentDTO.getStudentId()))
                .orElse(null);
    }

    public Student getStudentById(UUID studentId) {
        return studentRepository.findById(studentId)
                .orElse(null);
    }

    public Student addStudent(StudentDTO studentDTO) {
        // Check if student with the same email already exists
        if (studentRepository.existsByEmail(new Email(studentDTO.getEmail()))){
            throw new RuntimeException("Student with this email address already exists.");
        }

        // Create the student object
        Email email = new Email(studentDTO.getEmail());
        Student student = new Student(
                email,
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getBirthDate(),
                studentDTO.getEC(),
                studentDTO.isPropedeuseGehaald()
        );

        // Save the student to the database
        studentRepository.save(student);

        // Create the StudentDTO with the actual studentId generated
        StudentDTO updatedStudentDTO = new StudentDTO(
                student.getStudentId().toString(),  // studentId as string, no longer null
                student.getEmail(),
                student.getFirstName(),
                student.getLastName(),
                student.getBirthDate(),
                student.getEC(),
                student.isPropedeuseGehaald()
                );

        // Send the updated StudentDTO to RabbitMQ
        rabbitMQProducer.sendStudentToQueue(updatedStudentDTO);

        // Return the saved student
        return student;
    }

}
