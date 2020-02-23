# Imgur Image Uploader REST API

Application is registered with
	
	username: udayallangi
	client-Id: c18c160f2b5f410
	client-secret: 37e4d5fdb272ac705091cd0d331791e0ff51f1f4
	Token Type: bearer
	expires_in: 315360000
	account_id: 124551987
	account_username: udayallangi
	
GET Access Token
{
    "access_token": "07597920da999518464951687058cb355b319044",
    "expires_in": 315360000,
    "token_type": "bearer",
    "scope": "",
    "refresh_token": "cfb456bea99f8b33bb84e864792bf4cf24b0ad24",
    "account_id": 124551987,
    "account_username": "udayallangi"
}


To start this service, execute the below command:

    mvn spring-boot:run

## Show All uploaded images
GET request

    GET http://localhost:8080/v1/images

The base link to show imgur url's of all images successfully uploaded to imgur.

## New Upload Job
POST request 

    POST http://localhost:8080/v1/images/upload

Will return immediately as the image download and consequent upload to imgur happens asynchronously.


