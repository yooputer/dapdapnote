package org.dapdapnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DapdapnoteBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DapdapnoteBackendApplication.class, args);
    }

}
