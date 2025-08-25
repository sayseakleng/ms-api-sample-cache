package kh.com.foss.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApiSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiSampleApplication.class, args);
    }
}
