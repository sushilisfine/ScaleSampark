ScaleSampark
Software Requirements
1.	OpenJDK 11
2.	MySQL 8
3.	Apache-maven-4.0.0


Assumptions?


1.	Message Type as different types of messages with numeric values. Created a table for the same.
2.	If message is pooled by any of the present user, changed the status of message from unread to read of all the pending messages.
3.	I assumed that destroying state information will delete the registered user data and all the mapped messages.
4.	Default MySQL username and password, which can be changed in application.yml file.


Design decisions?
1.	Any user can register providing email and nickname.
2.	An email should be unique, so create unique index for the same.
3.	Any action by a user will lead to change in last seen status.
4.	Provided key for encryption in the application.properties file.

Requests/Responses
1.	Participant Registration: 

Method URL: http://localhost:9898/api/v1/participants
Method Type: POST
Sample Request: 
{
"email":"yadav.sushil@hotmail.com",
"nickname":"sushil"
}
Sample Response: 
     {
      "participant uuid": "52f34c29-a90b-4999-947a-6f54031030d7"
     }


2.	List a participant:
Method URL: http://localhost:9898/api/v1/participants/52f34c29-a90b-4999-947a-6f54031030d7
Method Type: GET
Sample Response: 
{
            "nickname": "sushil",
            "participant uuid": "52f34c29-a90b-4999-947a-6f54031030d7",
            "last seen": "2020-10-21T10:04:09"
}

3.	List all participants:
Method URL: http://localhost:9898/api/v1/participants
Method Type: GET
Sample Response: 
{"participants": [{
            "nickname": "sushil",
            "participant uuid": "52f34c29-a90b-4999-947a-6f54031030d7",
            "last seen": "2020-10-21T10:04:09"
}]}

4.	Incoming message request:
Method URL: http://localhost:9898/api/v1/messages
Method Type: POST
Sample Request: 
{
"message type":"text",
"message":"Custom Message",
"participant uuid":"52f34c29-a90b-4999-947a-6f54031030d7"
}

Sample Response:
{"Success": "Message sent successfully"}

5.	Poll Message:
Method URL: http://localhost:9898/api/v1/messages/?UUID=52f34c29-a90b-4999-947a-6f54031030d7
Method Type: GET
Sample Response:
{
"pendingMessage": [{
            "message": "Custom Message",
 "message uuid": "52f34c29-a90b-4999-947a-6f54031030d7",
 "participant uuid": "52f34c29-a90b-4999-947a-6f54031030d7"
,
            "message type": 21
           }]}
6.	Deregister User:
Method URL: http://localhost:9898/api/v1/participants/52f34c29-a90b-4999-947a-6f54031030d7
Method Type: DELETE
Sample Response:
{
    		"Successfully removed user ": 1
           }
