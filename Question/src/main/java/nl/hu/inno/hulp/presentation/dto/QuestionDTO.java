package nl.hu.inno.hulp.presentation.dto;

import nl.hu.inno.hulp.domain.answer.DichotomousAnswer;
import nl.hu.inno.hulp.domain.answer.OrderAnswer;

import java.util.List;

public class QuestionDTO {
    public String questionType;
    public Long id;
    public String questionText;
    public int points;
    public List<OrderAnswer> OrderAnswers;
    public List<DichotomousAnswer> dichotomousAnswers;
    public String exampleAnswer;
    public boolean partialScoring;

    public QuestionDTO() {
    }

    public void OrderQuestionDTO(String questionType, Long id, String questionText, int points, List<OrderAnswer> orderAnswers) {
        this.questionType = questionType;
        this.id = id;
        this.questionText = questionText;
        this.points = points;
        this.OrderAnswers = orderAnswers;
    }
    public void OpenQuestionDTO(String questionType, Long id, String questionText, int points, String exampleAnswer) {
        this.questionType = questionType;
        this.id = id;
        this.questionText = questionText;
        this.points = points;
        this.exampleAnswer = exampleAnswer;
    }
    public void OneOfMultipleQuestionDTO(String questionType, Long id, String questionText, int points, List<DichotomousAnswer> dichotomousAnswers) {
        this.questionType = questionType;
        this.id = id;
        this.questionText = questionText;
        this.points = points;
        this.dichotomousAnswers = dichotomousAnswers;
    }
    public void MoreOfMultipleQuestionDTO(String questionType, Long id, String questionText, int points, List<DichotomousAnswer> dichotomousAnswers, boolean partialScoring) {
        this.questionType = questionType;
        this.id = id;
        this.questionText = questionText;
        this.points = points;
        this.dichotomousAnswers = dichotomousAnswers;
        this.partialScoring = partialScoring;
    }
}
