
  
# User Management 
Purpose of the application is to manage users with following functionality.
1. Registration
2. Authentication
3. Reset Password
    
## Getting Started    
 These instructions will get you a copy of the project up and running on your local machine.    
    
### Prerequisites 
What things you need to install the software.    
    
``` 
jdk 1.8 
maven 
git 
```    
 ### Installing 
 To Install project follow the below steps:    
    
Open Command prompt and run the following command to checkout the project.    
    
``` 
git clone https://github.com/Urja/userManagement.git
```  
Go to project directory    
```    
 cd userManagement   
 ```  
  Now build the application by using following command    
    
``` 
mvn clean install  
```    
 To run the application type following command    
    
``` 
mvn spring-boot:run 
```
Now application is up and running.
API can be access through the swagger.

 ## API 
 To get information about APIs or run the APIs open swagger ui.    
Open browser and type following URL    
    
``` 
http://localhost:8080/swagger-ui.html
```    
 #### /registration (POST) 
 Click on API and click on Try it out.
 Add request param.
 ```
  {"email" : "registration@test.com","password":"regTest","fullName" : "Test User"} 
 ```
 Execute the request.
 Now check the status 201.
 
#### /authenticate (POST) 
Click on API and click on Try it out.
Add request param.
 ```
  {"email" : "registration@test.com","password":"regTest"} 
 ```
Execute the request.
If email and password exist in the database it will give 200 response.
If email or password is incorrect it will gives 422 response.


#### /reset-password (PUT) 
Click on API and click on Try it out.
Add request param.
 ```
  { "email" : "urja1986@gmail.com", "oldPassword":"regTest", "newPassword" : "testNew" } 
 ```
Execute the request.
If email and oldPassword exist in the database it will give 200 response and update new password.
If email or oldPassword is incorrect it will gives 422 response.


## Future Enhancement 
1. Use real db instead of H2.
2. Store encrypted password.
3. Use oAuth.
 
