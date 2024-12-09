package nl.hu.inno.hulp.presentation.DTO;

import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;

import java.time.LocalDate;
import java.util.UUID;

public class TeacherDTO {
    private UUID teacherId;
    private Email email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public TeacherDTO() {}

    public TeacherDTO(Student student) {
        this.teacherId = student.getStudentId();
        this.email = student.getEmail();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.birthDate = student.getBirthDate();
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public Email getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}