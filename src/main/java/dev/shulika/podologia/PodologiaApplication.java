package dev.shulika.podologia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PodologiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PodologiaApplication.class, args);
    }

}
