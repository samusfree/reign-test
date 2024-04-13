package com.reign.sgonzales.betest;

import com.reign.sgonzales.betest.data.repository.impl.ResourceRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
@EnableAsync
@EnableMongoRepositories(repositoryBaseClass = ResourceRepositoryImpl.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
