package com.example.project_01;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project01Application {

    public static void main(String[] args) {
        SpringApplication.run(Project01Application.class, args);
    }

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }
    @Bean
    public Gson gson(){
        return new Gson();
    }

}
