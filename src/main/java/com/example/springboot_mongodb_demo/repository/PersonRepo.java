package com.example.springboot_mongodb_demo.repository;

import com.example.springboot_mongodb_demo.collection.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends MongoRepository<Person, String> {
    List<Person> findByName(String name);

    List<Person> findByNameStartingWith(String name);

    void deleteByName(String name);

    List<Person> findByAgeBetween(Integer minAge, Integer maxAge);
}
