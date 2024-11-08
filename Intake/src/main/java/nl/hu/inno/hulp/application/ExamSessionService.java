package nl.hu.inno.hulp.application;

import nl.hu.inno.hulp.data.ExamRepository;
import nl.hu.inno.hulp.data.ExamSessionRepository;
import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.data.TeacherRepository;
import nl.hu.inno.hulp.domain.exam.Exam;
import nl.hu.inno.hulp.domain.examLocation.ExamLocation;
import nl.hu.inno.hulp.presentation.DTO.ExamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionService {
    private final ExamSessionRepository examSessionRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ExamSessionService(ExamRepository examRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, ExamLocation examLocation, ExamSessionRepository examSessionRepository, TeacherRepository teacherRepository1) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.examSessionRepository = examSessionRepository;
    }
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

//    public int getRegistrations(ExamDTO examDTO){
//        Exam exam = examRepository.findById(examDTO.getExamId())
//                .orElse(() -> new RuntimeException("Exam not found"));
//    }
}