package nl.hu.inno.hulp.domain.value;

import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.person.Student;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;


import java.util.Objects;

@UserDefinedType("course_registration")
public final class CourseRegistration {

    @CassandraType(type = CassandraType.Name.TEXT)
    private String studentEmail;
    @CassandraType(type = CassandraType.Name.TEXT)
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