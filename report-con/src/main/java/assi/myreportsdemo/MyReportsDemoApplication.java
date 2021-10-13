package assi.myreportsdemo;

import assi.myreportsdemo.Repository.ReportRepository;
import assi.myreportsdemo.model.Report;
import assi.myreportsdemo.service.SequenceGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyReportsDemoApplication {

    private static SequenceGeneratorService sequenceGenerator;
    private static ReportRepository repo;

    public static void main(String[] args) {

        SpringApplication.run(MyReportsDemoApplication.class, args);
    }

}
