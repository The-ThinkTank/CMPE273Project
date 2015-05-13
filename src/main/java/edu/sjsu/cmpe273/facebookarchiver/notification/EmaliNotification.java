package edu.sjsu.cmpe273.facebookarchiver.notification;

/**
 * Created by emy on 5/10/15.
 */
public class EmaliNotification {
    //private String secretKey="";
    //private String accessKey="";
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