package dev.shulika.podologia;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PodologiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PodologiaApplication.class, args);
    }
    @Bean    // Выносим в main классе
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
