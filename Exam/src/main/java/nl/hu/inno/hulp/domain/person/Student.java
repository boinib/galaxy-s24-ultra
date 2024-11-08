package nl.hu.inno.hulp.domain.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.value.Email;

import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Embedded
    private Email email;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private int EC;
    private boolean propedeuseGehaald;

    public Student() {
    }

    public Student(Email email, String firstName, String lastName, LocalDate birthDate, int EC, boolean propedeuseGehaald) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.EC = EC;
        this.propedeuseGehaald = propedeuseGehaald;
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