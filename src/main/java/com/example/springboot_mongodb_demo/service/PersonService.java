package com.example.springboot_mongodb_demo.service;

import com.example.springboot_mongodb_demo.collection.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    String save(Person person);
    Person findByName(String name);
    List<Person> findAll();

    List<Person> getAllStartWith(String name);

    void delete(String personId);

    void deleteByName(String name);

    List<Person> findByAge(int minAge, int maxAge);

    Page<Person> searchPerson(String name, Integer minAge, Integer maxAge, String city, String phone, String email, Pageable pageable);
}

