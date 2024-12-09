package nl.hu.inno.hulp.presentation.DTO;


import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.value.DateRange;
import nl.hu.inno.hulp.domain.value.Grade;
import java.util.UUID;

public class CourseDTO {
    private UUID id;
    private String name;
    private DateRange dateRange;
    private int maximumStudents;
    private Grade cesuur;
    private int minimumEC;
    private boolean propedeuseRequired;

    public CourseDTO() {
    }

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.dateRange = course.getDateRange();
        this.maximumStudents = course.getMaximumStudents();
        this.cesuur = course.getCesuur();
        this.minimumEC = course.getMinimumEC();
        this.propedeuseRequired = course.isPropedeuseRequired();
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
}