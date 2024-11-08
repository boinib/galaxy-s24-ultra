package nl.hu.inno.hulp.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CourseRegistrationListener {

    @RabbitListener(queues = RabbitMQConfig.COURSE_REGISTRATION_QUEUE)
    public void receiveMessage(String message) {
        System.out.println("Received message from RabbitMQ: " + message);
    }
}
