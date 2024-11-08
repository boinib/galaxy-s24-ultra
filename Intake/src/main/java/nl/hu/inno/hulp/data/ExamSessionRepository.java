package nl.hu.inno.hulp.data;

import nl.hu.inno.hulp.domain.examSession.ExamSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

}
