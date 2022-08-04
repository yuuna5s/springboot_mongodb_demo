package com.example.springboot_mongodb_demo.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "photo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photo {
    @Transient
    public static final String SEQUENCE_NAME = "photo_sequence";
    @Id
    private String photoId;
    private String photoName;
    private Binary photoFile;
}
