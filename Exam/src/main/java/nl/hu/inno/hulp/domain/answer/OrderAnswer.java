package nl.hu.inno.hulp.domain.answer;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderAnswer extends Answer {
    private String answerText;
    private int arrangementPosition;

    public OrderAnswer(String answerText, int arrangementPosition) {
        this.answerText = answerText;
        this.arrangementPosition = arrangementPosition;
    }

    protected OrderAnswer() {

    }


    public String getAnswerText() {
        return answerText;
    }

    public int getArrangementPosition() {
        return arrangementPosition;
    }

}
