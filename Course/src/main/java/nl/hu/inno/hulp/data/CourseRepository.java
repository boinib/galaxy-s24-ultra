package nl.hu.inno.hulp.data;


import nl.hu.inno.hulp.domain.course.Course;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CourseRepository extends CassandraRepository<Course, UUID> {
}

