package org.playground.scpapi.media;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.media")
@Getter
@Setter
public class MediaConfig {
    private List<String> allowedTypes;
}
