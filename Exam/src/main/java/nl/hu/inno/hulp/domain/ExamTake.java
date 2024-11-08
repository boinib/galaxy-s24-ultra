package nl.hu.inno.hulp.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class ExamTake {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;

    private String studentEmail;
    private String teacherEmail;
    private Long classRoom;
    @ElementCollection
    private List<ExamRegistration> registrations;

    public ExamTake() {}

    public ExamTake(Long studentId, Long teacherId, Long id, String adres) {}

    public ExamTake(Long id, Long studentId, Long teacherId, Long classroom) {
        this.id = id;
        this.studentEmail = studentId.toString();
        this.teacherEmail = teacherId.toString();
        this.classRoom = classroom;
    }

    public String getName(){return name;}

    public Date getStartDate(){return startDate;}

    public Date getEndDate(){return endDate;}

    public List<ExamRegistration> getRegistrations() {return registrations;}

    public void addRegistration(ExamRegistration registration) {
        this.registrations.add(registration);
    }
}
