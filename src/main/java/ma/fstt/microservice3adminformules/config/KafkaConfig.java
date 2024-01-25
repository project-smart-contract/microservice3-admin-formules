package ma.fstt.microservice3adminformules.config;

import ma.fstt.microservice3adminformules.constant.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {



    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(AppConstant.FORMULE_INFO)
                .build();
    }
    @Bean
    public NewTopic optionTopic() {
        return TopicBuilder
                .name(AppConstant.OPTION_INFO)
                .build();
    }
}