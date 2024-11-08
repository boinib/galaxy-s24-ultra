package nl.hu.inno.hulp.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;

import java.time.LocalDate;

public class StudentDTO {
    private Long studentId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int EC;
    private boolean propedeuseGehaald;

    public StudentDTO() {}

    public StudentDTO(Student student) {
        this.studentId = student.getStudentId();
        this.email = student.getEmail().getValue();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.birthDate = student.getBirthDate();
        this.EC = student.getEC();
        this.propedeuseGehaald = student.isPropedeuseGehaald();
    }

    public StudentDTO(Long studentId, Email email, String firstName, String lastName, LocalDate birthDate, int ec, boolean propedeuseGehaald) {
        this.studentId = studentId;
        this.email = email.getValue();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.EC = ec;
        this.propedeuseGehaald = propedeuseGehaald;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getEmail() {
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