package nl.hu.inno.hulp.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendStudentToQueue(StudentDTO studentDTO) {
        try {
            String message = objectMapper.writeValueAsString(studentDTO);
            rabbitTemplate.convertAndSend(RabbitMQConfig.STUDENT_QUEUE, message);
            System.out.println(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
