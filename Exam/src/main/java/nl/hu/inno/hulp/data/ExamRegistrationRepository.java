package nl.hu.inno.hulp.data;

import nl.hu.inno.hulp.domain.ExamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRegistrationRepository  extends JpaRepository <ExamRegistration, Long> {
}
