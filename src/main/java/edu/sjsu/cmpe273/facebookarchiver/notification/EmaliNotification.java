package edu.sjsu.cmpe273.facebookarchiver.notification;

/**
 * Created by emy on 5/10/15.
 */
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;

public class EmaliNotification {
    private String secretKey="EVYOZVMJAFy7Zp2BMaogvAxXXo5G1JV3vS3UBId9";
    private String accessKey="AKIAJ2FPKKJXHFMTI72Q";
/*
    public static void main(String[] args) {

        AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
        snsClient.setRegion(Region.getRegion(Regions.US_WEST_2));

        CreateTopicRequest createTopicRequest = new CreateTopicRequest().withName("CMPE273-Project");
        CreateTopicResult createTopicResult =  snsClient.createTopic(createTopicRequest);
        //System.out.println(createTopicResult);

        //publish to a topic
        String message = "Thanks for signing up with out application";
        PublishRequest publishRequest = new PublishRequest(createTopicResult.getTopicArn(), message);

    }
    */

    public static void send(String message, String topic){

    }
}