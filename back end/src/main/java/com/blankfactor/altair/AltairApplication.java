package com.blankfactor.altair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AltairApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AltairApplication.class, args);
    }

}
