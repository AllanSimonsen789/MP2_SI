package as.nl.si.teachcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class TeachConApplication {

    public static void main(String[] args) {
        System.out.println("opdateret 1710");
        SpringApplication.run(TeachConApplication.class, args);
    }

}
