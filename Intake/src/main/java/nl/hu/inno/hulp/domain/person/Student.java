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
    private String password;

    public Student() {
    }

    public Student(Email email, String password) {
        this.email = email;
        this.password = password;
    }
    public Long getStudentId () {
        return studentId;
    }
    public Email getEmail () {
        return email;
    }

    public String getPassword() {
        return password;
    }
}