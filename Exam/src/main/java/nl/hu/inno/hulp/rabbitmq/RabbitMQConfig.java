package nl.hu.inno.hulp.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUESTION_QUEUE = "question_queue";

    @Bean
    public Queue questionQueue() {
        return new Queue(QUESTION_QUEUE, true);
    }
}
