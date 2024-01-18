package ma.fstt.microservice3adminformules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("ma.fstt.microservice3adminformules.entity")
@EnableJpaRepositories("ma.fstt.microservice3adminformules.repository")
@ComponentScan("ma.fstt.microservice3adminformules.service")
@ComponentScan("ma.fstt.microservice3adminformules.controller")
@EnableDiscoveryClient
public class Microservice3AdminFormulesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Microservice3AdminFormulesApplication.class, args);
    }

}
