package com.example.springboot_mongodb_demo.service.implementation;

import com.example.springboot_mongodb_demo.collection.DbSequence;
import com.example.springboot_mongodb_demo.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public String getSequenceNumber(String sequenceName) {
        Query query = new Query();
        query.addCriteria(new org.springframework.data.mongodb.core.query.Criteria("id").is(sequenceName));
        Update update = new Update().inc("seqNumber",1);
        DbSequence counter = mongoOperations.findAndModify(
                query,
                update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeqNumber() + "" : "1";
    }
}
