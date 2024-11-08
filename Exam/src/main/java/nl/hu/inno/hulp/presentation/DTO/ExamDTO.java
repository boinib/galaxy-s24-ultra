package nl.hu.inno.hulp.presentation.DTO;

import nl.hu.inno.hulp.domain.TeacherConfirmation;
import nl.hu.inno.hulp.domain.value.DateRange;

import java.util.List;

public class ExamDTO {
    private Long id;
    private String name;
    private DateRange dateRange;
    private List<QuestionDTO> questionDTOS;
    private List<TeacherConfirmation> teacherConfirmations;
    private List<Long> registrationIds; // Assuming you want to transfer only registration IDs

    public ExamDTO() {
    }

    public ExamDTO(Long id, String name, DateRange dateRange, List<QuestionDTO> questions, List<TeacherConfirmation> teacherConfirmations, List<Long> registrationIds) {
        this.id = id;
        this.name = name;
        this.dateRange = dateRange;
        this.questionDTOS = questions;
        this.teacherConfirmations = teacherConfirmations;
        this.registrationIds = registrationIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<QuestionDTO> getQuestions() {
        return questionDTOS;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questionDTOS = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public List<Long> getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(List<Long> registrationIds) {
        this.registrationIds = registrationIds;
    }
}
