package edu.sjsu.cmpe273.facebookarchiver.repository;

import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

/**
 * Created by emy on 5/7/15.
 */
public interface PhotoRepo extends MongoRepository<UserPhotos, String> {
   // UserPhotos save(UserPhotos userPhotos);
   UserPhotos findById(String Id);
   ArrayList<UserPhotos> findByUserId(String Id);
}
