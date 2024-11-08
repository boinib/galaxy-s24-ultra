package nl.hu.inno.hulp.domain.person;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.value.Email;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    @Embedded
    private Email email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Teacher(Email email, String firstName, String lastName, LocalDate birthDate) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    protected Teacher() {

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
