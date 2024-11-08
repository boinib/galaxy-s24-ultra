package nl.hu.inno.hulp.domain.course;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.value.CourseRegistration;
import nl.hu.inno.hulp.domain.value.DateRange;
import nl.hu.inno.hulp.domain.value.Grade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private DateRange dateRange;
    @Embedded
    private Grade cesuur; //cesuur

    private int maximumStudents;
    private int minimumEC;
    private boolean propedeuseRequired;

    @ElementCollection
    private List<CourseRegistration> registrations = new ArrayList<>();

    protected Course() {

    }

    public Course(String name, DateRange dateRange,List<CourseRegistration> registrations,int maximumStudents, Grade cesuur, int minimumEC, boolean propedeuseRequired) {
        this.name = name;
        this.dateRange = new DateRange(dateRange.getStartDate(), dateRange.getEndDate());
        this.maximumStudents = maximumStudents;
        this.cesuur = cesuur;
        this.minimumEC = minimumEC;
        this.propedeuseRequired = propedeuseRequired;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public List<CourseRegistration> getRegistrations() {
        return registrations;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public Grade getCesuur() {
        return cesuur;
    }

    public int getMinimumEC() {
        return minimumEC;
    }

    public boolean isPropedeuseRequired() {
        return propedeuseRequired;
    }

    public void addRegistration(CourseRegistration registration) {
        this.registrations.add(registration);
    }

    public boolean canRegisterStudent(Grade studentGrade) {
        return studentGrade.getValue() >= cesuur.getValue();
    }

    protected boolean isCourseActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(dateRange.getStartDate()) && !today.isAfter(dateRange.getEndDate());
    }

}
