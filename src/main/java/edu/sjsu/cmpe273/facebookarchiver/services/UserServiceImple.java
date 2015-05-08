package edu.sjsu.cmpe273.facebookarchiver.services;

import com.restfb.types.User;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserAccounts;
import edu.sjsu.cmpe273.facebookarchiver.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by emy on 5/8/15.
 */
@Repository
public class UserServiceImple implements UserAccountService{

    @Autowired
    AccountRepo accountRepo;

    @Override
    public UserAccounts create(User me){
        UserAccounts userAccounts = new UserAccounts();
        userAccounts.setEmail(me.getEmail());
        userAccounts.setGender(me.getGender());
        userAccounts.setId(me.getId());
        userAccounts.setName(me.getName());
        accountRepo.save(userAccounts);
        return userAccounts;
    }
    @Override
    public UserAccounts getUser(String id){
        UserAccounts userAccounts = accountRepo.findById(id);
        return userAccounts;
    }
}
