package com.example.springboot_mongodb_demo.repository;

import com.example.springboot_mongodb_demo.collection.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends MongoRepository<Photo, String> {
}
