package edu.sjsu.cmpe273.facebookarchiver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.List;

/**
 * Created by emy on 4/25/15.
 */
@Document(collection = "userPhotos")
public class UserPhotos {


    @Id
    private String Id;

    private String createdAt;

    private String userId;
    private List<String> likes;
    private String source;
    private String link;
    private List<Comments> comments;
    private int numberOfLikes;


    public UserPhotos() {}

    public void setPhotoId(String Id){
        this.Id=Id;
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
    public void setLink(String link){
        this.link=link;
    }
    public String getSource(){return this.source;}
    public String getLink(){
        return this.link;
    }

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
    public String getId(){
        return Id;
    }
    public String getCreatedAt(){
        return createdAt;
    }
}
