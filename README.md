# InstaApi
## Instagram Application
### Requirments
 * IntelliJIDEA
 * Serverport: 8080 (use: localhost:8080)
 * Java version: 17
 * MySql Databse - insta_db
 * Everything is present in the pom.xml (no need to download any library)
 ### Steps to run program
 * Download the source code and import in intellijIDEA.
 * Go to localhost:8080/ 
 * Type endpoints in url or
 * You Can also Use Swagger 
 
### There are two models of -
#### 1- User has -
  * userId
  * firstName
  * lastName
  * phoneNumber
  * email

#### 2- Post (Many post can have one user - ManyToOne relationship) -
  * postId
  * createdDate
  * updatedDate
  * postData
  * user (type User)

### Note
* You can change server port by setting properties in application.properties file i.e.

        server.port=8081
 
 

