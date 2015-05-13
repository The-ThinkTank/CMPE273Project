package edu.sjsu.cmpe273.facebookarchiver.services;

import com.restfb.FacebookClient;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emy on 5/10/15.
 */
public interface UserPhotoService {

    UserPhotos create(FacebookClient facebookClient);
    ArrayList<UserPhotos> listAllPhotos(String Id);
    ArrayList<UserPhotos> getPhotos(String id);
     List<UserPhotos> getComments(String id);
}
