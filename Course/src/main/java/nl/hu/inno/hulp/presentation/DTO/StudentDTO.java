package nl.hu.inno.hulp.presentation.DTO;

import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;


import java.time.LocalDate;
import java.util.UUID;

public class StudentDTO {
    private UUID studentId;
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