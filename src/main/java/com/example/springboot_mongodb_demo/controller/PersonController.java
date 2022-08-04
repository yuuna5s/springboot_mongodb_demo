package com.example.springboot_mongodb_demo.controller;


import com.example.springboot_mongodb_demo.collection.Person;
import com.example.springboot_mongodb_demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/save")
    public String save(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/findAll")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/findByName")
    public Person findByName(@RequestParam("name") String name) {
        return personService.findByName(name);
    }

    @GetMapping("/findAllStartsWith")
    public List<Person> findPerson(@RequestParam("name") String name) {
        return personService.getAllStartWith(name);
    }

    @GetMapping("/findbyAgeBetween")
    public List<Person> findByAgeBetween(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        return personService.findByAge(minAge, maxAge);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String personId) {
        personService.delete(personId);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteByName(@PathVariable("name") String name) {
        personService.deleteByName(name);
    }

    @GetMapping("/search")
    public Page<Person> searchPerson(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return personService.searchPerson(name, minAge, maxAge, city, phone, email, pageable);
    }
}
