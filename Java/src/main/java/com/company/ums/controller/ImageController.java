package com.company.ums.controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.ums.datastore.DatabaseMap;
import com.company.ums.domain.JobIdObject;
import com.company.ums.domain.JobStatusObject;
import com.company.ums.domain.UrlObject;
import com.company.ums.service.UrlUploadRequestService;


@RestController
@RequestMapping(value = "/v1/images", produces = "application/json")
public class ImageController {

    @Autowired
    private DatabaseMap databaseMap;

    @Autowired
    private UrlUploadRequestService asyncRequestService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<JobIdObject> uploadJob(@RequestBody UrlObject urlObject){

        CompletableFuture<JobIdObject> responseJobId = asyncRequestService.getJobIdForUrl(urlObject);
        try {
            return new ResponseEntity<>(responseJobId.get(), HttpStatus.CREATED);
        } catch (InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/upload/{jobId}", method = RequestMethod.GET)
    public ResponseEntity<JobStatusObject> findByJobId(@PathVariable Long jobId){
        Optional<JobStatusObject> jobStatusObject = databaseMap.getJobStatusById(jobId);

        if(jobStatusObject.isPresent()) {
            return new ResponseEntity<>(jobStatusObject.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

