package kh.com.foss.sample.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.settings")
@Configuration
public class AppPropertyConfiguration {
    private String dataTxId;
    private String dataTxName;
}
