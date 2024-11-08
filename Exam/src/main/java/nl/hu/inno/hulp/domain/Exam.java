package nl.hu.inno.hulp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.examquestion.Question;
import nl.hu.inno.hulp.domain.value.DateRange;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Exam {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private DateRange dateRange;
    @ElementCollection
    private List<TeacherConfirmation> teacherConfirmations;
    @OneToMany
    private List<Question> questions;

    @ElementCollection
    private List<ExamRegistration> registrations = new ArrayList<>();
    @ManyToOne
    private Course course;

    protected Exam() {
        this.registrations = new ArrayList<>();
    }

    public Exam(String name, DateRange dateRange, List<ExamRegistration> registrations, List<Question> questions) {
        this.name = name;
        this.questions = questions;
        this.dateRange = new DateRange(dateRange.getStartDate(), dateRange.getEndDate());
        this.registrations = registrations != null ? registrations : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ExamRegistration> getRegistrations() {
        return registrations;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<TeacherConfirmation> getTeacherConfirmations() {
        return teacherConfirmations;
    }

    public void addRegistration(ExamRegistration registration) {
        this.registrations.add(registration);
    }
  
    public boolean addTeacherConfirmation(Long teachersID, boolean confirms) {
        for (TeacherConfirmation teacherConfirmation : teacherConfirmations) {
            if (Objects.equals(teacherConfirmation.getTeacherID(), teachersID)) {
                teacherConfirmation.setConfirmsExam(confirms);
                return true;
            }
        }
        TeacherConfirmation tc = new TeacherConfirmation(teachersID, confirms);
        teacherConfirmations.add(tc);
        return true;
  }

    public Course getCourse() {
        return course;
    }
}
