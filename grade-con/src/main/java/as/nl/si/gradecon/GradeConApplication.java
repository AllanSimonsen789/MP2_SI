package as.nl.si.gradecon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GradeConApplication {

    public static void main(String[] args) {
        System.out.println("1707");
        SpringApplication.run(GradeConApplication.class, args);
    }

}
