package nl.hu.inno.hulp.domain.examquestion;

import nl.hu.inno.hulp.domain.answer.OrderAnswer;
import nl.hu.inno.hulp.domain.exceptions.IllegalQuestionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderQuestionTest {

    private OrderAnswer answer1;
    private OrderAnswer answer2;
    private OrderAnswer answer3;

    @BeforeEach
    void setUp() {
        answer1 = new OrderAnswer("antw1", 1);
        answer2 = new OrderAnswer("antw2", 2);
        answer3 = new OrderAnswer("antw3", 3);
    }

    @Test
    void testShouldWorkNormally() {
        List<OrderAnswer> answers = Arrays.asList(answer1, answer2, answer3);
        assertDoesNotThrow(() -> new OrderQuestion("testvraag", 10, answers));
    }

    @Test
    void multipleAnswersWithSamePositionShouldThrowException() {
        OrderAnswer duplicatePositionAnswer = new OrderAnswer("antw1ButAgain", 1);
        List<OrderAnswer> answers = Arrays.asList(answer1, duplicatePositionAnswer, answer2);

        assertThrows(IllegalQuestionException.class, () -> {
            new OrderQuestion("testvraag", 10, answers);
        } );
    }

    @Test
    void testWithNoAnswersShouldThrowException() {
        List<OrderAnswer> noAnswers = Arrays.asList();
        assertThrows(IllegalQuestionException.class, () -> {
            new OrderQuestion("testvraag", 10, noAnswers);
        } );
    }

}
