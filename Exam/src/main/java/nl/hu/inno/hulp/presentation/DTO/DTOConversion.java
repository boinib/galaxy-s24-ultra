package nl.hu.inno.hulp.presentation.DTO;

import nl.hu.inno.hulp.domain.examquestion.*;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;

import java.time.LocalDate;

public class DTOConversion {
    public static QuestionDTO QuestionToDTO(Question question) {

        QuestionDTO returnQuestion = null;
        if (question instanceof OrderQuestion orderQuestion) {
            returnQuestion = new QuestionDTO();
            returnQuestion.OrderQuestionDTO("order",
                    orderQuestion.getId(),
                    orderQuestion.getQuestionText(),
                    orderQuestion.getPoints(),
                    orderQuestion.getAnswers());
        } else if (question instanceof OpenQuestion openQuestion) {
            returnQuestion = new QuestionDTO();
            returnQuestion.OpenQuestionDTO("open",
                    openQuestion.getId(),
                    openQuestion.getQuestionText(),
                    openQuestion.getPoints(),
                    openQuestion.getExampleAnswer());
        } else if (question instanceof OneOfMultipleQuestion oneOfMultipleQuestion) {
            returnQuestion = new QuestionDTO();
            returnQuestion.OneOfMultipleQuestionDTO("one_of_multiple",
                    oneOfMultipleQuestion.getId(),
                    oneOfMultipleQuestion.getQuestionText(),
                    oneOfMultipleQuestion.getPoints(),
                    oneOfMultipleQuestion.getAnswers());
        } else if (question instanceof MoreOfMultipleQuestion moreOfMultipleQuestion) {
            returnQuestion = new QuestionDTO();
            returnQuestion.MoreOfMultipleQuestionDTO("more_of_multiple",
                    moreOfMultipleQuestion.getId(),
                    moreOfMultipleQuestion.getQuestionText(),
                    moreOfMultipleQuestion.getPoints(),
                    moreOfMultipleQuestion.getAnswers(),
                    moreOfMultipleQuestion.isPartialScoring());
        }
        return returnQuestion;
    }

    public static Question DTOtoQuestion(QuestionDTO dto){
        Question question = null;
        if (dto.questionType.equalsIgnoreCase("order")) {
            question = DTOtoOrderQuestion(dto);
        } else if (dto.questionType.equalsIgnoreCase("open")) {
            question = DTOtoOpenQuestion(dto);
        } else if (dto.questionType.equalsIgnoreCase("one_of_multiple")) {
            question = DTOtoOneOfMultipleQuestion(dto);
        } else if (dto.questionType.equalsIgnoreCase("more_of_multiple")) {
            question = DTOtoMoreOfMultipleQuestion(dto);
        }
        return question;
    }
    public static OrderQuestion DTOtoOrderQuestion(QuestionDTO dto){
        return new OrderQuestion(dto.questionText, dto.points, dto.OrderAnswers);
    }
    public static OpenQuestion DTOtoOpenQuestion(QuestionDTO dto){
        return new OpenQuestion(dto.questionText, dto.points, dto.exampleAnswer);
    }
    public static OneOfMultipleQuestion DTOtoOneOfMultipleQuestion(QuestionDTO dto){
        return new OneOfMultipleQuestion(dto.questionText, dto.points, dto.dichotomousAnswers);
    }
    public static MoreOfMultipleQuestion DTOtoMoreOfMultipleQuestion(QuestionDTO dto){
        return new MoreOfMultipleQuestion(dto.questionText, dto.points, dto.dichotomousAnswers, dto.partialScoring);
    }

}
