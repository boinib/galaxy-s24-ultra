package nl.hu.inno.hulp.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import nl.hu.inno.hulp.domain.person.Student;

import java.time.LocalDate;

public class StudentDTO {
    private Long studentId;
    private String email;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private int EC;
    private boolean propedeuseGehaald;

    public StudentDTO() {}

    public StudentDTO(Student student) {
        this.studentId = student.getStudentId();
        this.email = student.getEmail().getValue();
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
