package as.nl.si.examcon.repository;

import as.nl.si.examcon.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
