package nl.hu.inno.hulp.application;


import nl.hu.inno.hulp.data.TeacherRepository;
import nl.hu.inno.hulp.domain.person.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    private List<Teacher> teachers = new ArrayList<>();

    public TeacherService(List<Teacher> teachers) {this.teachers = teachers;}

    public List<Teacher> getAllTeachers() {return teachers;}

    public Teacher getTeacher (Long id){
        return teachers.stream().filter(teacher -> teacher.getTeacherId().equals(id)).findFirst().orElse(null);
    }

    public Teacher addTeacher (Teacher teacher){
        teachers.add(teacher);
        teacherRepository.save(teacher);
        return teacher;
    }

}
