package nl.hu.inno.hulp.domain.examquestion;

import nl.hu.inno.hulp.domain.answer.DichotomousAnswer;
import nl.hu.inno.hulp.domain.exceptions.IllegalQuestionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OneOfMultipleQuestionTest {

    private DichotomousAnswer correctAnswer;
    private DichotomousAnswer incorrectAnswer1;
    private DichotomousAnswer incorrectAnswer2;

    @BeforeEach
    void setUp() {
        incorrectAnswer1 = new DichotomousAnswer("Antw1", false);
        incorrectAnswer2 = new DichotomousAnswer("Antw2", false);
        correctAnswer = new DichotomousAnswer("Antw3", true);
    }

    @Test
    void OnlyOneCorrectAnswerShouldWorkNormally() {
        List<DichotomousAnswer> answers = Arrays.asList(correctAnswer, incorrectAnswer1, incorrectAnswer2);
        assertDoesNotThrow(() -> new OneOfMultipleQuestion("testvraag?", 10, answers));
    }

    @Test
    void MultipleCorrectAnswersShouldThrowException() {
        DichotomousAnswer correctAnswer2 = new DichotomousAnswer("Answer 2", true);
        List<DichotomousAnswer> answers = Arrays.asList(correctAnswer, correctAnswer2, incorrectAnswer1);

        assertThrows(IllegalQuestionException.class, () -> {
            new OneOfMultipleQuestion("testvraag?", 10, answers);
        });
    }

    @Test
    void NoCorrectAnswersShouldThrowException() {
        List<DichotomousAnswer> answers = Arrays.asList(incorrectAnswer2, incorrectAnswer1);

        assertThrows(IllegalQuestionException.class, () -> {
            new OneOfMultipleQuestion("testvraag?", 10, answers);
        });
    }

    @Test
    void testWithNoAnswersShouldThrowException() {
        List<DichotomousAnswer> noAnswers = Arrays.asList();
        assertThrows(IllegalQuestionException.class, () -> {
            new OneOfMultipleQuestion("testvraag?", 10, noAnswers);
        } );
    }

}
