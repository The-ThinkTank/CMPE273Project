# CMPE273Project
CMPE 273 Project

Public DNS: ec2-54-153-90-122.us-west-1.compute.amazonaws.com
Please note that server may not be running.

Use Cases
- When a member of the group deletes a picture from facebook, all members who are subscribed will be notified based on the email address associated with the facebook account.
- If any subscribers account is breached the rest of the members will be notified via email.

Technology Stack
- Java
- Spring MVC
- MongoDB
- AmazonSNS

Model
- Comments.java
- UserAccounts.java
- UserPhotos.java

View
- logged.html
- login.html

Controller
- HomeController.java

Service
- EmailNotification.java  //Removed to protect AWS keys.

Run Server
- $ gradle clean
- $ gradle build
- $ gradle bootRun

Testing Considerations
- Use Postman/Advanced Rest Client to test Rest Request/Response.
- Ex: Test "GetTop5Likes" in Postman.
- Endpoint: http://localhost:8080/userAccounts/10152417721582614/Top5Likes
- Headers: Content-Type application/json
