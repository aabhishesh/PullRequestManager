package com.draaggy.prm.async;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

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
