package nl.hu.inno.hulp.application;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.hulp.data.ExamRegistrationRepository;
import nl.hu.inno.hulp.data.ExamRepository;
import nl.hu.inno.hulp.data.QuestionRepository;
import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.Exam;
import nl.hu.inno.hulp.domain.ExamRegistration;
import nl.hu.inno.hulp.domain.examquestion.Question;
import nl.hu.inno.hulp.domain.exceptions.ExamNotFoundException;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.presentation.DTO.*;
import nl.hu.inno.hulp.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    private final ExamRegistrationRepository examRegistrationRepository;

    private final RestTemplate restTemplate;

    @Value("${questionmoduleurl}")
    private String questionModuleUrl;
    private final RabbitTemplate rabbitTemplate;
    private List<Exam> exams = new ArrayList<>();

    @Autowired
    public ExamService(ExamRepository examRepository, StudentRepository studentRepository, QuestionRepository questionRepository, ExamRegistrationRepository examRegistrationRepository, RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.questionRepository = questionRepository;
        this.examRegistrationRepository = examRegistrationRepository;
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Exam> getAllExams() {return examRepository.findAll();}

    public Exam getExam(Long id){
        Exam exam = examRepository.findById(id)
                .orElse(null);
        if (exam == null) {
            throw new ExamNotFoundException("Could not find exam with ID: "+id+".");
        }
        return exam;
    }
    public Exam confirmExamByTeacher(Long examID, TeacherDTO teacherDTO, boolean confirms) {
        Exam exam = getExam(examID);

        exam.addTeacherConfirmation(teacherDTO.getTeacherId(),confirms);

        return examRepository.save(exam);
    }

    public ExamDTO createExam(ExamDTO examDTO) throws JsonProcessingException {
        List<QuestionDTO> questionsToSave = examDTO.getQuestions();

        ObjectMapper objectMapper = new ObjectMapper();
        String questionsString = objectMapper.writeValueAsString(questionsToSave);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUESTION_QUEUE, questionsString);

        List<Question> questions = new ArrayList<>();
        for (QuestionDTO question : examDTO.getQuestions()) {
            Question questionToSave = DTOConversion.DTOtoQuestion(question);
            questions.add( questionToSave );
            questionRepository.save(questionToSave);
        }
        Exam exam = new Exam(examDTO.getName(),examDTO.getDateRange(),null,questions);
        Exam savedExam = examRepository.save(exam);

        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : savedExam.getQuestions()) {
            questionDTOs.add( DTOConversion.QuestionToDTO(question) );
        }
        return new ExamDTO(exam.getId(),exam.getName(),exam.getDateRange(),questionDTOs,exam.getTeacherConfirmations(),null);
    }

    public Exam registerStudent(ExamDTO examDTO, StudentDTO studentDTO) {
        Exam exam = examRepository.findById(examDTO.getId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Student student = StudentDtoToStudent(studentDTO);

        ExamRegistration.validateRegistration(exam, student);
        ExamRegistration registration = new ExamRegistration(student.getEmail().getValue(), exam.getName());
        exam.addRegistration(registration);

        return examRepository.save(exam);
    }

    public static Student StudentDtoToStudent(StudentDTO studentDTO) {
        Email email = new Email(studentDTO.getEmail().getValue());
        return new Student(
                email,
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getBirthDate(),
                studentDTO.getEC(),
                studentDTO.isPropedeuseGehaald());
    }

}
