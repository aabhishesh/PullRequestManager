package com.aikyam.prm.async;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

public class AsyncQueueLauncher {

    private MergeQueueManager queueManager;

    @Autowired
    public AsyncQueueLauncher(MergeQueueManager queueManager) {
        this.queueManager = queueManager;
    }

    @PostConstruct
    public void wakeUpQueueManager() {
        queueManager.processQueue();
    }
}
