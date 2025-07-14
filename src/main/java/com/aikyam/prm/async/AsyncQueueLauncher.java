package com.aikyam.prm.async;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(AsyncQueueLauncher.class)
public class AsyncQueueLauncher {
    @Autowired
    private MergeQueueManager queueManager;

    @PostConstruct
    public void wakeUpQueueManager() {
        queueManager.processQueue();
    }
}
