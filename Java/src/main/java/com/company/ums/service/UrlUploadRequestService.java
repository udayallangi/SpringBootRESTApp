package com.company.ums.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.company.ums.datastore.DatabaseMap;
import com.company.ums.datastore.JobUrlLists;
import com.company.ums.domain.JobIdObject;
import com.company.ums.domain.UrlObject;

@Service
public class UrlUploadRequestService {
    public static final int NUMBER_OF_THREADS = 4;
    ExecutorService execService;

    @Autowired
    private DatabaseMap databaseMap;

    public UrlUploadRequestService() {
        execService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    }

    @Async
    public CompletableFuture<JobIdObject> getJobIdForUrl(UrlObject urlObject) {
        JobUrlLists jobUrlLists = databaseMap.create(urlObject);
        JobIdObject jobId = new JobIdObject();
        jobId.setJobId(jobUrlLists.getId());
        MainRequestExecutor mainRequestExecutor = new MainRequestExecutor(execService);
        mainRequestExecutor.mainExecutor(jobUrlLists);

        return CompletableFuture.completedFuture(jobId);
    }

    @PreDestroy
    public void closeExecService() {
        execService.shutdown();
    }

}
