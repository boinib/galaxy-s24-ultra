package nl.hu.inno.hulp.data;

import nl.hu.inno.hulp.domain.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
