package edu.sjsu.cmpe273.facebookarchiver.services;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Photo;
import com.restfb.types.User;
import edu.sjsu.cmpe273.facebookarchiver.entity.Comments;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;
import edu.sjsu.cmpe273.facebookarchiver.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Created by emy on 5/10/15.
 */
@Repository
public class UserPhotoServiceImpl implements UserPhotoService {

    @Autowired
    PhotoRepo photoRepo;

    private String topic="CMPE273-Topic";

    @Override
    public UserPhotos create(FacebookClient facebookClient){
        UserPhotos userPhotos = new UserPhotos();
        User me = facebookClient.fetchObject("me", User.class);

        int countOfLikes;//tracks number of likes.

        Connection<Photo> connection = facebookClient.fetchConnection("me/photos", Photo.class,
               Parameter.with("fields", "id,created_time,source,comments,likes"),Parameter.with("limit",200));
          userPhotos.setUserId(me.getId());
        for(Photo photos: connection.getData()) {
                List<String> tempLikes = new ArrayList<String>();
                List<Comments> commentList = new ArrayList<Comments>();
                userPhotos.setPhotoId(photos.getId());
                userPhotos.setCreatedAt(String.valueOf(photos.getCreatedTime()));
                userPhotos.setSource(photos.getSource());
                List<NamedFacebookType> likes = photos.getLikes();
                countOfLikes = 0;  //making sure likes dont keep increasing for each photo
                for (NamedFacebookType like : likes) {
                    String name = like.getName();
                    tempLikes.add(name);
                    ++countOfLikes;
                }
                userPhotos.setLikes(tempLikes);
                userPhotos.setNumberOfLikes(countOfLikes); //stores number of likes.

              //comments code
                List<Comment> comment = photos.getComments();
            int j=0;
                for (Comment notes : comment) {
                    Comments getcomments = new Comments();
                    getcomments.setId(notes.getId());
                    getcomments.setName(notes.getFrom().getName());
                    getcomments.setMessage(notes.getMessage());
                    getcomments.setLike_count(notes.getLikeCount());
                    userPhotos.setNumberOfComments(comment.size());
                    commentList.add(getcomments);
                    j++;
                }

                userPhotos.setComments(commentList);
                for(int i=0; i<userPhotos.getComments().size();i++) {
                    System.out.println(userPhotos.getComments().get(i).getMessage());
                }
                photoRepo.save(userPhotos);
            }

        return userPhotos;
    }

    public ArrayList<UserPhotos> listAllPhotos(String Id){
        ArrayList<UserPhotos> list = photoRepo.findByUserId(Id);
        return list;
    }

    @Override
    public ArrayList<UserPhotos> getPhotos(String id){
        ArrayList<UserPhotos> userPhotosArrayList = photoRepo.findByUserId(id);

        Collections.sort(userPhotosArrayList, UserPhotos.UserPhotosComparator);
        ArrayList<UserPhotos> userPhotoses = new ArrayList<UserPhotos>();
        int i=0;
        for(UserPhotos photos: userPhotosArrayList){
            userPhotoses.add(userPhotosArrayList.get(i));
            i++;
            if(i==5)
            {
                break;
            }
        }
        return userPhotoses;

    }

    @Override
    public List<UserPhotos> getComments(String id){
        ArrayList<UserPhotos> userPhotosArrayList = photoRepo.findByUserId(id);

        List<UserPhotos> userPhotosList = new ArrayList<UserPhotos>();

        Collections.sort(userPhotosArrayList, UserPhotos.userCommentsComparator);

        int i=0;
        for(UserPhotos userPhotos: userPhotosArrayList){
            userPhotosList.add(userPhotosArrayList.get(i));
            i++;
            if(i==5){
                break;
            }
        }
        return userPhotosList;
    }
}
