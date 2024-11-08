package nl.hu.inno.hulp.application;

import nl.hu.inno.hulp.data.StudentRepository;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.value.Email;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(StudentDTO studentDTO) {
        return studentRepository.findById(studentDTO.getStudentId())
                .orElse(null);
    }

    public Student addStudent(StudentDTO studentDTO) {
        Email email = new Email(studentDTO.getEmail());
        Student student = new Student();
        student = new Student(
                email, student.getPassword().toString());
        studentRepository.save(student);
        return student;
    }

}
