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


import java.util.ArrayList;
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
    private static String secretKey="";
    private static String accessKey="";


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



//        ConfirmSubscriptionResult result = service.confirmSubscriptionResult(service.subscribe(subscribeReq).getSubscriptionArn());



//            //Publish a topic
//            System.out.println("1");
//            String topicArn = "arn:aws:sns:us-east-1:071202288544:CMPE273Topic";
//            String msg = "Your Facebook picture: ";
//            System.out.println("2");
//            PublishRequest publishRequest = new PublishRequest(topicArn, msg);
//            System.out.println("3");
//            PublishResult publishResult = service.publish(publishRequest);
//            System.out.println("4");
//            //print MessageId of message published to SNS topic
//            System.out.println("MessageId - " + publishResult.getMessageId());


    }
    public static void sendMessage(ArrayList<String> picId){
        //Publish a topic
        AmazonSNSClient service = new AmazonSNSClient(new BasicAWSCredentials(secretKey, accessKey));
        String msg = "";
        System.out.println("1");
        String topicArn = "arn:aws:sns:us-east-1:071202288544:CMPE273Topic";
        msg += "Your top five Facebook pictures: ";
        for(String pictureId : picId){
            msg += pictureId + "";
        }

        System.out.println("2");
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        System.out.println("3");
        PublishResult publishResult = service.publish(publishRequest);
        System.out.println("4");
        //print MessageId of message published to SNS topic
        System.out.println("MessageId - " + publishResult.getMessageId());
    }

    public static void sendMessage2(String message){
        AmazonSNSClient service = new AmazonSNSClient(new BasicAWSCredentials(secretKey, accessKey));
        System.out.println("1");
        String topicArn = "arn:aws:sns:us-east-1:071202288544:CMPE273Topic";
        System.out.println("2");
        PublishRequest publishRequest = new PublishRequest(topicArn, message);
        System.out.println("3");
        PublishResult publishResult = service.publish(publishRequest);
        System.out.println("4");
        //print MessageId of message published to SNS topic
        System.out.println("MessageId - " + publishResult.getMessageId());

    }
}
