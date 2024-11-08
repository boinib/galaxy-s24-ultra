package nl.hu.inno.hulp.domain.examquestion;

import nl.hu.inno.hulp.domain.answer.DichotomousAnswer;
import nl.hu.inno.hulp.domain.answer.OrderAnswer;
import nl.hu.inno.hulp.domain.exceptions.IllegalQuestionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoreOfMultipleQuestionTest {


    private DichotomousAnswer correctAnswer1;
    private DichotomousAnswer correctAnswer2;
    private DichotomousAnswer incorrectAnswer1;
    private DichotomousAnswer incorrectAnswer2;

    @BeforeEach
    void setUp() {
        incorrectAnswer1 = new DichotomousAnswer("Antw1", false);
        incorrectAnswer2 = new DichotomousAnswer("Antw2", false);
        correctAnswer1 = new DichotomousAnswer("Antw3", true);
        correctAnswer2 = new DichotomousAnswer("Antw4", true);
    }

    @Test
    void testShouldWorkNormally() {
        List<DichotomousAnswer> answers = Arrays.asList(correctAnswer1, correctAnswer2, incorrectAnswer1, incorrectAnswer2);
        assertDoesNotThrow(() -> new MoreOfMultipleQuestion("testvraag?", 10, answers,true));
    }

    @Test
    void MultipleCorrectAnswersShouldNotThrowException() {
        DichotomousAnswer correctAnswer2 = new DichotomousAnswer("Answer 2", true);
        List<DichotomousAnswer> answers = Arrays.asList(this.correctAnswer1, correctAnswer2, correctAnswer2, incorrectAnswer1);

        assertDoesNotThrow(() -> new MoreOfMultipleQuestion("testvraag?", 10, answers,true));
    }

    @Test
    void NoCorrectAnswersShouldThrowException() {
        List<DichotomousAnswer> answers = Arrays.asList(incorrectAnswer2, incorrectAnswer1);

        assertThrows(IllegalQuestionException.class, () -> {
            new MoreOfMultipleQuestion("testvraag?", 10, answers,true);
        });
    }

    @Test
    void testWithNoAnswersShouldThrowException() {
        List<DichotomousAnswer> noAnswers = Arrays.asList();
        assertThrows(IllegalQuestionException.class, () -> {
            new MoreOfMultipleQuestion("testvraag?", 10, noAnswers,true);
        } );
    }
}