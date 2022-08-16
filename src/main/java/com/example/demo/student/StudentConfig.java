package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner
            (StudentRepository repository) {
        return args -> {
            Student rohesa = new Student(
                    "Rohesa",
                    "rohesa@gmail.com",
                    LocalDate.of(2005, MARCH,18)
            );

            Student rehan = new Student(
                    "Rehan",
                    "rehan@gmail.com",
                    LocalDate.of(2008, SEPTEMBER,9)
            );

            repository.saveAll(
                    List.of(rohesa,rehan)
            );
        };
    }
}
