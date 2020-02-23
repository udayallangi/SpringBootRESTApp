package com.company.ums.service;

import java.util.concurrent.ExecutorService;

import com.company.ums.datastore.JobUrlLists;
import com.company.ums.utility.URLToBase64;

/*
* Main executor class, responsible for creating executor service
* and delegating tasks.
* */

public class MainRequestExecutor {
    URLToBase64 urlToBase64;
    UploadService uploadService;
    JobUrlLists jobUrlLists;
    ExecutorService execService;

    public MainRequestExecutor(ExecutorService execService) {
        this.urlToBase64 = new URLToBase64();
        this.execService = execService;
    }

    public void mainExecutor(JobUrlLists jobUrlLists) {
        this.jobUrlLists = jobUrlLists;
        this.uploadService = new UploadService(jobUrlLists);



        for (String link : jobUrlLists.getPending()){
            RequestExecutor reqExec = new RequestExecutor(link, urlToBase64, uploadService);
            execService.submit(reqExec);
        }

    }
}
