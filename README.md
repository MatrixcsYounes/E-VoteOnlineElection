# E-VoteOnlineElection
Web App project using : <br>
## in the BackEnd  
```
*Spring Boot 3.0.5* with the integration of features and dependencies such as *Hibernate, Lombok, JWT*  
```
## in FrontEnd  
```
*Thymeleaf* with the integration of Asynchronous JavaScript and XML (aka "Ajax") to handle the JWT tokens with other functionalities such as the logout event, login, etc.  
```
## in the Database: 
```
MySQL as a Docker image 
```
# Requirements 

Mysql DOCKER image:
```
docker pull mysql
docker run -d -p 3306:3306 -p 33060:33060 -e MYSQL_ROOT_PASSWORD=root mysql
```
Application.properties settings:
## *this will create a database in MySQL called VoteMaster.*
```
spring.datasource.url=jdbc:mysql://localhost:3306/VoteMaster?createDatabaseIfNotExist=true
```
## *the user that will use the DB is "root", with a password "root"*
```
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Once done. just run the Docker container first, then run the project using the IDE Intellij for example.
