package as.si.myapplicationsh2demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("as.si")
public class MyApplicationsH2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplicationsH2DemoApplication.class, args);
    }

}
