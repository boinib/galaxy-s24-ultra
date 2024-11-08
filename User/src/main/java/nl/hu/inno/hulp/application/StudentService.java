package nl.hu.inno.hulp.application;

import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import nl.hu.inno.hulp.rabbitmq.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return studentRepository.findById(studentDTO.getStudentId())
                .orElse(null);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElse(null);
    }

    public Student addStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(new Email(studentDTO.getEmail()))){
            throw new RuntimeException("Student with this emailadress already exists.");
        }

        Email email = new Email(studentDTO.getEmail());
        Student student = new Student(
                email,
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getBirthDate(),
                studentDTO.getEC(),
                studentDTO.isPropedeuseGehaald()
        );

        studentRepository.save(student);

        rabbitMQProducer.sendStudentToQueue(studentDTO);

        return student;
    }
}
