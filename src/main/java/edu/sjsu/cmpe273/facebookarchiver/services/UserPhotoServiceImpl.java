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
        Comments getcomments = new Comments();
        User me = facebookClient.fetchObject("me", User.class);
        List<String> tempLikes = new ArrayList<String>();
        List<Comments> commentList = new ArrayList<Comments>();

        int countOfLikes =0;//tracks number of likes.

        Connection<Photo> connection = facebookClient.fetchConnection("me/photos", Photo.class,
                Parameter.with("fields", "id,created_time,source,comments,likes")/*, Parameter.with("limit",15)*/);
          userPhotos.setUserId(me.getId());
        for(Photo photos: connection.getData()) {
            userPhotos.setPhotoId(photos.getId());
            userPhotos.setCreatedAt(String.valueOf(photos.getCreatedTime()));
            userPhotos.setSource(photos.getSource());
            userPhotos.setLink(photos.getLink());

            List<NamedFacebookType> likes = photos.getLikes();

            for(NamedFacebookType like: likes){
                String name = like.getName();
                tempLikes.add(name);
                countOfLikes++;
            }
            userPhotos.setLikes(tempLikes);
            userPhotos.setNumberOfLikes(countOfLikes); //stores number of likes.
            List<Comment> comment = photos.getComments();
            for(Comment notes:comment){
                getcomments.setId(notes.getId());
                getcomments.setName(notes.getFrom().getName());
                getcomments.setMessage(notes.getMessage());
                getcomments.setLike_count(notes.getLikeCount());
                commentList.add(getcomments);
              //  userPhotos.setComments(commentList);
            }
            userPhotos.setComments(commentList);
            photoRepo.save(userPhotos);
        }
        return userPhotos;
    }

    public ArrayList<UserPhotos> listAllPhotos(String Id){
        ArrayList<UserPhotos> list = photoRepo.findByUserId(Id);
        return list;
    }
}
