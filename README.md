# Labo-SMTP-2018-olivierKopp-florentPiller

## Projet Description 

This project is a client-server application that send forged email to a mock server.

## How to to setup a mock server

All you have to do is run the docker build form the docker directory-
In order to start the docker, use the commande docker run -d -p 25:25  -p 8282:8282 nameOfTheImage .
You can now send pranks and see the reslut in a web broser at the adress 192.168.99.100:8282.

## How to use the application

You just have to edit the files in the config directory.

### The configSMTP.utf8

this file allows you to change the address and port of the server and the number of groups you want to have

### The messagesConfig.utf8 
it contains the messages you want to send with the following syntax:

Subject: the subjet of the prank 

The prank you want to send 

== //the end of the message token

### The victimsConfig.utf8

it contains the email of the victims you want to prank.
The email address have to be something.otherthing@email or else the program crashes.
The ccVvictimes is the list email you want to send a the copy to
the bccVicitmes is the list of the hidden copy of mail to 
