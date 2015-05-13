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
import edu.sjsu.cmpe273.facebookarchiver.repository.AccountRepo;
import edu.sjsu.cmpe273.facebookarchiver.repository.PhotoRepo;
import edu.sjsu.cmpe273.facebookarchiver.repository.Top5ByCommentsRepo;
import edu.sjsu.cmpe273.facebookarchiver.repository.Top5LikesRepo;
import edu.sjsu.cmpe273.facebookarchiver.results.Top5ByComments;
import edu.sjsu.cmpe273.facebookarchiver.results.Top5ByLikes;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    Top5ByCommentsRepo top5ByCommentsRepo;
    @Autowired
    Top5LikesRepo top5LikesRepo;

    private String topic="CMPE273-Topic";

    @Override
    public UserPhotos create(FacebookClient facebookClient){
        UserPhotos userPhotos = new UserPhotos();
        User me = facebookClient.fetchObject("me", User.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String createdAt;

        int countOfLikes;//tracks number of likes.

        Connection<Photo> connection = facebookClient.fetchConnection("me/photos", Photo.class,
               Parameter.with("fields", "id,created_time,source,comments,likes"),Parameter.with("limit",200));
          userPhotos.setUserId(me.getId());
        for(Photo photos: connection.getData()) {
                List<String> tempLikes = new ArrayList<String>();
                List<Comments> commentList = new ArrayList<Comments>();
                userPhotos.setPhotoId(photos.getId());
                createdAt = dateFormat.format(photos.getCreatedTime());
                userPhotos.setCreatedAt(createdAt);
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
                photoRepo.save(userPhotos);
            }

        return userPhotos;
    }

    public ArrayList<UserPhotos> listAllPhotos(String Id){
        ArrayList<UserPhotos> list = photoRepo.findByUserId(Id);
        return list;
    }

    @Override
    public List<UserPhotos> getTopPhotoByLikes(String id){
        List<UserPhotos> userPhotosArrayList = photoRepo.findByUserId(id);
        Top5ByLikes top5ByLikes = new Top5ByLikes();

        Collections.sort(userPhotosArrayList, UserPhotos.UserPhotosComparator);
        List<UserPhotos> userPhotoses = new ArrayList<UserPhotos>();
        int i=0;
        for(UserPhotos photos: userPhotosArrayList){
            userPhotoses.add(photos);
            i++;
            if(i==5)
            {
                break;
            }
        }
        top5ByLikes.setUserPhotoses(userPhotoses);
        top5LikesRepo.save(top5ByLikes);
        return userPhotoses;

    }

    @Override
    public List<UserPhotos> getTopPhotoByComments(String id){
        List<UserPhotos> userPhotosArrayList = photoRepo.findByUserId(id);
        Top5ByComments top5ByComments = new Top5ByComments();

        List<UserPhotos> userPhotosList = new ArrayList<UserPhotos>();
        Collections.sort(userPhotosArrayList, UserPhotos.userCommentsComparator);

        int i=0;
        for(UserPhotos userPhotos: userPhotosArrayList){
            userPhotosList.add(userPhotos);
            i++;
            if(i==5){
                break;
            }
        }
        top5ByComments.setUserPhotoses(userPhotosList);
        top5ByCommentsRepo.save(top5ByComments);
        return userPhotosList;
    }



    @Override
    public List<UserPhotos> getPicsYear(String Id, int years) throws ParseException {
        ArrayList<UserPhotos> photosList = photoRepo.findByUserId(Id);
        List<UserPhotos> newList = new ArrayList<UserPhotos>();
       DateTimeFormatter year = DateTimeFormat.forPattern("yyyy");
        //DateTime date = new DateTime();
        DateTime dateTime = new DateTime();
        //int a = new DateTime().getYear()-years;
        System.out.println(new DateTime().getYear()-years);
        DateTime subtracted = dateTime.minusYears(new DateTime().getYear()-years);
        String date;


        for(UserPhotos eachPic: photosList){
            String previousYear = eachPic.getCreatedAt();
            date = year.print(subtracted);
            if(date.equals(previousYear)){
                newList.add(eachPic);
            }

        }
        return newList;
    }
    @Override
    public String deletePhoto(String id, String PhotoId){
        if(accountRepo.exists(id)){
            photoRepo.delete(PhotoId);
            return "Photo is deleted";
        }
        else
            return "Photo does not exist";
    }

    @Override
    public UserPhotos findPhoto(String Id, String PhotoId){
        if(accountRepo.exists(Id)){
            UserPhotos userPhotos = photoRepo.findByPhotoId(PhotoId);
            return userPhotos;
        }
        return null;
    }

}
