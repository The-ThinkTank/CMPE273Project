package edu.sjsu.cmpe273.facebookarchiver.repository;

import edu.sjsu.cmpe273.facebookarchiver.entity.UserVideos;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by emy on 5/7/15.
 */
public interface VideoRepo extends MongoRepository<UserVideos, String> {
}
