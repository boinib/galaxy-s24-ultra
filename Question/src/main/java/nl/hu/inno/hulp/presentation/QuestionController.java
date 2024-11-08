package nl.hu.inno.hulp.presentation;

import nl.hu.inno.hulp.application.QuestionService;
import nl.hu.inno.hulp.domain.examquestion.OrderQuestion;
import nl.hu.inno.hulp.domain.examquestion.Question;
import nl.hu.inno.hulp.domain.exceptions.IllegalQuestionException;
import nl.hu.inno.hulp.presentation.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO question) {
        try {
            return ResponseEntity.ok(questionService.saveQuestion(question));
        } catch (IllegalQuestionException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/saveMultiple")
    public ResponseEntity<?> createMultipleQuestions(@RequestBody List<QuestionDTO> questions) {
        try {
            return ResponseEntity.ok(questionService.saveMultipleQuestions(questions));
        } catch (IllegalQuestionException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
