package nl.hu.inno.hulp.data;

import nl.hu.inno.hulp.domain.person.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
