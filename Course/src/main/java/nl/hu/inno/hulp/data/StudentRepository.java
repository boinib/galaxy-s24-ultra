package nl.hu.inno.hulp.data;

import nl.hu.inno.hulp.domain.person.Student;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends CassandraRepository<Student, UUID> {
    Optional<Student> findByStudentId(UUID studentId);
}
