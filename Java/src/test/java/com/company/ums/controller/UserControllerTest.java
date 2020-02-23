package com.company.ums.controller;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.ums.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {

    }

    @Test
    public void testUserShouldBeCreated() throws Exception {
        HttpEntity<Object> user = getHttpEntity(
                "{\"firstName\": \"uday\", \"lastName\": \"allangi\" }");
        ResponseEntity<User> resultAsset = template.postForEntity("/users", user,
                User.class);
        Assert.assertNotNull(resultAsset.getBody().getId());
    }

    @Test
    public void testUserShouldBeRetrievedById() throws Exception {
        HttpEntity<Object> article = getHttpEntity(
                "{\"firstName\": \"uday\", \"lastName\": \"allangi\" }");
        ResponseEntity<User> resultAsset = template.postForEntity("/users", article,
                User.class);

        Long id = resultAsset.getBody().getId();
        resultAsset = template.postForEntity("/users", id, User.class);

        Assert.assertNotNull(resultAsset);
    }
    
    @Test
    public void testSuccessfullFileUpload() throws IOException
    {
    	MockMultipartFile mutipartFile = new MockMultipartFile("file", 
    			"filename.txt", "text/plain", "some text".getBytes());


    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}
