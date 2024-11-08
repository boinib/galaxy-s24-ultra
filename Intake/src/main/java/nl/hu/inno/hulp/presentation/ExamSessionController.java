package nl.hu.inno.hulp.presentation;

import nl.hu.inno.hulp.application.ExamSessionService;
import nl.hu.inno.hulp.domain.exam.Exam;
import nl.hu.inno.hulp.domain.examSession.ExamSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ExamSession")
public class ExamSessionController {
    private final ExamSessionService examSessionService;

    @Autowired
    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }
    @PostMapping
    public ExamSession addExamSession(@RequestBody ExamSession examSession) {
        return examSession;
    }
    @PostMapping
    public ExamSession getExamSession(@RequestBody ExamSession examSession) { return examSession; }
    @PostMapping
    public ExamSession updateExamSession(@RequestBody ExamSession examSession) { return examSession; }
    @PostMapping
    public ExamSession deleteExamSession(@RequestBody ExamSession examSession) { return examSession; }

}