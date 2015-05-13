package edu.sjsu.cmpe273.facebookarchiver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Created by emy on 4/25/15.
 */
@Document(collection = "userPhotos")
public class UserPhotos {


    @Id
    private String photoId;

    private String createdAt;

    private String userId;
    private List<String> likes;
    private String source;
    private List<Comments> comments=new ArrayList<Comments>();
    private int numberOfLikes;
    private int numberOfComments;


    public UserPhotos(){

    }

    public void setPhotoId(String photoId){
        this.photoId=photoId;
    }
    public void setCreatedAt(String createdAt){
        this.createdAt=createdAt;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public void setSource(String source){
        this.source = source;
    }
    public void setNumberOfLikes(int numberOfLikes){
        this.numberOfLikes=numberOfLikes;
    }
    public String getSource(){return this.source;}


    public void setLikes(List<String> likes){
        this.likes=likes;
    }
    public void setComments(List<Comments> comments){
        this.comments=comments;
    }

    public List<String> getLikes(){return likes;}

    public List<Comments> getComments(){return comments;}
    public int getNumberOfLikes(){return numberOfLikes;}
    public String getUserId(){
        return userId;
    }
    public String getPhotoId(){
        return photoId;
    }
    public String getCreatedAt(){
        return createdAt;
    }
    public void setNumberOfComments(int numberOfComments){
        this.numberOfComments=numberOfComments;
    }
    public int getNumberOfComments(){
        return numberOfComments;
    }

  public static Comparator<UserPhotos> UserPhotosComparator = new Comparator<UserPhotos>() {
      @Override
      public int compare(UserPhotos o1, UserPhotos o2) {
          int result = o1.getNumberOfLikes();
          int result2 = o2.getNumberOfLikes();
          return result2-result;
      }
  };

    public static Comparator<UserPhotos> userCommentsComparator = new Comparator<UserPhotos>() {
        @Override
        public int compare(UserPhotos o1, UserPhotos o2) {
            int comment1 = o1.getNumberOfComments();
            int comment2 = o2.getNumberOfComments();
            return comment2-comment1;
        }
    };
}
