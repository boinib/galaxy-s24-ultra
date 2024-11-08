package nl.hu.inno.hulp.domain.exam;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.examSession.ExamSession;
import nl.hu.inno.hulp.domain.person.Teacher;
import nl.hu.inno.hulp.domain.value.DateRange;

@Entity
public class Exam extends ExamSession {
    @Id
    @GeneratedValue
    private Long examId;
    private String name;
    @OneToOne
    private Teacher teacher;
    @Embedded
    private DateRange dateRange;
//    @OneToMany
//    private List<ExamQuestion> questions;
    public Exam() {}

    public Long getExamId() {
        return examId;
    }
    public Long getTeacherId() {return teacher.getTeacherId();}
    public String getName() {
        return name;
    }
//    public List<ExamQuestion> getExamQuestions() {
//        return questions;
//    }
}
