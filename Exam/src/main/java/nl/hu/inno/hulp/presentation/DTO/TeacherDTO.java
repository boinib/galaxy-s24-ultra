package nl.hu.inno.hulp.presentation.DTO;

import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.person.Teacher;
import nl.hu.inno.hulp.domain.value.Email;

import java.time.LocalDate;

public class TeacherDTO {
    private Long teacherId;
    private Email email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public TeacherDTO() {}

    public TeacherDTO(Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.email = teacher.getEmail();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
        this.birthDate = teacher.getBirthDate();
    }

    public Long getTeacherId() {
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