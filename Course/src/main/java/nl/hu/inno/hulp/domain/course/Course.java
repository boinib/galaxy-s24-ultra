package nl.hu.inno.hulp.domain.course;

import nl.hu.inno.hulp.domain.value.DateRange;
import nl.hu.inno.hulp.domain.value.Grade;
import nl.hu.inno.hulp.domain.value.CourseRegistration;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table("courses")
public class Course {
    @Id
    private UUID id = UUID.randomUUID();
    @Column("name")
    private String name;
    @Column("date_range")
    private DateRange dateRange;
    @Column("grade")
    private Grade cesuur;
    @Column("maximum_students")
    private int maximumStudents;
    @Column("minimum_ec")
    private int minimumEC;
    @Column("propedeuse_required")
    private boolean propedeuseRequired;
    @Column("registrations")
    private List<CourseRegistration> registrations = new ArrayList<>();

    public Course() {
        this.registrations = new ArrayList<>();
    }

    public Course(String name, DateRange dateRange,List<CourseRegistration> registrations, int maximumStudents, Grade grade, int minimumEC, boolean propedeuseRequired) {
        this.name = name;
        this.dateRange = dateRange;
        this.registrations = registrations != null ? registrations : new ArrayList<>();
        this.maximumStudents = maximumStudents;
        this.cesuur = grade;
        this.minimumEC = minimumEC;
        this.propedeuseRequired = propedeuseRequired;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public Grade getCesuur() {
        return cesuur;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public int getMinimumEC() {
        return minimumEC;
    }

    public boolean isPropedeuseRequired() {
        return propedeuseRequired;
    }

    public List<CourseRegistration> getRegistrations() {
        return registrations;
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
