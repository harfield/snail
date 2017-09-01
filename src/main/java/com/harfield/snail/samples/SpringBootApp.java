package com.harfield.snail.samples;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Created by harfield on 2017/8/29.
 */

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        System.out.println("in bean");
        final ApplicationContext ctx0= ctx;
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Let's inspect the beans provided by Spring Boot:");
                String[] beanNames = ctx0.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
            }
        };
    }

}
