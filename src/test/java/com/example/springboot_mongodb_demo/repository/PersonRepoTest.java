package com.example.springboot_mongodb_demo.repository;

import com.example.springboot_mongodb_demo.collection.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class PersonRepoTest {

    @Autowired
    private PersonRepo personRepo;

    @BeforeEach
    void setUp() {
        Person person = new Person();
        person.setPersonId("1");
        person.setName("John");
        person.setAge(30);
        person.setEmail("test@gmail.com");
        person.setPhone("1234567890");
        person.setHobbies(List.of("hobby1", "hobby2"));
        person.setAddresses("address");
        personRepo.save(person);
    }

    @AfterEach
    void tearDown() {
        personRepo.deleteAll();
    }

    @Test
    void findByName() {
        // when
        List<Person> foundPerson = personRepo.findByName("John");
        foundPerson.forEach(System.out::println);

        // then
        assertThat(foundPerson.get(0).getName()).isEqualTo("John");
    }

    @Test
    void findByNameStartingWith() {
        // when
        List<Person> foundPerson = personRepo.findByNameStartingWith("J");
        foundPerson.forEach(System.out::println);

        // then
        assertThat(foundPerson.get(0).getName()).isEqualTo("John");
    }

    @Test
    void deleteByName() {
        // when
        personRepo.deleteByName("John");
        List<Person> foundPerson = personRepo.findByName("John");

        // then
        assertThat(foundPerson).isEmpty();
    }

    @Test
    void findByAgeBetween() {
        // when
        List<Person> foundPerson = personRepo.findByAgeBetween(20, 40);
        foundPerson.forEach(System.out::println);

        // then
        assertThat(foundPerson.get(0).getName()).isEqualTo("John");
    }
}