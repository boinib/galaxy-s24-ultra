package nl.hu.inno.hulp.application;

import nl.hu.inno.hulp.data.TeacherRepository;
import nl.hu.inno.hulp.domain.person.Student;
import nl.hu.inno.hulp.domain.person.Teacher;
import nl.hu.inno.hulp.presentation.DTO.StudentDTO;
import nl.hu.inno.hulp.presentation.DTO.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(TeacherDTO teacherDTO) {
        return teacherRepository.findById(teacherDTO.getTeacherId())
                .orElse(null);
    }
}
