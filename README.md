# Labo-SMTP-2018-olivierKopp-florentPiller

## Projet Description 

This project allow a client to prank a list of chosen victims by sending them a forged email containing a random prank message.

## How to to setup a mock server

All you have to do is run the docker build form the docker directory.
In order to start the docker, use the commande `docker run -d -p 25:25  -p 8282:8282 nameOfTheImage` .
You can now send pranks and see the result in a web browser at the address 192.168.99.100:8282.
It allow you to test the application before sending real prank.

## How to use the application

You just have to edit the files in the config directory.

### The configSMTP.utf8

this file allows you to change the address and port of the server and the number of groups you want to have

### The messagesConfig.utf8 
it contains the messages you want to send with the following syntax:

Subject: the subject of the prank 

The prank you want to send 

== //the end of the message token

### The victimsConfig.utf8

it contains the email of the victims you want to prank.
All the emails need to have the same smtp server, for example only gmail address.
The ccVictims is the list of email you want to send a copy to
the bccVicitms is the list of email you want to send a hidden copy to 
The syntax of this file is :
victims
[list of victims]
ccVictims
[list of ccVictims]
bccVictims
[list of bccBictims]

the 3 categories need to be correctly spelled or the program won't work properly.
