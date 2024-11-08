package nl.hu.inno.hulp.domain;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.course.Course;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.DateRange;

import java.time.LocalDate;
import java.util.List;

@Embeddable
public final class ExamRegistration {

    private String examName;
    private String studentEmail;

    public ExamRegistration() {}

    public ExamRegistration(String studentEmail, String examName) {
        this.studentEmail = studentEmail;
        this.examName = examName;
    }

    public static void validateRegistration(Exam exam, Student student) {
        if (exam.getRegistrations().stream().anyMatch(reg -> reg.getStudentEmail().equals(student.getEmail().getValue()))) {
            throw new RuntimeException("Student is already registered for this exam");
        }

        LocalDate today = LocalDate.now();
        DateRange dateRange = exam.getDateRange();
        if (today.isBefore(dateRange.getStartDate()) || today.isAfter(dateRange.getEndDate())) {
            throw new RuntimeException("Registration is not within the allowed period");
        }


        int maxRegistrations = exam.getCourse().getMaximumStudents();
        if (exam.getRegistrations().size() >= maxRegistrations) {
            throw new RuntimeException("The exam has reached its maximum number of registrations");
        }
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getExamName() {
        return examName;
    }
}