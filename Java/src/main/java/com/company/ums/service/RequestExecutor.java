package com.company.ums.service;

import com.company.ums.exception.UploadException;
import com.company.ums.utility.URLToBase64;

/*
* Thread's request executor class, defines what
* processes are to be done in each thread.
* */

public class RequestExecutor implements Runnable{
    private String imageLink;
    URLToBase64 urlToBase64;
    UploadService uploadService;

    public RequestExecutor(String imageLink, URLToBase64 urlToBase64, UploadService uploadService) {
        this.imageLink = imageLink;
        this.uploadService = uploadService;
        this.urlToBase64 = urlToBase64;
    }

    @Override
    public void run() {

        try {
            //download the image from provided URL and convert to Base64 string
            String base64String = urlToBase64.getBase64String(imageLink);

            //upload the image to imgur using the provided base 64 string
            uploadService.uploadImage(base64String, imageLink);
        } catch (UploadException e) {
        	e.getStatus();
        }

    }
}
