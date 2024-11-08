package nl.hu.inno.hulp.domain.value;

import jakarta.persistence.Embeddable;
import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.person.Student;


import java.util.Objects;

@Embeddable
public final class CourseRegistration {

    private String studentEmail;
    private String courseName;

    public CourseRegistration(String studentEmail, String courseName) {
        this.studentEmail = studentEmail;
        this.courseName = courseName;
    }

    public static void validateRegistration(Course course, Student student) {
        if (course.getRegistrations().stream().anyMatch(reg -> reg.getStudentEmail().equals(student.getEmail().getValue()))) {
            throw new RuntimeException("Student is already registered for this course");
        }

        if (course.getRegistrations().size() >= course.getMaximumStudents()) {
            throw new RuntimeException("Course is full");
        }

        if (student.getEC() < course.getMinimumEC()) {
            throw new RuntimeException("Student does not have enough EC");
        }

        if (!student.isPropedeuseGehaald() == course.isPropedeuseRequired()) {
            throw new RuntimeException("Student has not met the propedeuse requirement");
        }
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRegistration that = (CourseRegistration) o;
        return Objects.equals(studentEmail, that.studentEmail) && Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentEmail, courseName);
    }

    protected CourseRegistration(){
    }
}