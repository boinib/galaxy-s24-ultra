package nl.hu.inno.hulp.domain.examquestion;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.answer.OrderAnswer;
import nl.hu.inno.hulp.domain.exceptions.IllegalQuestionException;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class OrderQuestion extends Question {// student must place the answers in the correct order

    @Id
    @GeneratedValue()
    private Long id;
    private String questionText;
    private int points;
    @ElementCollection
    private List<OrderAnswer> answers = new ArrayList<>();

    public OrderQuestion(String questionText, int points, List<OrderAnswer> answers) {
        this.questionText = questionText;
        this.points = points;
        this.answers = answers;
        checkIfOrderMakesSense();
    }

    protected OrderQuestion() {

    }

    public int getPoints() {
        return points;
    }

    public List<OrderAnswer> getAnswers() {
        return answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    private void checkIfOrderMakesSense() {
        List<Integer> positions = new ArrayList<>();
        for (OrderAnswer answer : answers) {
            int answerPosition = answer.getArrangementPosition();
            if ( positions.contains(answerPosition) ) {
                throw new IllegalQuestionException("No two answers should share the same position in the order.");
            }
            positions.add(answerPosition);
        }
        if (answers.size() < 2) {
            throw new IllegalQuestionException("Question must have at least two answers.");
        }
    }
}
