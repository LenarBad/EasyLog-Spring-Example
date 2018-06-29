package io.lenar.examples.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Universe createWorld() {
        return new Universe();
    }
}
