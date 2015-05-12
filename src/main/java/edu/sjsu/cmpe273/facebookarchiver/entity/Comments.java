package edu.sjsu.cmpe273.facebookarchiver.entity;

/**
 * Created by emy on 5/10/15.
 */
public class Comments {

    private String Id;
    private String message;
    private String name;
    private long like_count;

    public void setId(String Id){
        this.Id=Id;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setLike_count(long like_count){
        this.like_count=like_count;
    }
    public String getId(){
        return Id;
    }
    public String getMessage(){
        return message;
    }
    public String getName(){
        return name;
    }
    public long getLike_count(){
        return like_count;
    }
}
