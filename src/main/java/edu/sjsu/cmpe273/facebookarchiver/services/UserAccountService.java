package edu.sjsu.cmpe273.facebookarchiver.services;


import com.restfb.FacebookClient;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserAccounts;

/**
 * Created by emy on 5/7/15.
 */
public interface UserAccountService {

   UserAccounts create(FacebookClient facebookClient); //create Account for user
   UserAccounts getUser(String id);
}
