package nl.hu.inno.hulp.application;

import nl.hu.inno.hulp.data.QuestionRepository;
import nl.hu.inno.hulp.domain.examquestion.Question;
import nl.hu.inno.hulp.presentation.dto.DTOConversion;
import nl.hu.inno.hulp.presentation.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public QuestionDTO getQuestionById(Long id) {
        return DTOConversion.QuestionToDTO(questionRepository.findById(id)
                .orElse(null));
    }

    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        Question question = DTOConversion.DTOtoQuestion(questionDTO);
        return DTOConversion.QuestionToDTO(questionRepository.save(question));
    }
    public List<QuestionDTO> saveMultipleQuestions(List<QuestionDTO> questionDTOs) {
        List<Question> savedQuestions = new ArrayList<>();
        List<QuestionDTO> savedQuestionDTOs = new ArrayList<>();
        for (QuestionDTO questionDTO : questionDTOs) {
            Question question = DTOConversion.DTOtoQuestion(questionDTO);
            Question savedQuestion = questionRepository.save(question);
            savedQuestions.add(savedQuestion);
            savedQuestionDTOs.add(DTOConversion.QuestionToDTO(savedQuestion));
        }
        return savedQuestionDTOs;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

}
