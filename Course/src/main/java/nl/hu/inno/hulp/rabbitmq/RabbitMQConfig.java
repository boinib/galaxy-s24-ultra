package nl.hu.inno.hulp.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "course_registration_queue";
    public static final String STUDENT_QUEUE = "student_queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Queue studentQueue() {
        return new Queue(STUDENT_QUEUE, true);
    }
}
