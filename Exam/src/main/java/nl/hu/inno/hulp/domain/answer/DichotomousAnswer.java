package nl.hu.inno.hulp.domain.answer;

import jakarta.persistence.Embeddable;

@Embeddable
public class DichotomousAnswer extends Answer {
    private String answerText;
    private boolean correctAnswer = false;

    public DichotomousAnswer(String answerText, boolean correctAnswer) {
        this.answerText = answerText;
        this.correctAnswer = correctAnswer;
    }

    protected DichotomousAnswer() {

    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

}
