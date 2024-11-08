package nl.hu.inno.hulp.domain.examquestion;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.answer.DichotomousAnswer;
import nl.hu.inno.hulp.domain.exceptions.IllegalQuestionException;

import java.util.List;

@Entity
@DiscriminatorValue("4")
public class MoreOfMultipleQuestion extends Question {// student must select multiple correct answers out of multiple options

    @Id
    @GeneratedValue()
    private Long id;
    private String questionText;
    private int points;
    @ElementCollection
    private List<DichotomousAnswer> answers;
    private boolean partialScoring = true;// if false; must select all correct answers to score any points.

    public MoreOfMultipleQuestion(String questionText, int points, List<DichotomousAnswer> answers, boolean partialScoring) {
        this.questionText = questionText;
        this.points = points;
        this.answers = answers;
        this.partialScoring = partialScoring;
        checkForCorrectAnswersAmount();
    }

    protected MoreOfMultipleQuestion() {

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

    public boolean isPartialScoring() {
        return partialScoring;
    }

    private void checkForCorrectAnswersAmount(){
        int correctAnswers = 0;
        for (DichotomousAnswer answer : answers) {
            if (answer.isCorrectAnswer()) {
                correctAnswers++;
            }
        }

        if (correctAnswers == 0) {
            throw new IllegalQuestionException("Question must have at least one correct answer.");
        } else if (answers.size() < 2) {
            throw new IllegalQuestionException("Question must have at least two answers.");
        }
    }
}
