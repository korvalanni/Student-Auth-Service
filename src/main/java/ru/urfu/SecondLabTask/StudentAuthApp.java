package ru.urfu.SecondLabTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = { "ru.urfu.SecondLabTask.model" })
public class StudentAuthApp {

    public static void main(String[] args) {
        SpringApplication.run(StudentAuthApp.class, args);
    }

}
