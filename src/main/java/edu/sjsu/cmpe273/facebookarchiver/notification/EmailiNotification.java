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

public class EmaliNotification {
    private String secretKey="";
    private String accessKey="";
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




    public static void sendSubscription(){
	User me = facebookClient.fetchObject("me", User.class);
	String email = me.getEmail();
	String topic = "CMPE273Topic";

	 AmazonSNSClient service = new AmazonSNSClient(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));

	 // Create a topic
	CreateTopicRequest createReq = new CreateTopicRequest()
			.withName(topic);
		        //.withName("MyTopic");
		        CreateTopicResult createRes = service.createTopic(createReq);

		        // Subscribe to topic
	 SubscribeRequest subscribeReq = new SubscribeRequest()
		            .withTopicArn(createRes.getTopicArn())
		            .withProtocol("email")
		            //.withEndpoint("deng_yz1016@hotmail.com");
		            .withEndpoint(email);
		        service.subscribe(subscribeReq);



    }
}
