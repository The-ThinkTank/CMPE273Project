package edu.sjsu.cmpe273.facebookarchiver.services;

import com.restfb.FacebookClient;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emy on 5/10/15.
 */
public interface UserPhotoService {

     UserPhotos create(FacebookClient facebookClient); //creates the photo in the list
     ArrayList<UserPhotos> listAllPhotos(String Id); //list all photos.
     List<UserPhotos> getTopPhotoByLikes(String id);
     List<UserPhotos> getTopPhotoByComments(String id);
     List<UserPhotos> getPicsYear(String id, int year) throws ParseException;
     String deletePhoto(String Id, String photoId); //delete a photo based on the userId
     UserPhotos findPhoto(String Id, String PhotoId);

}
