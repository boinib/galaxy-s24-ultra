package nl.hu.inno.hulp.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQConsumer(StudentRepository studentRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.STUDENT_QUEUE)
    public void receiveStudent(String message) {
        try {
            StudentDTO studentDTO = objectMapper.readValue(message, StudentDTO.class);

            Student student = new Student(
                    new Email(studentDTO.getEmail().getValue()),
                    studentDTO.getFirstName(),
                    studentDTO.getLastName(),
                    studentDTO.getBirthDate(),
                    studentDTO.getEC(),
                    studentDTO.isPropedeuseGehaald()
            );
            studentRepository.save(student);
            System.out.println("Student saved from queue: " + studentDTO.getFirstName() + " " + studentDTO.getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
