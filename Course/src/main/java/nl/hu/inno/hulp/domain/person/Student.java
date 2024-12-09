package nl.hu.inno.hulp.domain.person;

import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import nl.hu.inno.hulp.domain.value.Email;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

@Table("students")
public class Student {
    @PrimaryKeyColumn(name = "student_id", type = PrimaryKeyType.PARTITIONED)
    private UUID studentId;
    @Column("email")
    private Email email;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("birth_date")
    private LocalDate birthDate;
    @Column("ec")
    private int EC;
    @Column("propedeuse_gehaald")
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

    // Getters and Setters
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

    public void setPropedeuseGehaald(boolean propedeuseGehaald) {
        this.propedeuseGehaald = propedeuseGehaald;
    }
}
