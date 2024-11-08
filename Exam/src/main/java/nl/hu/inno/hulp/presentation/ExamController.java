package nl.hu.inno.hulp.presentation;


import com.fasterxml.jackson.core.JsonProcessingException;
import nl.hu.inno.hulp.api.RestTemplateConfigTeacher;
import nl.hu.inno.hulp.application.ExamService;
import nl.hu.inno.hulp.api.RestTemplateConfig;
import nl.hu.inno.hulp.domain.Exam;
import nl.hu.inno.hulp.presentation.DTO.ExamDTO;
import nl.hu.inno.hulp.presentation.DTO.RegistrationDTO;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import nl.hu.inno.hulp.presentation.DTO.TeacherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExamService examService;
    private final RestTemplateConfig restTemplateConfig;
    private final RestTemplateConfigTeacher restTemplateConfigTeacher;
    @Value("${usermoduleurl}")
    private String userModuleUrl;
    @Value("${teachermoduleurl}")
    private String teacherModuleUrl;

    public ExamController(ExamService examService, RestTemplateConfig restTemplateConfig, RestTemplateConfigTeacher restTemplateConfigTeacher) {
        this.examService = examService;
        this.restTemplateConfig = restTemplateConfig;
        this.restTemplateConfigTeacher = restTemplateConfigTeacher;
    }

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @PostMapping
    public ExamDTO createExam(@RequestBody ExamDTO exam) throws JsonProcessingException {
        return examService.createExam(exam);
    }

    @PostMapping ("/register")
    public Exam registerStudent(@RequestBody RegistrationDTO request){
        Long studentId = request.getStudent().getStudentId();
        String serviceUserUrl = userModuleUrl + studentId;

        ResponseEntity<StudentDTO> response = restTemplateConfig.getForEntity(serviceUserUrl, StudentDTO.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            StudentDTO studentDTO = response.getBody();
            return examService.registerStudent(request.getExam(), studentDTO);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @PostMapping("/teacherDeconfirm/{examId}/{teacherId}")
    public Exam deconfirmExamByTeacher(@PathVariable Long examId, @PathVariable Long teacherId) {
        return examByTeacherConfirmation(examId, teacherId,false);
    }
    @PostMapping("/teacherConfirm/{examId}/{teacherId}")
    public Exam confirmExamByTeacher(@PathVariable Long examId, @PathVariable Long teacherId) {
        return examByTeacherConfirmation(examId, teacherId,true);
    }
    public Exam examByTeacherConfirmation(Long examId, Long teacherId, boolean confirms) {
        String serviceUserUrl = teacherModuleUrl + teacherId;

        ResponseEntity<TeacherDTO> response = restTemplateConfigTeacher.getForEntity(serviceUserUrl, TeacherDTO.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            TeacherDTO teacherDTO = response.getBody();
            return examService.confirmExamByTeacher(examId, teacherDTO, confirms);
        } else {
            throw new RuntimeException("Teacher not found");
        }
    }

//    @PostMapping("{examId}/teachers/{teacherID}")
//    public ExamIntake registerTeacher(@PathVariable Long examId, @PathVariable Long teacherID) {
//        return examService.registerTeacher(examId, teacherID);
//    }
}
