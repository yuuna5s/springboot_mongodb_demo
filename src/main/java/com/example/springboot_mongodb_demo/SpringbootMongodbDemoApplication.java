package com.example.springboot_mongodb_demo;

import com.example.springboot_mongodb_demo.collection.Person;
import com.example.springboot_mongodb_demo.repository.PersonRepo;
import com.example.springboot_mongodb_demo.service.implementation.PersonServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootMongodbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbDemoApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(PersonServiceImpl personService) {
//        return args -> {
//            personService.save(new Person(null,"John",25,"(+91) 9876543210","email1@gmail.com",null,null));
//            personService.save(new Person(null,"Kyle",32,"(+91) 9876543210","email2@gmail.com",null,null));
//            personService.save(new Person(null,"Mary",28,"(+91) 9876543210","email3@gmail.com",null,null));
//        };
//    }
}
