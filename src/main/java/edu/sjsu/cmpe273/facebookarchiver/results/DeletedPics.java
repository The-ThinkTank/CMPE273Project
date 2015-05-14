package edu.sjsu.cmpe273.facebookarchiver.results;

import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Rajat on 5/13/2015.
 */
@Document(collection="DeletedPhotos")
public class DeletedPics {

    private String id;

    private List<UserPhotos> userPhotoses;

    public DeletedPics()
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
