package nl.hu.inno.hulp.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RabbitMQConsumer {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQConsumer(StudentRepository studentRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "student_request_queue")
    public String handleStudentRequest(String message) {
        try {
            Long studentId = objectMapper.readValue(message, Long.class);
            Student student = studentRepository.findById(studentId).orElse(null);

            if (student != null) {
                Email email = student.getEmail();
                StudentDTO studentDTO = new StudentDTO(
                        student.getStudentId(),
                        email,
                        student.getFirstName(),
                        student.getLastName(),
                        student.getBirthDate(),
                        student.getEC(),
                        student.isPropedeuseGehaald()
                );
                return objectMapper.writeValueAsString(studentDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
