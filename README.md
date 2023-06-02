# Hel Online Library API

#### This application exposes following REST API endpoints

    * CRUD operations on books,authors,genres by authorised admin
    * Fetch All books, genres and authors
    * Borrow book/Return book endpoints
    * Transaction history based on authorised admin/user

### What I learnt from this
    How to make good data model
    How to design good API
    How to map java object modeling to database with hibernate
    How basic concepts of OOP works
    How to layer the backend code by splitting it to individual modules
    
## Prerequisite
 #### Following tools required,
    * Java 8 or higher
    * Maven 3
    * spring-boot 3.0.6
    * postgresql
    
## Database connection commands

#### To start the service:
Update `src/main/resources/application.properties` with appropriate connection parameters

##### Terminal:
`brew services start postgresql@14`
##### Connect DB
`psql -U postgres`

## To start application in local
Run `git clone https://github.com/Deepi03/Hel-Library-Backend-Service.git`

Run `mvn clean install`
## Build and Run
`mvn spring-boot:run`

service should start in `http://localhost:8080`

============================================

## Test endpoints using postman/insomnia

## Book

### Get Books
##### Method: GET
URL:

  local host:  `http://localhost:8080/api/v1/books/`

  live :`https://hel-library-web-service.onrender.com/api/v1/books/`

### Get Single Book
##### Method: GET

URL:

   localhost :  `http://localhost:8080/api/v1/books/${bookId}`

   live :  `https://hel-library-web-service.onrender.com/api/v1/books/${bookId}`


### Create Book (Role : ADMIN)
##### Method: POST
URL:

localhost :`http://localhost:8080/api/v1/admin/books`


live :`https://hel-library-web-service.onrender.com/api/v1/admin/books`
Header : `Bearer Token`

Request Body :
`
{
genre:"dcsdacadscsavcsfcv";
author:"csdacvsfdvdsfvdfv";
title:"Far from lights";
isbn:"dsvsdfvdfvdfv";
publishedDate:"1960-07-18";
publisher:"miriam";
cover:"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o";
description:"cadsvadsvdfv";
isAvailable:yes;
}`

Response :

`
{
"id":"cdsvcsdfvdfvdfbv";
genre:"dcsdacadscsavcsfcv";
author:"csdacvsfdvdsfvdfv";
title:"Far from lights";
isbn:"dsvsdfvdfvdfv";
publishedDate:"1960-07-18";
publisher:"miriam";
cover:"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o";
description:"cadsvadsvdfv";
isAvailable:yes;
}`



OR
`
{
"statusCode": 400,
"message": "Given Book title or isbn already exist or bad input"
}
`

### Update Book (Role : ADMIN)
##### Method: PUT

URL:

 localhost : `http://localhost:8080/api/v1/admin/books/${bookId}`


 live :`https://hel-library-web-service.onrender.com/api/v1/admin/books/${bookId}`

Header : `Bearer Token`

Request Body :
`
{
"id":"cdsvcsdfvdfvdfbv",
genre:"dcsdacadscsavcsfcv",
author:"csdacvsfdvdsfvdfv",
title:"Far from lights",
isbn:"dsvsdfvdfvdfv",
publishedDate:"1960-07-18",
publisher:"miriam",
cover:"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o",
description:"cadsvadsvdfv",
isAvailable:yes,
}`


Response :
`
{
"id":"cdsvcsdfvdfvdfbv",
genre:"dcsdacadscsavcsfcv",
author:"csdacvsfdvdsfvdfv",
title:"Far from lights",
isbn:"dsvsdfvdfvdfv",
publishedDate:"1960-07-18",
publisher:"miriam",
cover:"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o",
description:"cadsvadsvdfv",
isAvailable:yes,
}`



OR
`
{
"statusCode": 400,
"message": "Given Book title or isbn already exist or bad input"
}
`

### Delete Book (Role: ADMIN)
##### Method: Delete

URL:


localhost : `http://localhost:8080/api/v1/admin/books/${bookId}/`

live :`https://hel-library-web-service.onrender.com/api/v1/admin/books/${bookId}`

Response : 200 ok

or

`
{
"statusCode": 404,
"message": "Book not found"
}`

<============================================>

## Author

### Get Authors
##### Method: GET

URL:


live:`http://localhost:8080/api/v1/authors/`

live :`https://hel-library-web-service.onrender.com/api/v1/authors/`


### Get Single Author
##### Method: GET

URL:

localhost: `http://localhost:8080/api/v1/authors/${authorId}`

live :`https://hel-library-web-service.onrender.com/api/v1/authors/${authorId}`


### Create Author (Role : ADMIN)
#### Method: POST
URL:


localhost:`http://localhost:8080/api/v1/admin/authors`

live :`https://hel-library-web-service.onrender.com/api/v1/admin/authors`

Header : `Bearer Token`

Request Body :
`
{
name:"Donna",
info:"ewgfdasdhfasdfjasfdjyasdfhjadsfhjdasfg",
image:"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o"
}`

Response :

`
{
"id":"csafdvfdbvdf"
"name":"Donna";
"info":"ewgfdasdhfasdfjasfdjyasdfhjadsfhjdasfg";
"image":"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o";
}`


OR
`
{
"statusCode": 400,
"message": "Given author name already exist or bad input"
}`

### Update Author (Role : ADMIN)
#### Method: PUT
URL:


localhost:`http://localhost:8080/api/v1/admin/authors/${authorId}`

live :`https://hel-library-web-service.onrender.com/api/v1/admin/authors/${authorsId}`

Header : `Bearer Token`

Request Body :
`
{
"id":"csafdvfdbvdf",
name:"Donna",
info:"ewgfdasdhfasdfjasfdjyasdfhjadsfhjdasfg",
image:"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o"
}`

Response :

`
{
"id":"csafdvfdbvdf"
"name":"Donna";
"info":"ewgfdasdhfasdfjasfdjyasdfhjadsfhjdasfg";
"image":"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o";
}`


OR
`
{
"statusCode": 400,
"message": "Given author name already exist or bad input"
}`

### Delete Author (Role: ADMIN)
##### Method: Delete

URL:


localhost:`http://localhost:8080/api/v1/admin/authors/${authorId}`

live :`https://hel-library-web-service.onrender.com/api/v1/admin/authors/${authorId}`

Response : 200 ok

or

`
{
"statusCode": 404,
"message": "Author not found"
}`


<====================================================>

## GENRE

### Get Genres
##### Method: GET

URL:
localhost:`http://localhost:8080/api/v1/genres/`


live :`https://hel-library-web-service.onrender.com/api/v1/genres/`


### Get Single Genre
##### Method: GET

URL:


localhost:`http://localhost:8080/api/v1/genres/${genreId}`

live :`https://hel-library-web-service.onrender.com/api/v1/genres/${genreId}`


### Create Genre (Role : ADMIN)
#### Method : POST

URL:


localhost:`http://localhost:8080/api/v1/admin/genres`

live:`https://hel-library-web-service.onrender.com/api/v1/admin/genres`

Header : `Bearer Token`

Request Body :
`
{ 
name:"History",
description:" fvdv fdvdfvdfbvdf",
coverImage:"https://fastly.picsum.photos/id/287/200/300.jpg?hmac=9JSSeZMseJ8l_WTFAMmF3HXoyYmKFzQxmagpyWBefTA",
}`

Response :

`
{
id:"cfdavdfavdfv",
name:"History",
description:" fvdv fdvdfvdfbvdf",
coverImage:"https://fastly.picsum.photos/id/287/200/300.jpg?hmac=9JSSeZMseJ8l_WTFAMmF3HXoyYmKFzQxmagpyWBefTA",
}`


OR
`
{
"statusCode": 400,
"message": "Given genre name already exist or bad input"
}
`


## Update Genre (Role : ADMIN)

##### Method: PUT

URL:


localhost:`http://localhost:8080/api/v1/admin/genres/${genreId}`

live:`https://hel-library-web-service.onrender.com/api/v1/admin/genres/${genreId}`

Header : `Bearer Token`

Request Body :
`
{
name : "Historical";
description:"csdvsfvafsvfdvdf";
coverImage : "https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o";
}`

Response :

`
{
"id":"csdcsvfvfvfvfvfsvsf"
"name":"Historical";
"coverImage":"csdvsfvafsvfdvdf";
"description":"https://fastly.picsum.photos/id/421/200/300.jpg?hmac=uS2eM0G0F1Jupud0-BfxxJpQ3_kL8LFMxr6EHhop69o";
}`


OR
`
{
"statusCode": 400,
"message": "Given genre name already exist or bad input"
}
`

### Delete Genre (Role: ADMIN)
##### Method: Delete

URL:


localhost:`http://localhost:8080/api/v1/admin/genres/{genreId}`

live:`https://hel-library-web-service.onrender.com/api/v1/admin/genres`/${genreId}

Response : 200 ok

or

`
{
"statusCode": 404,
"message": "Genre not found"
}`

<=====================================================>

## Transaction

### Borrow Book (Role : ADMIN)
##### Method: POST
URL:


localhost:`http://localhost:8080/api/v1/transactions/borrow`

live:`https://hel-library-web-service.onrender.com/api/v1/transactions/borrow`

Header : `Bearer Token`

Request Body :
`
{
userId:"fdsfvdfv"";
bookId:"vvdgbdfgbgf";
day: either  TEN,TWENTY,THIRTY ;
}`

Response :

`
{
"id": "transactionId",
"user": "userId",
"book": "bookId",
"borrowDate": "borrow date",
"returnDate": null,
"toBeReturned": "return date",
"returned": true / false
}`


OR
`
{
"statusCode": 400,
"message": "Transaction cannot be created/updated! please check input"
}
`

### Return Book (Role : User)
##### Method: GET

URL:


localhost:`http://localhost:8080/api/v1/transactions/return/${transactionId}`

live:`https://hel-library-web-service.onrender.com/api/v1/transactions/return/${transactionId}`

Header : `Bearer Token`


### Get All Transactions (Role : ADMIN)
##### Method: GET

URL:


localhost:`http://localhost:8080/api/v1/admin/transactions`

live:`https://hel-library-web-service.onrender.com/api/v1/admin/transactions

Header : `Bearer Token`


### Transaction history of user (Role : User)
##### Method: GET

URL:


localhost:`http://localhost:8080/api/v1/transactions/user/${userId}`

live:`https://hel-library-web-service.onrender.com/api/v1/transactions/user/${userId}`

Header : `Bearer Token`


### Delete Transaction (Role: ADMIN)
##### Method: Delete

URL:


localhost:`http://localhost:8080/api/v1/admin/transactions/${transactionId}`


live:`https://hel-library-web-service.onrender.com/api/v1/admin/transactions/${transactionId}`

Response : 200 ok

or

`
{
"statusCode": 404,
"message": "Author not found"
}`

<=================================================>

## User

### Sing Up
##### Method: POST
URL:


localhost: `http://localhost:8080/api/v1/users/signup`


live: `https://hel-library-web-service.onrender.com/api/v1/users/signup`


Request Body :
`
{
username:"Laura";
password:"123";
}`

Response :

`
{
"statusCode": 200,
"message": "User created Successfully"
}`


OR
`
{
"statusCode": 400,
"message": "User name already exist"
}
`
### Login
##### Method: POST


URL:


localhost: `http://localhost:8080/api/v1/users/signin`

live:`https://hel-library-web-service.onrender.com/api/v1/users/signin`


Request Body :
`
{
username:"Laura";
password:"123";
}`

Response :

`
{
"token": "eyJhbfvdfGciOiJIUzImjhkgkm1NiJ9.eyJyb2xlIjoiVVNFUiIsInVzZXJfaWQiOiIwZDM2ZTJmMC0wMWQ1LTRhY2QtYjQwOS1iNzA0MjVjMzczOGIiLCJ1c2VybmFtZSI6Ik1vdW5pa2EiLCJzdWIiOiJNb3VuaWthIiwiaWF0IjoxNjg1NjI3ODYwLCJleHAiOjE2ODU5ODc4NjB9.MOMwHwNp0E94pafinxGRTURarvk4rWCVItEZtCLQHyU"
}`


OR
`
{
"statusCode": 400,
"message": "Login credentials not match"
}
`


### Get All Users (Role : ADMIN)
##### Method: GET

URL:
localhost: `http://localhost:8080/api/v1/admin/users`

live:`https://hel-library-web-service.onrender.com/api/v1/admin/users`

Header : `Bearer Token`


![highlevel.png](highlevel.png)


![erd.png](erd.png)



### Potential plan
 
  * more clean code with good documentation(Swagger documentation)
  * Better endpoints which follows REST API constraints and architecture
  * Better validations and exception handling
  * As there is no space for unit testing , planning to implement integration and e2e testing




























    
