package nl.hu.inno.hulp.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String COURSE_REGISTRATION_QUEUE = "course_registration_queue";
    public static final String STUDENT_QUEUE = "student_queue";
    public static final String STUDENT_REQUEST_QUEUE = "student_request_queue";

    @Bean
    public Queue courseRegistrationQueue() {
        return new Queue(COURSE_REGISTRATION_QUEUE, false);
    }

    @Bean
    public Queue studentQueue() {
        return new Queue(STUDENT_QUEUE, true);
    }

    @Bean
    public Queue studentRequestQueue() {
        return new Queue(STUDENT_REQUEST_QUEUE,true);
    }
}
