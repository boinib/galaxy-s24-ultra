package nl.hu.inno.hulp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    private String answerText;
    private boolean correctAnswer = false;

    public Answer(String answerText, boolean correctAnswer) {
        this.answerText = answerText;
        this.correctAnswer = correctAnswer;
    }

    protected Answer() {

    }

    public Long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

}
