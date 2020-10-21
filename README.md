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

Method URL: http://localhost:9898/api/v1/register
Method Type: POST
Sample Request: 
{
"email":"yadav.sushil@hotmail.com",
"nickname":"sushil"
}
Sample Response: 
     {
      "participant uuid": 7
     }


2.	List all participants:
Method URL: http://localhost:9898/api/v1/participants/1
Method Type: GET
Sample Response: 
{
    "participants": [{
            "nickname": "sushil",
            "participant uuid": 1,
            "last seen": "2020-10-21T10:04:09"
}]}

3.	Incoming message request:
Method URL: http://localhost:9898/api/v1/message
Method Type: POST
Sample Request: 
{
"message type":21,
"message":"Custom Message",
"participant uuid":1
}

Sample Response:
{
    "Success": "Message sent successfully"
}

4.	Poll Message:
Method URL: http://localhost:9898/api/v1/message/1
Method Type: GET
Sample Response:
{
 "pendingMessage": [{
            "message": "Custom Message",
            "message uuid": 1,
            "participant uuid": 1,
            "message type": 21
           }]}

5.	Deregister User:
Method URL: http://localhost:9898/api/v1/deregister/1
Method Type: DELETE
Sample Response:
{
    		"Successfully removed user ": 1
           }
