package nl.hu.inno.hulp.domain.examquestion;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.answer.DichotomousAnswer;
import nl.hu.inno.hulp.domain.exceptions.IllegalQuestionException;

import java.util.List;

@Entity
@DiscriminatorValue("3")
public class OneOfMultipleQuestion extends Question {// student must select the one correct answer out of multiple options

    @Id
    @GeneratedValue()
    private Long id;
    private String questionText;
    private int points;
    @ElementCollection
    private List<DichotomousAnswer> answers;

    public OneOfMultipleQuestion(String questionText, int points, List<DichotomousAnswer> answers) {
        this.questionText = questionText;
        this.points = points;
        this.answers = answers;
        checkForCorrectAnswersAmount();

    }

    protected OneOfMultipleQuestion() {

    }

    public String getQuestionText() {
        return questionText;
    }

    public int getPoints() {
        return points;
    }

    public List<DichotomousAnswer> getAnswers() {
        return answers;
    }

    private void checkForCorrectAnswersAmount(){
        int correctAnswers = 0;
        for (DichotomousAnswer answer : answers) {
            if (answer.isCorrectAnswer()) {
                correctAnswers++;
            }
            if (correctAnswers >= 2) {
                throw new IllegalQuestionException("One of multiple question may only have a single correct answer.");
            }
        }
        if (correctAnswers == 0) {
            throw new IllegalQuestionException("Question must have at least one correct answer.");
        } else if (answers.size() < 2) {
            throw new IllegalQuestionException("Question must have at least two answers.");
        }
    }
}
