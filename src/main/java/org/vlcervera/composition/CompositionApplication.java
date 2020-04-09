package org.vlcervera.composition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CompositionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompositionApplication.class, args);
    }

}
