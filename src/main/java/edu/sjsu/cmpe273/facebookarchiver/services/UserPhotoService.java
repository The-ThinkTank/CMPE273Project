package edu.sjsu.cmpe273.facebookarchiver.services;

import com.restfb.FacebookClient;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;

import java.util.ArrayList;

/**
 * Created by emy on 5/10/15.
 */
public interface UserPhotoService {

    UserPhotos create(FacebookClient facebookClient);
    ArrayList<UserPhotos> listAllPhotos(String Id);
}
