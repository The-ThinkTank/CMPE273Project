package edu.sjsu.cmpe273.facebookarchiver.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by  on 4/25/15.
 */
@Document(collection="UserAccounts")
public class UserAccounts {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String email;
    @Field
    private String gender;

    public UserAccounts() {}

    public void setId(String Id) {
        this.id = Id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public String getId() {
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getGender(){
        return this.gender;
    }
}
