package edu.sjsu.cmpe273.facebookarchiver.notification;

/**
 */

/*
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;*/


import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.*;
import com.amazonaws.services.sns.model.*;
import com.restfb.types.User;
import com.restfb.FacebookClient;

public class EmailNotification {
    private static String secretKey="AKIAJ5BVG3SDMJRZ6ZSQ";
    private static String accessKey="wcviO7XRG+13pSIB1op6hvNbIrE+zgOoCTahJzad";
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




    public static void sendSubscription(FacebookClient facebookClient){
	User me = facebookClient.fetchObject("me", User.class);

	String email = me.getEmail();
	String topic = "CMPE273Topic";

	 AmazonSNSClient service = new AmazonSNSClient(new BasicAWSCredentials(secretKey, accessKey));
        //Need to fill in AWS credentials

	 // Create a topic
	CreateTopicRequest createReq = new CreateTopicRequest()
			.withName(topic);
		        //.withName("MyTopic");
		        CreateTopicResult createRes = service.createTopic(createReq);

        //Print out TopicArn for future parameter usage.
        System.out.println(createRes);
		        // Subscribe to topic
	 SubscribeRequest subscribeReq = new SubscribeRequest()
		            .withTopicArn(createRes.getTopicArn())
		            .withProtocol("email")
		            //.withEndpoint("deng_yz1016@hotmail.com");
		            .withEndpoint(email);
		        service.subscribe(subscribeReq);

        //Publish a topic
        System.out.println("1");
        String topicArn = "arn:aws:sns:us-east-1:071202288544:CMPE273Topic";
        String msg = "This is your facebook feed";
        System.out.println("2");
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        System.out.println("3");
        PublishResult publishResult = service.publish(publishRequest);
        System.out.println("4");
        //print MessageId of message published to SNS topic
        System.out.println("MessageId - " + publishResult.getMessageId());

    }
}
