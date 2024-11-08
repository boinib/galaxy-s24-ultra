package nl.hu.inno.hulp.data;

import nl.hu.inno.hulp.domain.Exam;
import nl.hu.inno.hulp.domain.ExamTake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
