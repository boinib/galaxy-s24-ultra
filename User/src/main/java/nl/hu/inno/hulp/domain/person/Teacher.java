package nl.hu.inno.hulp.domain.person;

import jakarta.persistence.*;
import nl.hu.inno.hulp.domain.value.Email;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")    private UUID teacherId;
    @Embedded
    private Email email;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Teacher(Email email, String firstName, String lastName, Date birthDate) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    protected Teacher() {

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

    public Date getBirthDate() {
        return birthDate;
    }
}
