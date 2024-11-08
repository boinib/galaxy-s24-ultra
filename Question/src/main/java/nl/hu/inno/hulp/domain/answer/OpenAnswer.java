package nl.hu.inno.hulp.domain.answer;

import jakarta.persistence.Embeddable;

@Embeddable
public class OpenAnswer extends Answer {
    private String answerText;

    public OpenAnswer(String answerText) {
        this.answerText = answerText;
    }

    protected OpenAnswer() {

    }


    public String getAnswerText() {
        return answerText;
    }

}
