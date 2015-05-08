package edu.sjsu.cmpe273.facebookarchiver.services;


import com.restfb.types.User;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserAccounts;

/**
 * Created by emy on 5/7/15.
 */
public interface UserAccountService {

   UserAccounts create(User me);
   UserAccounts getUser(String id);
}
