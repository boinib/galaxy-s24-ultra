package nl.hu.inno.hulp.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;

import java.time.LocalDate;

public class StudentDTO {
    private Long studentId;
    private Email email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int EC;
    private boolean propedeuseGehaald;

    public StudentDTO() {}

    public StudentDTO(Student student) {
        this.studentId = student.getStudentId();
        this.email = student.getEmail();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.birthDate = student.getBirthDate();
        this.EC = student.getEC();
        this.propedeuseGehaald = student.isPropedeuseGehaald();
    }

    public Long getStudentId() {
        return studentId;
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

    public int getEC() {
        return EC;
    }

    public boolean isPropedeuseGehaald() {
        return propedeuseGehaald;
    }
}