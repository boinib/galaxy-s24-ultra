package nl.hu.inno.hulp.domain.examquestion;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("2")
public class OpenQuestion extends Question {// student must place the answers in the correct order

    @Id
    @GeneratedValue()
    private Long id;
    private String questionText;
    private int points;
    private String exampleAnswer;

    public OpenQuestion(String questionText, int points, String exampleAnswer) {
        this.questionText = questionText;
        this.points = points;
        this.exampleAnswer = exampleAnswer;
    }

    protected OpenQuestion() {

    }

    public String getQuestionText() {
        return questionText;
    }

    public int getPoints() {
        return points;
    }

    public String getExampleAnswer() {
        return exampleAnswer;
    }
}
