package nl.hu.inno.hulp.domain.examSession;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.examLocation.ExamLocation;
import nl.hu.inno.hulp.domain.exam.Exam;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.DateRange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class ExamSession {
    @Id
    @GeneratedValue
    private long examSessionId;

    private String name;

    @Embedded
    private DateRange dateRange;
    @OneToMany
    private List<Student> students;
    @OneToOne
    private ExamLocation examLocation;
    @OneToOne
    private Exam Exam;
//    @OneToMany
//    private List<ExamAnswer> examAnswers;

    public ExamSession(){}

    public ExamSession(String name, DateRange dateRange, ExamLocation examLocation, Exam Exam) {
        this.name = name;
        this.dateRange = dateRange;
        this.examLocation = examLocation;
        this.Exam = Exam;
        this.students = new ArrayList<>();
        this.examSessionId = getExamSessionId();
    }

    public Long getExamSessionId() {return examSessionId;}

    public List<Student> getStudents() {return students;}

//    public List<ExamAnswer> getExamAnswers() {return examAnswers;}

    public Long getExamLocation(){return examLocation.getExamLocationID();}

    public List<ExamSession> getRegistrations() {
        for (Student student : students) {
            if (getRegistrations().contains(student.getStudentId())) {
                continue;
            } else {
                return getRegistrations();
            }
        }
        return List.of();
    }

    public DateRange getDateRange() {
        return dateRange;
    }
}
