package nl.hu.inno.hulp.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import nl.hu.inno.hulp.domain.person.Teacher;

import java.time.LocalDate;

public class TeacherDTO {
    private Long teacherID;
    private String email;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    public TeacherDTO(){}

    public TeacherDTO(Teacher teacher) {
        this.teacherID = teacher.getTeacherId();
        this.email = teacher.getEmail().getValue();
    }
    public Long getTeacherId() {
        return teacherID;
    }

    public String getEmail() {
        return email;
    }
}
