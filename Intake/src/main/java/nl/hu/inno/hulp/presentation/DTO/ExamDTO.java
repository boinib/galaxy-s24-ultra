package nl.hu.inno.hulp.presentation.DTO;

import nl.hu.inno.hulp.domain.exam.Exam;
import nl.hu.inno.hulp.domain.value.DateRange;
import nl.hu.inno.hulp.domain.value.Grade;

public class ExamDTO {
    private Long examId;
    private String name;
    private DateRange dateRange;
    private Long examLocation;
    private int maxStudents;
    private Grade grade;

    public ExamDTO() {}

    public ExamDTO(Exam exam) {
        this.examId = exam.getExamId();
        this.name = exam.getName();
        this.examLocation = exam.getExamLocation();
        this.dateRange = exam.getDateRange();
        this.grade = grade;
    }

    public long getExamId() {return examId;}
    public Long getExamLocation() {return examLocation;}

}
