package com.example.springboot_mongodb_demo.service.implementation;

import com.example.springboot_mongodb_demo.collection.Person;
import com.example.springboot_mongodb_demo.repository.PersonRepo;
import com.example.springboot_mongodb_demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    @Override
    public String save(Person person) {
        person.setPersonId(sequenceGeneratorService.getSequenceNumber(Person.SEQUENCE_NAME));
        log.info("Person saved with id: {}", person.getPersonId());
        return personRepo.save(person).getPersonId();
    }

    @Override
    public Person findByName(String name) {
        log.info("findByName: {}", name);
        return (Person) personRepo.findByName(name);
    }

    @Override
    public List<Person> findAll() {
        log.info("findAll");
        return personRepo.findAll();
    }

    @Override
    public List<Person> getAllStartWith(String name) {
        log.info("getAllStartWith: {}", name);
        return personRepo.findByNameStartingWith(name);
    }

    @Override
    public void delete(String personId) {
        log.info("delete: {}", personId);
        personRepo.deleteById(personId);
    }

    @Override
    public void deleteByName(String name) {
        log.info("deleteByName: {}", name);
        personRepo.deleteByName(name);
    }

    @Override
    public List<Person> findByAge(int minAge, int maxAge) {
        return personRepo.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Page<Person> searchPerson(String name, Integer minAge, Integer maxAge, String city, String phone, String email, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteriaList = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            criteriaList.add(Criteria.where("name").regex(name,"i"));
        }
        if (minAge != null && maxAge != null) {
            criteriaList.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }
        if (city != null && !city.isEmpty()) {
            criteriaList.add(Criteria.where("city").regex(city,"i"));
        }
        if (phone != null && !phone.isEmpty()) {
            criteriaList.add(Criteria.where("phone").regex(phone,"i"));
        }
        if (email != null && !email.isEmpty()) {
            criteriaList.add(Criteria.where("email").regex(email,"i"));
        }
        if (!criteriaList.isEmpty())
        {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        }
        Page<Person> people = PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Person.class),
                pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Person.class));
        log.info("searchPerson: {}", people);
        return people;
    }
}
