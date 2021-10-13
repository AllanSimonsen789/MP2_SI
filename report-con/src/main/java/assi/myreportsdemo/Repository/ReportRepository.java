package assi.myreportsdemo.Repository;

import assi.myreportsdemo.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, Long> {}