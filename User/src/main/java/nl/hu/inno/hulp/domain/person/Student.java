package nl.hu.inno.hulp.domain.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.value.Email;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Student {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID studentId = UUID.randomUUID();
    @Embedded
    private Email email;
    private String firstName;
    private String lastName;
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

    public Student(UUID studentId, Email email, String firstName, String lastName, LocalDate birthDate, int ec, boolean propedeuseGehaald) {
        this.studentId = studentId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.EC = ec;
        this.propedeuseGehaald = propedeuseGehaald;
    }

    public UUID getStudentId() {
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