package nl.hu.inno.hulp.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.hulp.application.QuestionService;
import nl.hu.inno.hulp.presentation.dto.QuestionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionListener {
    private final QuestionService questionService;
    private final ObjectMapper objectMapper;

    @Autowired
    public QuestionListener(QuestionService questionService, ObjectMapper objectMapper) {
        this.questionService = questionService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUESTION_QUEUE)
    public void receiveMessage(String message) {
        try {
            QuestionDTO[] questionDTOArray = objectMapper.readValue(message, QuestionDTO[].class);
            List<QuestionDTO> questionDTOs = Arrays.asList(questionDTOArray);

            questionService.saveMultipleQuestions(questionDTOs);
            System.out.println("Question saved: "+message);

        } catch (JsonProcessingException e) {
            System.err.println("Couldn't read the question-string: " + e.getMessage());
        }
    }
}

