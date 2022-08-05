package com.example.springboot_mongodb_demo.service.implementation;

import com.example.springboot_mongodb_demo.SpringbootMongodbDemoApplication;
import com.example.springboot_mongodb_demo.collection.Person;
import com.example.springboot_mongodb_demo.repository.PersonRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest{

    @Mock
    private PersonRepo personRepo;
    @InjectMocks
    @Autowired
    private PersonServiceImpl personService;
    private Person person;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl();
        person = new Person();
        person.setPersonId("1");
        person.setName("John");
        person.setAge(30);
        person.setEmail("test@gmail.com");
        person.setPhone("1234567890");
        person.setHobbies(List.of("hobby1", "hobby2"));
        person.setAddresses("address");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void save() {
        // when
        personService.save(person);
        // then
        verify(personRepo).save(person);
    }

    @Test
    @Disabled
    void findByName() {
        // when
        personService.findByName("John");
        // then
        verify(personRepo).findByName("John");
    }

    @Test
    void findAll() {
        // when
        personService.findAll();
        // then
        verify(personRepo).findAll();
    }

    @Test
    @Disabled
    void getAllStartWith() {
    }

    @Test
    @Disabled
    void delete() {
    }

    @Test
    @Disabled
    void deleteByName() {
    }

    @Test
    @Disabled
    void findByAge() {
    }

    @Test
    @Disabled
    void searchPerson() {
    }
}