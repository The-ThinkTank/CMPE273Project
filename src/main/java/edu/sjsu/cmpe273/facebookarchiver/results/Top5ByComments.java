package edu.sjsu.cmpe273.facebookarchiver.results;

import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;

import java.util.List;

/**
 * Created by Rajat on 5/13/2015.
 */
//@Document(collection="Top5ByComments")
public class Top5ByComments {

    private String id;

    private List<UserPhotos> userPhotoses;

    public Top5ByComments()
    {
    }

    public void setId(String Id){
        this.id=Id;
    }

    public String getId()
    {
        return id;
    }

    public void setUserPhotoses(List<UserPhotos> userPhotoses){
        this.userPhotoses=userPhotoses;
    }
    public List<UserPhotos> getUserPhotoses()
    {
        return this.userPhotoses;
    }
}
