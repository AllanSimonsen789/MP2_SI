package assi.myreportsdemo;

import assi.myreportsdemo.Repository.ReportRepository;
import assi.myreportsdemo.model.Report;
import assi.myreportsdemo.service.SequenceGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyReportsDemoApplication {

    private static SequenceGeneratorService sequenceGenerator;
    private static ReportRepository repo;

    public static void main(String[] args) {
        System.out.println("opdateret 1705");
        SpringApplication.run(MyReportsDemoApplication.class, args);
    }

}
