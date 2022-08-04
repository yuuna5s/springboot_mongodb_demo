package com.example.springboot_mongodb_demo.service.implementation;

import com.example.springboot_mongodb_demo.collection.Photo;
import com.example.springboot_mongodb_demo.repository.PhotoRepo;
import com.example.springboot_mongodb_demo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoRepo photoRepo;
    @Autowired
    private SequenceGeneratorServiceImpl sequenceGeneratorService;
    @Override
    public String savePhoto(String originalFilename, MultipartFile image) throws IOException {
        Photo photo = new Photo();
        photo.setPhotoId(sequenceGeneratorService.getSequenceNumber(Photo.SEQUENCE_NAME));
        photo.setPhotoName(originalFilename);
        photo.setPhotoFile(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        log.info("Photo saved with id: {}", photo.getPhotoId());
        return photoRepo.save(photo).getPhotoId();
    }

    @Override
    public Photo getPhoto(String id) {
        log.info("Downloading photo with id: {}", id);
        Optional<Photo> photoValue = photoRepo.findById(id);
        if (photoValue.isPresent()) {
            return photoValue.get();
        } else {
            log.error("Photo with id: {} not found", id);
            return null;
        }
    }
}
