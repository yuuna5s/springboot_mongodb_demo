package com.example.springboot_mongodb_demo.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Transient
    public static final String SEQUENCE_NAME = "person_sequence";
    @Id
    private String personId;
    private String name;
    private int age;
    private String phone;
    private String email;
    private List<String> hobbies;
    private List<Address> addresses;
}
