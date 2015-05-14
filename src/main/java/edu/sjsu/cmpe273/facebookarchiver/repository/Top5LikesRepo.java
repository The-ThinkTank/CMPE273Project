package edu.sjsu.cmpe273.facebookarchiver.repository;

import edu.sjsu.cmpe273.facebookarchiver.results.Top5ByLikes;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Rajat on 5/13/2015.
 */
public interface Top5LikesRepo extends MongoRepository<Top5ByLikes, String>{

}
