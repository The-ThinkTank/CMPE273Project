package edu.sjsu.cmpe273.facebookarchiver.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by emy on 4/25/15.
 */
@Document
public class UserPhotos {

    @Field
    private String photoId;
    @Field
    private String createdAt;

    public UserPhotos() {}

    public void setId(String photoId){
        this.photoId=photoId;
    }
    public void setCreatedAt(String createdAt){
        this.createdAt=createdAt;
    }
}
