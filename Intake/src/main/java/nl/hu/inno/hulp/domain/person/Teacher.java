package nl.hu.inno.hulp.domain.person;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import nl.hu.inno.hulp.domain.value.Email;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private Long teacherId;

    @Embedded
    private Email email;
    private String password;

    public Teacher (){}

    public Teacher(Email email, String password){
        this.email = email;
        this.password = password;
    }

    public Long getTeacherId(){return teacherId;}

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

