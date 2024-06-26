# JavaChat  
### a chat software developed in **JavaFX**

<p align="center">
  <a href="https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/README.md">简体中文</a>
  ｜
  English
</p>

---

## introduction

Java Chat This is my final assignment for the second half of my freshman semester. <br/>
The interface is based on JavaFX, which is more modern than swing, and the database is based on Mysql. <br/>
It realizes the basic chat functions, including sending messages, receiving messages, viewing history messages, user registration, login, etc. <br/>
At the same time, it uses Redis to cache user information. The use of Redis cache user information to improve the user experience. <br/>
With a complete graphical interface , UI design using SceneBuilder , create fxml files , and use CSS to style the interface . <br/>
This is my first properly written big assignment now, you may wish to point a star to support qwq. 🤣
Thank you for your reading.😘

## UI

- log in screen <br/>

![log in screen](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/logIn.png)

- registration screen <br/>

![registration screen](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/logUp.png)

- error screen <br/>

![error screen](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/logInErroe.png)

- User Lists <br/>

![User Lists](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/main.png)

- chat room <br/>

![chat room](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/chatRoomP.png)

> UI design is rather ugly, will be optimized in the future
> If you have any suggestions, please feel free to submit them.
> The next step is to realize the function 😊

---

## Requirements and realization

#### log in

- [x] Login with account password
- [x] false alert
- [ ] memorize passwords
- [ ] automatic login
- [ ] Separate logins for administrators and regular users
- [ ] verification code

#### register

- [x] Input box validation
- [ ] verification code
- [ ] Register Successful Alert
- [ ] Avatar Settings
- [ ] Email Verification Activation

#### User Lists

- [x] Your own friends list
- [x] add a friend
- [x] delete a friend
- [x] Chat with friends
- [ ] History messages
- [ ] group chat
- [ ] black lists
- [ ] Add a friend to agree to the application

#### chat room

- [x] send a message
- [x] receive a message
- [x] Historical messages
- [ ] Real-time message
- [ ] Picture and file sending
- [ ] Emoji sending
- [ ] Voice sending
- [ ] Video calling
- [ ] kicker
- [ ] ban user
- [ ] Administrator Functions

> These are all the features of Java Chat, and will continue to improve! <br/>
> The unfinished functions will be learned slowly <br/>
> If you think it's functional enough to be used as a big job <br/>
> Here's a tutorial on how to run it <br/>
> A few quick steps to get up and running 🥰 <br/>

---

## Running

- Currently, the author doesn't have his own server 🙁 so you needs to set up your own server <br/>
- server-side <a href="https://github.com/Cthaat/JavaChatSer" target="_blank">JavaChatSer</a> <br/>
- It will be deployed to the server later for your convenience 😘

--- 

## Server deployment


- Clone server-side code to local
- Import Mysql database, create database and tables
- Use `src/main/resources/javaChat.sql` to create tables
- Download redis as a caching database
- Starting the redis server
- Modify the database connection information in the configuration file
- The configuration information is in the `src/main/resources` resource folder.
- Run the client and connect to the server

---

## Client Deployment

- Clone client-side code to local
- Download Java Environment
- Note that JavaFx requires a separate download
- Ruuuuuuuuuuun it.


--- 

## Technology used

- Java
    - JavaFX
    - JDK 21
- SQL
    - Mysql
    - Redis
    - JDBC
- database connection pool
    - Druid
- GUI
    - SceneBuilder
    - CSS
    - FXML
- network communication
    - Socket
    - JSON
    - HTTP
- build tool
    - Maven
    - Spring
- others
    - Jackson
    - Jedis
    - Lombok
    - Log4j
    - JUnit
---

> Have fun!😘 <br/>
> I hope you enjoy the program! <br/>
> We also hope that you will give us more advice and make progress together! <br/>
> If you have any questions or suggestions, please feel free to contact us by issue! <br/>
> Finally, thank you all for your support!🤗

---

Powered by [Cthaat](https://github.com/Cthaat)

