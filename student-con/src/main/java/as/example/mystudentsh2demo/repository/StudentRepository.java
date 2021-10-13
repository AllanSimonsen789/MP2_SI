package as.example.mystudentsh2demo.repository;

import as.example.mystudentsh2demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
