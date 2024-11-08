//package nl.hu.inno.hulp.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class QuestionListener {
//    @RabbitListener(queues = RabbitMQConfig.QUESTION_QUEUE)
//    public void receiveMessage(String message) {
//        System.out.println("Received message from RabbitMQ: " + message);
//    }
//}
