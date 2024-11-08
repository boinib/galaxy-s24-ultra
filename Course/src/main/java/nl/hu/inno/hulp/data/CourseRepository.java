package nl.hu.inno.hulp.data;


import nl.hu.inno.hulp.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
