package com.aikyam.prm.config;

import com.aikyam.prm.async.MergeQueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(QueueHealthChecker.class)
public class QueueHealthChecker {

    @Autowired
    private MergeQueueManager queueManager;

    @Scheduled(fixedDelay = 5000)
    public void checkHealth() {
        if (!queueManager.isProcessing()) {
            System.out.println("[HealthCheck] Queue processor not running. Starting it...");
            queueManager.processQueue();
        } else {
            System.out.println("[HealthCheck] Queue processor is already running.");
        }
    }
}
