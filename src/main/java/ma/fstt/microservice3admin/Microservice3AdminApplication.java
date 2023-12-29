package ma.fstt.microservice3admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ma.fstt.microservice3admin.controller")
@EnableDiscoveryClient
public class Microservice3AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Microservice3AdminApplication.class, args);
    }

}
