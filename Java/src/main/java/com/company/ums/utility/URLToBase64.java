package com.company.ums.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

import com.company.ums.exception.UploadException;

/*
* Downloads the image from provided link
* and converts it to a Base64 string that is later
* used for uploading to imgur
* */

public class URLToBase64 {

    byte[] byteData;
    String base64;

    public String getBase64String (String link) throws UploadException {
        try(InputStream in = new URL(link).openStream()){
            byteData = IOUtils.toByteArray(in);
            base64 = Base64.getEncoder().encodeToString(byteData);
        } catch (MalformedURLException e) {
            throw new UploadException(e, 555);
        } catch (IOException e) {
            throw new UploadException(e);
        }

        return base64;
    }
}
