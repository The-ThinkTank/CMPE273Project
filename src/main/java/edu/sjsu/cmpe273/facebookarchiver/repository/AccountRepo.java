package edu.sjsu.cmpe273.facebookarchiver.repository;

import edu.sjsu.cmpe273.facebookarchiver.entity.UserAccounts;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by emy on 5/7/15.
 */
public interface AccountRepo extends MongoRepository<UserAccounts, String> {

    public UserAccounts save(UserAccounts userAccounts);
    public UserAccounts findById(String Id);

}
