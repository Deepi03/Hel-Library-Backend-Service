# Hel Online Library API

Live Service

#### Get all books
`https://hel-library-web-service.onrender.com/api/v1/books/`



#### This application does the following

    * All Users can see the books , genres and authors
    * Authenticated User can borrow books for 10,20 or 30 days,return books also user can see his/her transactions history 
    * Authorised Admin can do basic CRUD operations such as Create,Read,Update and Delete books,authors ,genres 
      as well as transactions.

### What I learnt from this
    How to make good data model
    How to design good API
    How to map java object modeling to database with hibernate
    How basic concepts of OOP works
    How to layer the backend code by splitting it to individual modules
## Prerequisite
 #### Following tools required,
    * Java 8 or greater
    * Maven 3
    * spring-boot 3.0.6
    * postgresql
## Database connection commands
#### To start the service:
    
##### Command prompt:

`net start postgrsql`

OR

##### Terminal:
`brew services restart postgresql@14`
##### Connect DB
`psql -U postgres`

## Build and Run
  ### API
`mvn spring-boot:run`

============================================

## Using postman/insomnia

## Book

### Get Books
##### Method: GET
URL:

  local host:  `http://localhost:8080/api/v1/books/`

  live :`https://hel-library-web-service.onrender.com/api/v1/books/`

Response : `
[
{
"id": "5a369ecf-c5c6-4c5a-8272-cb4e5f2a9f58",
"title": "Kalevala",
"isbn": "110366567-6",
"author": "85e37ac3-66eb-47a4-89f5-b7ff7d8d3dc0",
"genre": "db9f9b2e-669e-4fe3-a050-748a4cc3437d",
"publishedDate": "1859-10-02T00:00:00.000+00:00",
"publisher": "Kukkonen",
"cover": "https://fastly.picsum.photos/id/187/200/300.jpg?hmac=RGKQU40hHnXm-pBoMbUE5TDcy26DLc6CdcqednFcmB0",
"description": "The Kalevala (Finnish: Kalevala, IPA: [ˈkɑleʋɑlɑ]) is a 19th-century work of epic poetry compiled by Elias Lönnrot from Karelian and Finnish oral folklore and mythology,[1] telling an epic story about the Creation of the Earth, describing the controversies and retaliatory voyages between the peoples of the land of Kalevala called Väinölä and the land of Pohjola and their various protagonists and antagonists, as well as the construction and robbery of the epic mythical wealth-making machine Sampo.[2]",
"available": true
},
{
"id": "d22eb419-868e-4c9a-b5eb-a54bbbae524c",
"title": "Living in Oblivion",
"isbn": "703663487-1",
"author": "b04ac11c-05bb-4dec-9fbc-b7c15c2dd4b4",
"genre": "db9f9b2e-669e-4fe3-a050-748a4cc3437d",
"publishedDate": "1959-10-02T00:00:00.000+00:00",
"publisher": "Tobey Groocock",
"cover": "https://fastly.picsum.photos/id/175/200/300.jpg?hmac=jzS4h5cKiC2EYjpW11ejlay_4MSHkmfCWei9e7n8cE0",
"description": "The Adventures of Sherlock Holmes is a collection of twelve short stories by Arthur Conan Doyle, first published on 14 October 1892. It contains the earliest short stories featuring the consulting detective Sherlock Holmes, which had been published in twelve monthly issues of The Strand Magazine from July 1891 to June 1892. The stories are collected in the same sequence, which is not supported by any fictional chronology. The only characters common to all twelve are Holmes and Dr. Watson and all are related in first-person narrative from Watson's point of view.",
"available": false
}]`

### Get Single Book
##### Method: GET

URL:

   localhost :  `http://localhost:8080/api/v1/books/${bookId}`

   live :  `https://hel-library-web-service.onrender.com/api/v1/books/${bookId}`

Response : `{
"id": "d22eb419-868e-4c9a-b5eb-a54bbbae524c",
"title": "Living in Oblivion",
"isbn": "703663487-1",
"author": "b04ac11c-05bb-4dec-9fbc-b7c15c2dd4b4",
"genre": "db9f9b2e-669e-4fe3-a050-748a4cc3437d",
"publishedDate": "1959-10-02T00:00:00.000+00:00",
"publisher": "Tobey Groocock",
"cover": "https://fastly.picsum.photos/id/175/200/300.jpg?hmac=jzS4h5cKiC2EYjpW11ejlay_4MSHkmfCWei9e7n8cE0",
"description": "The Adventures of Sherlock Holmes is a collection of twelve short stories by Arthur Conan Doyle, first published on 14 October 1892. It contains the earliest short stories featuring the consulting detective Sherlock Holmes, which had been published in twelve monthly issues of The Strand Magazine from July 1891 to June 1892. The stories are collected in the same sequence, which is not supported by any fictional chronology. The only characters common to all twelve are Holmes and Dr. Watson and all are related in first-person narrative from Watson's point of view.",
"available": false
}`

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

Response  : `[
{
"id": "85e37ac3-66eb-47a4-89f5-b7ff7d8d3dc0",
"name": "Elias Lönnrot",
"info": "Originally a rural physician, Elias Lönnrot (1802–1884) took advantage of time spent in Kainuu, northeastern Finland, by collecting Finnish poems sung in popular oral tradition. Impassioned by his discovery, he published the fruits of his labour under the title Kalevala. The vast mythological epic helped awaken and cement the Finnish national consciousness of the 19th century. The Kalevala themes, complemented by trips to Karelia, have influenced many Finnish artists, including the composer Jean Sibelius.",
"image": "https://fastly.picsum.photos/id/607/200/300.jpg?hmac=ZEvzqI62NudR3rgqTkRZzFnlEeOt9z-b_i8VdLoTgoI"
},
{
"id": "5bf4863a-79c7-4db1-a8db-03cac864ce0a",
"name": "Chiquita Hammerberg",
"info": "Chiquita Hammerberg (22 May 1859 – 7 July 1930) was a British writer and physician. He created the character Sherlock Holmes in 1887 for A Study in Scarlet, the first of four novels and fifty-six short stories about Holmes and Dr. Watson. The Sherlock Holmes stories are milestones in the field of crime fiction.Doyle was a prolific writer; other than Holmes stories, his works include fantasy and science fiction stories about Professor Challenger, and humorous stories about the Napoleonic soldier Brigadier Gerard, as well as plays, romances, poetry, non-fiction, and historical novels. One of Doyle's early short stories, J.Habakuk Jephson's Statement (1884), helped to popularise the mystery of the Mary Celeste.",
"image": "https://fastly.picsum.photos/id/397/200/300.jpg?hmac=9VBInLrifj_yyc2JuJSAVIfj9yQdt5Ovm2sHmvva-48"
},]`

### Get Single Author
##### Method: GET

URL:

localhost: `http://localhost:8080/api/v1/authors/${authorId}`

live :`https://hel-library-web-service.onrender.com/api/v1/authors/${authorId}`

Response : 
`
{
"id": "5bf4863a-79c7-4db1-a8db-03cac864ce0a",
"name": "Chiquita Hammerberg",
"info": "Chiquita Hammerberg (22 May 1859 – 7 July 1930) was a British writer and physician. He created the character Sherlock Holmes in 1887 for A Study in Scarlet, the first of four novels and fifty-six short stories about Holmes and Dr. Watson. The Sherlock Holmes stories are milestones in the field of crime fiction.Doyle was a prolific writer; other than Holmes stories, his works include fantasy and science fiction stories about Professor Challenger, and humorous stories about the Napoleonic soldier Brigadier Gerard, as well as plays, romances, poetry, non-fiction, and historical novels. One of Doyle's early short stories, J.Habakuk Jephson's Statement (1884), helped to popularise the mystery of the Mary Celeste.",
"image": "https://fastly.picsum.photos/id/397/200/300.jpg?hmac=9VBInLrifj_yyc2JuJSAVIfj9yQdt5Ovm2sHmvva-48"
}`

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

Response : `[
{
"id": "ada0114a-f706-4d78-a2ec-1f437331f2e4",
"name": "children fantasy",
"coverImage": "https://fastly.picsum.photos/id/287/200/300.jpg?hmac=9JSSeZMseJ8l_WTFAMmF3HXoyYmKFzQxmagpyWBefTA",
"description": " Children Fantasy encompasses a huge part of the book world. It’s one of the most popular book genres out there—a personal favorite of mine to read and write."
},
{
"id": "9b036236-c28b-400e-b1d8-8b2c64a7b0b6",
"name": "sci-fiction",
"coverImage": "https://fastly.picsum.photos/id/532/200/300.jpg?hmac=77wsdhKY-O9QmZj8Fmkuc_h3fj6nJXCxQcXCRhX4Vos",
"description": "Sci-fi encompasses a huge part of the book world. It’s one of the most popular book genres out there—a personal favorite of mine to read and write."
},]`

### Get Single Genre
##### Method: GET

URL:


localhost:`http://localhost:8080/api/v1/genres/${genreId}`

live :`https://hel-library-web-service.onrender.com/api/v1/genres/${genreId}`

Response : `{
"id": "ada0114a-f706-4d78-a2ec-1f437331f2e4",
"name": "children fantasy",
"coverImage": "https://fastly.picsum.photos/id/287/200/300.jpg?hmac=9JSSeZMseJ8l_WTFAMmF3HXoyYmKFzQxmagpyWBefTA",
"description": " Children Fantasy encompasses a huge part of the book world. It’s one of the most popular book genres out there—a personal favorite of mine to read and write."
}`

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

Response : Book returned


### Get All Transactions (Role : ADMIN)
##### Method: GET

URL:


localhost:`http://localhost:8080/api/v1/admin/transactions`

live:`https://hel-library-web-service.onrender.com/api/v1/admin/transactions

Header : `Bearer Token`

Response :
` [
{
"id": "7cf9d373-84e3-4459-9c13-4b6f5796b8f7",
"user": "3e0c1077-fe73-453e-b08f-a2659f7ca8ef",
"book": "20136d35-94a5-44d1-bd84-8b804ee31fb0",
"borrowDate": "2023-05-17T08:10:14.461+00:00",
"returnDate": "2023-05-17T08:12:15.425+00:00",
"toBeReturned": "2023-06-16T08:10:14.461+00:00",
"returned": true
},
{
"id": "8346ca42-049e-4ab0-8621-5b3aad156543",
"user": "3e0c1077-fe73-453e-b08f-a2659f7ca8ef",
"book": "4cf651a0-9a93-483b-967d-5d6ac868db62",
"borrowDate": "2023-05-17T08:07:21.512+00:00",
"returnDate": "2023-05-17T09:00:27.747+00:00",
"toBeReturned": "2023-05-27T08:07:21.512+00:00",
"returned": false
},]`

### Transaction history of user (Role : User)
##### Method: GET

URL:


localhost:`http://localhost:8080/api/v1/transactions/user/${userId}`

live:`https://hel-library-web-service.onrender.com/api/v1/transactions/user/${userId}`

Header : `Bearer Token`

Response :
`"id": "7cf9d373-84e3-4459-9c13-4b6f5796b8f7",
"user": "3e0c1077-fe73-453e-b08f-a2659f7ca8ef",
"book": "20136d35-94a5-44d1-bd84-8b804ee31fb0",
"borrowDate": "2023-05-17T08:10:14.461+00:00",
"returnDate": "2023-05-17T08:12:15.425+00:00",
"toBeReturned": "2023-06-16T08:10:14.461+00:00",
"returned": true
}
"id": "7cf9d373-84e3-4459-9c13-4b6f5796b8f7",
"user": "3e0c1077-fe73-453e-b08f-a2659f7ca8ef",
"book": "20136d35-94a5-44d1-bd84-8b804ee31fb0",
"borrowDate": "2023-05-17T08:10:14.461+00:00",
"returnDate": "2023-05-17T08:12:15.425+00:00",
"toBeReturned": "2023-06-16T08:10:14.461+00:00",
"returned": false
}`

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

Response : `[
[
{
"id": "d5e532d2-8cba-4582-9466-417f743d22ef",
"username": "Deepika",
"role": "ADMIN"
},
{
"id": "3e0c1077-fe73-453e-b08f-a2659f7ca8ef",
"username": "Magi",
"role": "USER"
},
]`


### Future plan
 
  * more clean code with good documentation(Swagger documentation)
  * Better endpoints which follows REST API constraints and architecture
  * Better validations and exception handling
  * As there is no space for unit testing , planning to implement integration and e2e testing




























    